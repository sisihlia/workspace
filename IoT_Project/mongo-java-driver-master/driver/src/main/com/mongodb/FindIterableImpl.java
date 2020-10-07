/*
 * Copyright (c) 2008-2014 MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.FindOptions;
import com.mongodb.operation.BatchCursor;
import com.mongodb.operation.FindOperation;
import com.mongodb.operation.OperationExecutor;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static com.mongodb.assertions.Assertions.notNull;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

final class FindIterableImpl<T> implements FindIterable<T> {
    private final MongoNamespace namespace;
    private final Class<T> clazz;
    private final ReadPreference readPreference;
    private final CodecRegistry codecRegistry;
    private final OperationExecutor executor;
    private final FindOptions findOptions;
    private Object filter;

    FindIterableImpl(final MongoNamespace namespace, final Class<T> clazz, final CodecRegistry codecRegistry,
                     final ReadPreference readPreference, final OperationExecutor executor,
                     final Object filter, final FindOptions findOptions) {
        this.namespace = notNull("namespace", namespace);
        this.clazz = notNull("clazz", clazz);
        this.codecRegistry = notNull("codecRegistry", codecRegistry);
        this.readPreference = notNull("readPreference", readPreference);
        this.executor = notNull("executor", executor);
        this.filter = notNull("filter", filter);
        this.findOptions = notNull("findOptions", findOptions);
    }

    @Override
    public FindIterable<T> filter(final Object filter) {
        this.filter = filter;
        return this;
    }

    @Override
    public FindIterable<T> limit(final int limit) {
        findOptions.limit(limit);
        return this;
    }

    @Override
    public FindIterable<T> skip(final int skip) {
        findOptions.skip(skip);
        return this;
    }

    @Override
    public FindIterable<T> maxTime(final long maxTime, final TimeUnit timeUnit) {
        notNull("timeUnit", timeUnit);
        findOptions.maxTime(maxTime, timeUnit);
        return this;
    }

    @Override
    public FindIterable<T> batchSize(final int batchSize) {
        findOptions.batchSize(batchSize);
        return this;
    }

    @Override
    public FindIterable<T> modifiers(final Object modifiers) {
        findOptions.modifiers(modifiers);
        return this;
    }

    @Override
    public FindIterable<T> projection(final Object projection) {
        findOptions.projection(projection);
        return this;
    }

    @Override
    public FindIterable<T> sort(final Object sort) {
        findOptions.sort(sort);
        return this;
    }

    @Override
    public FindIterable<T> noCursorTimeout(final boolean noCursorTimeout) {
        findOptions.noCursorTimeout(noCursorTimeout);
        return this;
    }

    @Override
    public FindIterable<T> oplogReplay(final boolean oplogReplay) {
        findOptions.oplogReplay(oplogReplay);
        return this;
    }

    @Override
    public FindIterable<T> partial(final boolean partial) {
        findOptions.partial(partial);
        return this;
    }

    @Override
    public FindIterable<T> cursorType(final CursorType cursorType) {
        findOptions.cursorType(cursorType);
        return this;
    }

    @Override
    public MongoCursor<T> iterator() {
        return execute().iterator();
    }

    @Override
    public T first() {
        return execute().first();
    }

    @Override
    public <U> MongoIterable<U> map(final Function<T, U> mapper) {
        return new MappingIterable<T, U>(this, mapper);
    }

    @Override
    public void forEach(final Block<? super T> block) {
        execute().forEach(block);
    }

    @Override
    public <A extends Collection<? super T>> A into(final A target) {
        return execute().into(target);
    }

    private MongoIterable<T> execute() {
        return new FindOperationIterable(createQueryOperation(), this.readPreference, executor);
    }

    private <C> Codec<C> getCodec(final Class<C> clazz) {
        return codecRegistry.get(clazz);
    }

    private FindOperation<T> createQueryOperation() {
        return new FindOperation<T>(namespace, getCodec(clazz))
                   .filter(asBson(filter))
                   .batchSize(findOptions.getBatchSize())
                   .skip(findOptions.getSkip())
                   .limit(findOptions.getLimit())
                   .maxTime(findOptions.getMaxTime(MILLISECONDS), MILLISECONDS)
                   .modifiers(asBson(findOptions.getModifiers()))
                   .projection(asBson(findOptions.getProjection()))
                   .sort(asBson(findOptions.getSort()))
                   .cursorType(findOptions.getCursorType())
                   .noCursorTimeout(findOptions.isNoCursorTimeout())
                   .oplogReplay(findOptions.isOplogReplay())
                   .partial(findOptions.isPartial())
                   .slaveOk(readPreference.isSlaveOk());
    }

    private BsonDocument asBson(final Object document) {
        return BsonDocumentWrapper.asBsonDocument(document, codecRegistry);
    }

    private final class FindOperationIterable extends OperationIterable<T> {
        private final ReadPreference readPreference;
        private final OperationExecutor executor;

        FindOperationIterable(final FindOperation<T> operation, final ReadPreference readPreference,
                              final OperationExecutor executor) {
            super(operation, readPreference, executor);
            this.readPreference = readPreference;
            this.executor = executor;
        }

        @Override
        public T first() {
            FindOperation<T> findFirstOperation = createQueryOperation().batchSize(0).limit(-1);
            BatchCursor<T> batchCursor = executor.execute(findFirstOperation, readPreference);
            return batchCursor.hasNext() ? batchCursor.next().iterator().next() : null;
        }

        @Override
        public FindOperationIterable batchSize(final int batchSize) {
            batchSize(batchSize);
            return this;
        }
    }
}
