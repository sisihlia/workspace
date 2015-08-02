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

package com.mongodb.async.client;

import com.mongodb.MongoNamespace;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.annotations.ThreadSafe;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.CreateIndexOptions;
import com.mongodb.client.model.FindOneAndDeleteOptions;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.RenameCollectionOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.WriteModel;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.List;

/**
 * The MongoCollection interface.
 *
 * <p>Note: Additions to this interface will not be considered to break binary compatibility.</p>
 *
 * @param <T> The type that this collection will encode documents from and decode documents to.
 * @since 3.0
 */
@ThreadSafe
public interface MongoCollection<T> {

    /**
     * Gets the namespace of this collection.
     *
     * @return the namespace
     */
    MongoNamespace getNamespace();

    /**
     * Get the default class to cast any documents returned from the database into.
     *
     * @return the default class to cast any documents into
     */
    Class<T> getDefaultClass();

    /**
     * Get the codec registry for the MongoCollection.
     *
     * @return the {@link org.bson.codecs.configuration.CodecRegistry}
     */
    CodecRegistry getCodecRegistry();

    /**
     * Get the read preference for the MongoCollection.
     *
     * @return the {@link com.mongodb.ReadPreference}
     */
    ReadPreference getReadPreference();

    /**
     * Get the write concern for the MongoCollection.
     *
     * @return the {@link com.mongodb.WriteConcern}
     */
    WriteConcern getWriteConcern();

    /**
     * Create a new MongoCollection instance with a different default class to cast any documents returned from the database into..
     *
     * @param clazz the default class to cast any documents returned from the database into.
     * @param <C> The type that the new collection will encode documents from and decode documents to
     * @return a new MongoCollection instance with the different default class
     */
    <C> MongoCollection<C> withDefaultClass(Class<C> clazz);

    /**
     * Create a new MongoCollection instance with a different codec registry.
     *
     * @param codecRegistry the new {@link org.bson.codecs.configuration.CodecRegistry} for the collection
     * @return a new MongoCollection instance with the different codec registry
     */
    MongoCollection<T> withCodecRegistry(CodecRegistry codecRegistry);

    /**
     * Create a new MongoCollection instance with a different read preference.
     *
     * @param readPreference the new {@link com.mongodb.ReadPreference} for the collection
     * @return a new MongoCollection instance with the different readPreference
     */
    MongoCollection<T> withReadPreference(ReadPreference readPreference);

    /**
     * Create a new MongoCollection instance with a different write concern.
     *
     * @param writeConcern the new {@link com.mongodb.WriteConcern} for the collection
     * @return a new MongoCollection instance with the different writeConcern
     */
    MongoCollection<T> withWriteConcern(WriteConcern writeConcern);

    /**
     * Counts the number of documents in the collection.
     *
     * @param callback the callback passed the number of documents in the collection
     */
    void count(SingleResultCallback<Long> callback);

    /**
     * Counts the number of documents in the collection according to the given options.
     *
     * @param filter   the query filter
     * @param callback the callback passed the number of documents in the collection
     */
    void count(Object filter, SingleResultCallback<Long> callback);

    /**
     * Counts the number of documents in the collection according to the given options.
     *
     * @param filter   the query filter
     * @param options  the options describing the count
     * @param callback the callback passed the number of documents in the collection
     */
    void count(Object filter, CountOptions options, SingleResultCallback<Long> callback);

    /**
     * Gets the distinct values of the specified field name.
     *
     * @param fieldName the field name
     * @param clazz     the default class to cast any distinct items into.
     * @param <C>       the target type of the iterable.
     * @return an iterable of distinct values
     * @mongodb.driver.manual reference/command/distinct/ Distinct
     */
    <C> DistinctIterable<C> distinct(String fieldName, Class<C> clazz);

    /**
     * Finds all documents in the collection.
     *
     * @return the find iterable interface
     * @mongodb.driver.manual tutorial/query-documents/ Find
     */
    FindIterable<T> find();

    /**
     * Finds all documents in the collection.
     *
     * @param clazz the class to decode each document into
     * @param <C>   the target document type of the iterable.
     * @return the find iterable interface
     * @mongodb.driver.manual tutorial/query-documents/ Find
     */
    <C> FindIterable<C> find(Class<C> clazz);

