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
import org.openjdk.jmh.infra.BenchmarkParams;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@State(Scope.Thread)
@Fork(3)
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

    private LRUMap<String, String> map;

    @Setup
    public void populateLRU(BenchmarkParams params){
        map = new LRUMap<>(numEntries);
        if(!params.getBenchmark().contains("testPutLRU")){
            for(int i=1; i<=numEntries;i++){
                // Add some entries to the map
                map.put("key"+i, "value"+i);
            }

            map.put("key"+numEntries+1, "value"+numEntries+1);
        }
    }

    @Benchmark
    public void testPut() {
        if (!isPutAddSupported() || !isPutChangeSupported()) {
            return;
        }
        // Add some entries to the map

        for(int i=1; i<=numEntries;i++){
            // Add some entries to the map
            map.put("key"+i, "value"+i);
        }

        map.put("key"+(numEntries+1), "value"+(numEntries+1));
        map.put("key"+(numEntries+2), "value"+(numEntries+2));

    }

    @Benchmark
    public void testGet() {
        int randomKey = (int) (Math.random() * numEntries)+1;
        map.get("key"+randomKey);
        map.get("key"+1);

    }



}
