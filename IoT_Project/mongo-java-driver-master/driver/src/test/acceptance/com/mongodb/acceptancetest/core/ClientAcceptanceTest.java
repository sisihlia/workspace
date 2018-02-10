/*
 * Copyright (c) 2015 MongoDB, Inc.
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

package com.mongodb.acceptancetest.core;

import com.mongodb.MongoClient;
import com.mongodb.client.DatabaseTestCase;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.Fixture.getMongoClient;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Documents the basic functionality available for the MongoClient via the Java driver.
 */
public class ClientAcceptanceTest extends DatabaseTestCase {

    @Test
    public void shouldListDatabaseNamesFromDatabase() {
        database.createCollection(getCollectionName());
        List<String> names = client.getDatabaseNames();

        assertThat(names.contains(getDatabaseName()), is(true));
    }

    @Test
    public void shouldListDatabasesFromDatabase() {
        database.dropDatabase();

        List<Document> databases = client.listDatabases().into(new ArrayList<Document>());
        int size = databases.size();

        database.createCollection(getCollectionName());
        databases = client.listDatabases().into(new ArrayList<Document>());
        assertThat(databases.size(), is(greaterThan(size)));
    }

    @Test
    public void shouldBeAbleToListAllTheDatabasesAvailable() {
        MongoClient mongoClient = getMongoClient();
        MongoDatabase firstDatabase = mongoClient.getDatabase("FirstNewDatabase");
        MongoDatabase secondDatabase = mongoClient.getDatabase("SecondNewDatabase");
        MongoDatabase otherDatabase = mongoClient.getDatabase("DatabaseThatDoesNotExistYet");

        try {
            // given
            firstDatabase.getCollection("coll").insertOne(new Document("aDoc", "to force database creation"));
            secondDatabase.getCollection("coll").insertOne(new Document("aDoc", "to force database creation"));

            //when
            List<String> databaseNames = mongoClient.getDatabaseNames();

            //then
            assertThat(databaseNames, hasItems(firstDatabase.getName(), secondDatabase.getName()));
            assertThat(databaseNames, not(hasItem(otherDatabase.getName())));
        } finally {
            //tear down
            firstDatabase.dropDatabase();
            secondDatabase.dropDatabase();
        }
    }
}
