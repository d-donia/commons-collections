/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package benchmark.map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.map.AbstractOrderedMapTest;
import org.apache.commons.collections4.map.LRUMap;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@State(Scope.Thread)
public class LRUMapTest<K, V> extends AbstractOrderedMapTest<K, V> {

    public LRUMapTest() {
        super(LRUMapTest.class.getSimpleName());
    }
    @Override
    public LRUMap<K, V> makeObject() {
        return new LRUMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LRUMap<K, V> makeFullMap() {
        return (LRUMap<K, V>) super.makeFullMap();
    }

    @Override
    public boolean isGetStructuralModify() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LRUMap<K, V> getMap() {
        return (LRUMap<K, V>) super.getMap();
    }

    @Param({"10", "100", "1000"})
    int numEntries;

    @Benchmark
    public void testLRU() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        LRUMap<String, String> map = new LRUMap<>(numEntries);

        // Add some entries to the map

        for(int i=1; i<=numEntries;i++){
            // Add some entries to the map
            map.put("key"+i, "value"+i);
        }

        for(int i=1; i<=numEntries;i++){
            // Check that the entries are in the map
            map.get("key"+i);
        }

        // Check that the size of the map is 3
        map.size();

        // Remove an entry from the map
        map.remove("key2");

        // Check that the removed entry is no longer in the map
        map.get("key2");

        // Add an entry with a null key to the map
        map.put("key"+(numEntries+1), null);

        // Add more entries to the map than its maximum size
        map.put("key"+(numEntries+2), "value"+(numEntries+1));
        map.put("key"+(numEntries+3), "value"+(numEntries+2));

        // Check that the least recently used entries have been removed from the map
        map.get("key3");
        map.get("key"+(numEntries+1));
        map.get("key"+(numEntries+2));
        map.get("key"+(numEntries+3));

        // Clear the map
        map.clear();

    }



}
