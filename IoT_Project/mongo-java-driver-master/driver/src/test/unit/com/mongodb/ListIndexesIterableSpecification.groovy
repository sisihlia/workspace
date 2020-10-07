/*
 * Copyright 2015 MongoDB, Inc.
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

package com.mongodb

import com.mongodb.operation.BatchCursor
import com.mongodb.operation.ListIndexesOperation
import org.bson.Document
import org.bson.codecs.BsonValueCodecProvider
import org.bson.codecs.DocumentCodec
import org.bson.codecs.DocumentCodecProvider
import org.bson.codecs.ValueCodecProvider
import org.bson.codecs.configuration.RootCodecRegistry
import spock.lang.Specification

import static com.mongodb.CustomMatchers.isTheSameAs
import static com.mongodb.ReadPreference.secondary
import static java.util.concurrent.TimeUnit.MILLISECONDS
import static spock.util.matcher.HamcrestSupport.expect

class ListIndexesIterableSpecification extends Specification {

    def namespace = new MongoNamespace('db', 'coll')
    def codecRegistry = new RootCodecRegistry([new ValueCodecProvider(),
                                               new DocumentCodecProvider(),
                                               new DBObjectCodecProvider(),
                                               new BsonValueCodecProvider()])
    def readPreference = secondary()

    def 'should build the expected listIndexesOperation'() {
        given:
        def executor = new TestOperationExecutor([null, null]);
        def listIndexesIterable = new ListIndexesIterableImpl<Document>(namespace, Document, codecRegistry, readPreference, executor)
                .batchSize(100).maxTime(1000, MILLISECONDS)

        when: 'default input should be as expected'
        listIndexesIterable.iterator()

        def operation = executor.getReadOperation() as ListIndexesOperation<Document>
        def readPreference = executor.getReadPreference()

        then:
        expect operation, isTheSameAs(new ListIndexesOperation<Document>(namespace, new DocumentCodec())
                .batchSize(100).maxTime(1000, MILLISECONDS))
        readPreference == secondary()

        when: 'overriding initial options'
        listIndexesIterable.batchSize(99)
                .maxTime(999, MILLISECONDS)
                .iterator()

        operation = executor.getReadOperation() as ListIndexesOperation<Document>

        then: 'should use the overrides'
        expect operation, isTheSameAs(new ListIndexesOperation<Document>(namespace, new DocumentCodec())
                .batchSize(99).maxTime(999, MILLISECONDS))
    }


    def 'should follow the MongoIterable interface as expected'() {
        given:
        def cannedResults = [new Document('_id', 1), new Document('_id', 2), new Document('_id', 3)]
        def cursor = {
            Stub(BatchCursor) {
                def count = 0
                def results;
                def getResult = {
                    count++
                    results = count == 1 ? cannedResults : null
                    results
                }
                next() >> {
                    getResult()
                }
                hasNext() >> {
                    count == 0
                }

            }
        }
        def executor = new TestOperationExecutor([cursor(), cursor(), cursor(), cursor()]);
        def mongoIterable = new ListIndexesIterableImpl<Document>(namespace, Document, codecRegistry, readPreference, executor)

        when:
        def results = mongoIterable.first()

        then:
        results == cannedResults[0]

        when:
        def count = 0
        mongoIterable.forEach(new Block<Document>() {
            @Override
            void apply(Document document) {
                count++
            }
        })

        then:
        count == 3

        when:
        def target = []
        mongoIterable.into(target)

        then:
        target == cannedResults

        when:
        target = []
        mongoIterable.map(new Function<Document, Integer>() {
            @Override
            Integer apply(Document document) {
                document.getInteger('_id')
            }
        }).into(target)

        then:
        target == [1, 2, 3]
    }
}
