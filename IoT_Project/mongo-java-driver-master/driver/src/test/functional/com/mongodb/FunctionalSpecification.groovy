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

package com.mongodb

import spock.lang.Specification

import static com.mongodb.Fixture.getDefaultDatabaseName
import static com.mongodb.Fixture.getMongoClient

class FunctionalSpecification extends Specification {
    protected DB database;
    protected DBCollection collection;

    def setupSpec() {
        getMongoClient().getDB(getDefaultDatabaseName()).dropDatabase()
    }

    def cleanupSpec() {
        getMongoClient().getDB(getDefaultDatabaseName()).dropDatabase()
    }

    def setup() {
        database = getMongoClient().getDB(getDefaultDatabaseName())
        collection = database.getCollection(getClass().getName())
        collection.drop();
    }

    def cleanup() {
        if (collection != null) {
            collection.drop()
        }
    }

    String getDatabaseName() {
        getDefaultDatabaseName()
    }

    String getCollectionName() {
        collection.getName()
    }
}