    /**
     * Finds all documents in the collection.
     *
     * @param filter the query filter
     * @return the find iterable interface
     * @mongodb.driver.manual tutorial/query-documents/ Find
     */
    FindIterable<T> find(Object filter);

    /**
     * Finds all documents in the collection.
     *
     * @param filter the query filter
     * @param clazz  the class to decode each document into
     * @param <C>    the target document type of the iterable.
     * @return the find iterable interface
     * @mongodb.driver.manual tutorial/query-documents/ Find
     */
    <C> FindIterable<C> find(Object filter, Class<C> clazz);

    /**
     * Aggregates documents according to the specified aggregation pipeline.  If the pipeline ends with a $out stage, the returned
     * iterable will be a query of the collection that the aggregation was written to.  Note that in this case the pipeline will be
     * executed even if the iterable is never iterated.
     *
     * @param pipeline the aggregate pipeline
     * @return an iterable containing the result of the aggregation operation
     * @mongodb.driver.manual aggregation/ Aggregation
     */
    AggregateIterable<Document> aggregate(List<?> pipeline);

    /**
     * Aggregates documents according to the specified aggregation pipeline.  If the pipeline ends with a $out stage, the returned
     * iterable will be a query of the collection that the aggregation was written to.  Note that in this case the pipeline will be
     * executed even if the iterable is never iterated.
     *
     * @param pipeline the aggregate pipeline
     * @param clazz    the class to decode each document into
     * @param <C>      the target document type of the iterable.
     * @return an iterable containing the result of the aggregation operation
     * @mongodb.driver.manual aggregation/ Aggregation
     */
    <C> AggregateIterable<C> aggregate(List<?> pipeline, Class<C> clazz);

    /**
     * Aggregates documents according to the specified map-reduce function.
     *
     * @param mapFunction    A JavaScript function that associates or "maps" a value with a key and emits the key and value pair.
     * @param reduceFunction A JavaScript function that "reduces" to a single object all the values associated with a particular key.
     * @return an iterable containing the result of the map-reduce operation
     * @mongodb.driver.manual reference/command/mapReduce/ map-reduce
     */
    MapReduceIterable<Document> mapReduce(String mapFunction, String reduceFunction);

    /**
     * Aggregates documents according to the specified map-reduce function.
     *
     * @param mapFunction    A JavaScript function that associates or "maps" a value with a key and emits the key and value pair.
     * @param reduceFunction A JavaScript function that "reduces" to a single object all the values associated with a particular key.
     * @param clazz          the class to decode each resulting document into.
     * @param <C>            the target document type of the iterable.
     * @return an iterable containing the result of the map-reduce operation
     * @mongodb.driver.manual reference/command/mapReduce/ map-reduce
     */
    <C> MapReduceIterable<C> mapReduce(String mapFunction, String reduceFunction, Class<C> clazz);

    /**
     * Executes a mix of inserts, updates, replaces, and deletes.
     *
     * @param requests the writes to execute
     * @param callback the callback passed the result of the bulk write
     */
    void bulkWrite(List<? extends WriteModel<? extends T>> requests, SingleResultCallback<BulkWriteResult> callback);

    /**
     * Executes a mix of inserts, updates, replaces, and deletes.
     *
     * @param requests the writes to execute
     * @param options  the options to apply to the bulk write operation
     * @param callback the callback passed the result of the bulk write
     */
    void bulkWrite(List<? extends WriteModel<? extends T>> requests, BulkWriteOptions options,
                   SingleResultCallback<BulkWriteResult> callback);

    /**
     * Inserts the provided document. If the document is missing an identifier, the driver should generate one.
     *
     * @param document the document to insert
     * @param callback the callback that is completed once the insert has completed
     * @throws com.mongodb.MongoWriteException        returned via the callback
     * @throws com.mongodb.MongoWriteConcernException returned via the callback
     * @throws com.mongodb.MongoException             returned via the callback
     */
    void insertOne(T document, SingleResultCallback<Void> callback);

    /**
     * Inserts one or more documents.  A call to this method is equivalent to a call to the {@code bulkWrite} method
     *
     * @param documents the documents to insert
     * @param callback  the callback that is completed once the insert has completed
     * @throws com.mongodb.MongoBulkWriteException if there's an exception in the bulk write operation
     * @throws com.mongodb.MongoException          if the write failed due some other failure
     * @see com.mongodb.async.client.MongoCollection#bulkWrite
     */
    void insertMany(List<? extends T> documents, SingleResultCallback<Void> callback);

