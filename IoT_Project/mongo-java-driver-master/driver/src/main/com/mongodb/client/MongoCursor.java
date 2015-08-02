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

package com.mongodb.client;

import com.mongodb.ServerAddress;
import com.mongodb.ServerCursor;
import com.mongodb.annotations.NotThreadSafe;

import java.io.Closeable;
import java.util.Iterator;

/**
 * The Mongo Cursor interface implementing the iterator protocol
 *
 * @since 3.0
 * @param <T> The type of documents the cursor contains
 */
@NotThreadSafe
public interface MongoCursor<T> extends Iterator<T>, Closeable {
    @Override
    void close();

    @Override
    boolean hasNext();

    @Override
    T next();

    /**
     * A special {@code next()} case that returns the next element in the iteration if available or null.
     *
     * <p>Tailable cursors are an example where this is useful. A call to {@code tryNext()} may return null, but in the future calling
     * {@code tryNext()} would return a new element if a document had been added to the capped collection.</p>
     *
     * @return the next element in the iteration if available or null.
     * @mongodb.driver.manual reference/glossary/#term-tailable-cursor Tailable Cursor
     */
    T tryNext();

    /**
     * Returns the server cursor
     *
     * @return ServerCursor
     */
    ServerCursor getServerCursor();

    /**
     * Returns the server address
     *
     * @return ServerAddress
     */
    ServerAddress getServerAddress();
}