    /**
     * Inserts one or more documents.  A call to this method is equivalent to a call to the {@code bulkWrite} method
     *
     * @param documents the documents to insert
     * @param options   the options to apply to the operation
     * @param callback  the callback that is completed once the insert has completed
     * @throws com.mongodb.MongoBulkWriteException if there's an exception in the bulk write operation
     * @throws com.mongodb.MongoException          if the write failed due some other failure
     * @see com.mongodb.async.client.MongoCollection#bulkWrite
     */
    void insertMany(List<? extends T> documents, InsertManyOptions options, SingleResultCallback<Void> callback);

    /**
     * Removes at most one document from the collection that matches the given filter.  If no documents match, the collection is not
     * modified.
     *
     * @param filter   the query filter to apply the the delete operation
     * @param callback the callback passed the result of the remove one operation
     * @throws com.mongodb.MongoWriteException        returned via the callback
     * @throws com.mongodb.MongoWriteConcernException returned via the callback
     * @throws com.mongodb.MongoException             returned via the callback
     */
    void deleteOne(Object filter, SingleResultCallback<DeleteResult> callback);

    /**
     * Removes all documents from the collection that match the given query filter.  If no documents match, the collection is not modified.
     *
     * @param filter   the query filter to apply the the delete operation
     * @param callback the callback passed the result of the remove many operation
     * @throws com.mongodb.MongoWriteException        returned via the callback
     * @throws com.mongodb.MongoWriteConcernException returned via the callback
     * @throws com.mongodb.MongoException             returned via the callback
     */
    void deleteMany(Object filter, SingleResultCallback<DeleteResult> callback);

    /**
     * Replace a document in the collection according to the specified arguments.
     *
     * @param filter      the query filter to apply the the replace operation
     * @param replacement the replacement document
     * @param callback    the callback passed the result of the replace one operation
     * @throws com.mongodb.MongoWriteException        returned via the callback
     * @throws com.mongodb.MongoWriteConcernException returned via the callback
     * @throws com.mongodb.MongoException             returned via the callback
     * @mongodb.driver.manual tutorial/modify-documents/#replace-the-document Replace
     */
    void replaceOne(Object filter, T replacement, SingleResultCallback<UpdateResult> callback);

    /**
     * Replace a document in the collection according to the specified arguments.
     *
     * @param filter      the query filter to apply the the replace operation
     * @param replacement the replacement document
     * @param options     the options to apply to the replace operation
     * @param callback    the callback passed the result of the replace one operation
     * @throws com.mongodb.MongoWriteException        returned via the callback
     * @throws com.mongodb.MongoWriteConcernException returned via the callback
     * @throws com.mongodb.MongoException             returned via the callback
     * @mongodb.driver.manual tutorial/modify-documents/#replace-the-document Replace
     */
    void replaceOne(Object filter, T replacement, UpdateOptions options, SingleResultCallback<UpdateResult> callback);

    /**
     * Update a single document in the collection according to the specified arguments.
     *
     * @param filter   a document describing the query filter, which may not be null. This can be of any type for which a {@code Codec} is
     *                 registered
     * @param update   a document describing the update, which may not be null. The update to apply must include only update operators. This
     *                 can be of any type for which a {@code Codec} is registered
     * @param callback the callback passed the result of the update one operation
     * @throws com.mongodb.MongoWriteException        returned via the callback
     * @throws com.mongodb.MongoWriteConcernException returned via the callback
     * @throws com.mongodb.MongoException             returned via the callback
     * @mongodb.driver.manual tutorial/modify-documents/ Updates
     * @mongodb.driver.manual reference/operator/update/ Update Operators
     */
    void updateOne(Object filter, Object update, SingleResultCallback<UpdateResult> callback);

    /**
     * Update a single document in the collection according to the specified arguments.
     *
     * @param filter   a document describing the query filter, which may not be null. This can be of any type for which a {@code Codec} is
     *                 registered
     * @param update   a document describing the update, which may not be null. The update to apply must include only update operators. This
     *                 can be of any type for which a {@code Codec} is registered
     * @param options  the options to apply to the update operation
     * @param callback the callback passed the result of the update one operation
     * @throws com.mongodb.MongoWriteException        returned via the callback
     * @throws com.mongodb.MongoWriteConcernException returned via the callback
     * @throws com.mongodb.MongoException             returned via the callback
     * @mongodb.driver.manual tutorial/modify-documents/ Updates
     * @mongodb.driver.manual reference/operator/update/ Update Operators
     */
    void updateOne(Object filter, Object update, UpdateOptions options, SingleResultCallback<UpdateResult> callback);

    /**
     * Update a single document in the collection according to the specified arguments.
     *
     * @param filter   a document describing the query filter, which may not be null. This can be of any type for which a {@code Codec} is
     *                 registered
     * @param update   a document describing the update, which may not be null. The update to apply must include only update operators. This
     *                 can be of any type for which a {@code Codec} is registered
     * @param callback the callback passed the result of the update one operation
     * @throws com.mongodb.MongoWriteException        returned via the callback
     * @throws com.mongodb.MongoWriteConcernException returned via the callback
     * @throws com.mongodb.MongoException             returned via the callback
     * @mongodb.driver.manual tutorial/modify-documents/ Updates
     * @mongodb.driver.manual reference/operator/update/ Update Operators
     */
    void updateMany(Object filter, Object update, SingleResultCallback<UpdateResult> callback);

    /**
     * Update a single document in the collection according to the specified arguments.
     *
     * @param filter   a document describing the query filter, which may not be null. This can be of any type for which a {@code Codec} is
     *                 registered
     * @param update   a document describing the update, which may not be null. The update to apply must include only update operators. This
     *                 can be of any type for which a {@code Codec} is registered
     * @param options  the options to apply to the update operation
     * @param callback the callback passed the result of the update one operation
     * @throws com.mongodb.MongoWriteException        returned via the callback
     * @throws com.mongodb.MongoWriteConcernException returned via the callback
     * @throws com.mongodb.MongoException             returned via the callback
     * @mongodb.driver.manual tutorial/modify-documents/ Updates
     * @mongodb.driver.manual reference/operator/update/ Update Operators
     */
    void updateMany(Object filter, Object update, UpdateOptions options, SingleResultCallback<UpdateResult> callback);

    /**
     * Atomically find a document and remove it.
     *
     * @param filter   the query filter to find the document with
     * @param callback the callback passed the document that was removed.  If no documents matched the query filter, then null will be
     *                 returned
     */
    void findOneAndDelete(Object filter, SingleResultCallback<T> callback);

    /**
     * Atomically find a document and remove it.
     *
     * @param filter   the query filter to find the document with
     * @param options  the options to apply to the operation
     * @param callback the callback passed the document that was removed.  If no documents matched the query filter, then null will be
     *                 returned
     */
    void findOneAndDelete(Object filter, FindOneAndDeleteOptions options, SingleResultCallback<T> callback);

    /**
     * Atomically find a document and replace it.
     *
     * @param filter      the query filter to apply the the replace operation
     * @param replacement the replacement document
     * @param callback    the callback passed the document that was replaced.  Depending on the value of the {@code returnOriginal}
     *                    property, this will either be the document as it was before the update or as it is after the update.  If no
     *                    documents matched the query filter, then null will be returned
     */
    void findOneAndReplace(Object filter, T replacement, SingleResultCallback<T> callback);

    /**
     * Atomically find a document and replace it.
     *
     * @param filter      the query filter to apply the the replace operation
     * @param replacement the replacement document
     * @param options     the options to apply to the operation
     * @param callback    the callback passed the document that was replaced.  Depending on the value of the {@code returnOriginal}
     *                    property, this will either be the document as it was before the update or as it is after the update.  If no
     *                    documents matched the query filter, then null will be returned
     */
    void findOneAndReplace(Object filter, T replacement, FindOneAndReplaceOptions options, SingleResultCallback<T> callback);

    /**
     * Atomically find a document and update it.
     *
     * @param filter   a document describing the query filter, which may not be null. This can be of any type for which a {@code Codec} is
     *                 registered
     * @param update   a document describing the update, which may not be null. The update to apply must include only update operators. This
     *                 can be of any type for which a {@code Codec} is registered
     * @param callback the callback passed the document that was updated before the update was applied.  If no documents matched the query
     *                 filter, then null will be returned
     */
    void findOneAndUpdate(Object filter, Object update, SingleResultCallback<T> callback);

    /**
     * Atomically find a document and update it.
     *
     * @param filter   a document describing the query filter, which may not be null. This can be of any type for which a {@code Codec} is
     *                 registered
     * @param update   a document describing the update, which may not be null. The update to apply must include only update operators. This
     *                 can be of any type for which a {@code Codec} is registered
     * @param options  the options to apply to the operation
     * @param callback the callback passed the document that was updated.  Depending on the value of the {@code returnOriginal} property,
     *                 this will either be the document as it was before the update or as it is after the update.  If no documents matched
     *                 the query filter, then null will be returned
     */
    void findOneAndUpdate(Object filter, Object update, FindOneAndUpdateOptions options, SingleResultCallback<T> callback);

    /**
     * Drops this collection from the Database.
     *
     * @param callback the callback that is completed once the collection has been dropped
     * @mongodb.driver.manual reference/command/drop/ Drop Collection
     */
    void dropCollection(SingleResultCallback<Void> callback);

    /**
     * Creates an index.
     *
     * @param key      an object describing the index key(s), which may not be null. This can be of any type for which a {@code Codec} is
     *                 registered
     * @param callback the callback that is completed once the index has been created
     * @mongodb.driver.manual reference/method/db.collection.ensureIndex Ensure Index
     */
    void createIndex(Object key, SingleResultCallback<Void> callback);

    /**
     * Creates an index.
     *
     * @param key      an object describing the index key(s), which may not be null. This can be of any type for which a {@code Codec} is
     *                 registered
     * @param options  the options for the index
     * @param callback the callback that is completed once the index has been created
     * @mongodb.driver.manual reference/method/db.collection.ensureIndex Ensure Index
     */
    void createIndex(Object key, CreateIndexOptions options, SingleResultCallback<Void> callback);

    /**
     * Get all the indexes in this collection.
     *
     * @return the list indexes iterable interface
     * @mongodb.driver.manual reference/command/listIndexes/ listIndexes
     */
    ListIndexesIterable<Document> listIndexes();

    /**
     * Get all the indexes in this collection.
     *
     * @param clazz    the class to decode each document into
     * @param <C>      the target document type of the iterable.
     * @return the list indexes iterable interface
     * @mongodb.driver.manual reference/command/listIndexes/ listIndexes
     */
    <C> ListIndexesIterable<C> listIndexes(Class<C> clazz);

    /**
     * Drops the given index.
     *
     * @param indexName the name of the index to remove
     * @param callback  the callback that is completed once the index has been dropped
     * @mongodb.driver.manual reference/command/dropIndexes/ Drop Indexes
     */
    void dropIndex(String indexName, SingleResultCallback<Void> callback);

    /**
     * Drop all the indexes on this collection, except for the default on _id.
     *
     * @param callback the callback that is completed once all the indexes have been dropped
     * @mongodb.driver.manual reference/command/dropIndexes/ Drop Indexes
     */
    void dropIndexes(SingleResultCallback<Void> callback);

    /**
     * Rename the collection with oldCollectionName to the newCollectionName.
     *
     * @param newCollectionNamespace the namespace the collection will be renamed to
     * @param callback               the callback that is completed once the collection has been renamed
     * @throws com.mongodb.MongoServerException if you provide a newCollectionName that is the name of an existing collection, or if the
     *                                          oldCollectionName is the name of a collection that doesn't exist
     * @mongodb.driver.manual reference/commands/renameCollection Rename collection
     */
    void renameCollection(MongoNamespace newCollectionNamespace, SingleResultCallback<Void> callback);

    /**
     * Rename the collection with oldCollectionName to the newCollectionName.
     *
     * @param newCollectionNamespace the name the collection will be renamed to
     * @param options                the options for renaming a collection
     * @param callback               the callback that is completed once the collection has been renamed
     * @throws com.mongodb.MongoServerException if you provide a newCollectionName that is the name of an existing collection and dropTarget
     *                                          is false, or if the oldCollectionName is the name of a collection that doesn't exist
     * @mongodb.driver.manual reference/commands/renameCollection Rename collection
     */
    void renameCollection(MongoNamespace newCollectionNamespace, RenameCollectionOptions options, SingleResultCallback<Void> callback);

}
