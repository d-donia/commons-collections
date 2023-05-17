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

import org.apache.commons.collections4.collection.AbstractCollectionTest;
import org.apache.commons.collections4.map.AbstractMapTest;
import org.apache.commons.collections4.map.LRUMap;
import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.apache.commons.collections4.map.PassiveExpiringMap.ExpirationPolicy;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@State(Scope.Thread)
@Fork(3)
public class PassiveExpiringMapTest<K, V> extends AbstractMapTest<K, V> {

    private static class TestExpirationPolicy
        implements ExpirationPolicy<Integer, String> {

        private static final long serialVersionUID = 1L;

        @Override

        public long expirationTime(final Integer key, final String value) {
            // odd keys expire immediately, even keys never expire
            if (key == null) {
                return 0;
            }

            if (key.intValue() % 2 == 0) {
                return -1;
            }

            return 0;
        }
    }

    @Override
    public Map<K, V> makeObject() {
        return new PassiveExpiringMap<>();
    }

    @Override
    protected int getIterationBehaviour() {
        return AbstractCollectionTest.UNORDERED;
    }

    public PassiveExpiringMapTest() {
        super(PassiveExpiringMapTest.class.getSimpleName());
    }

    @Param({"10", "100", "1000"})
    int numEntries;

    @Param({"true", "false"})
    boolean decorated;

    private Map<Integer, String> m;

    @Setup
    public void makeDecoratedTestMap(BenchmarkParams params) {
        final Map<Integer, String> not_decorated_m = new HashMap<>();
        if (params.getParam("decorated").equals("true")){
            if(!params.getBenchmark().contains("testPutMap")) {
                for (int i = 1; i <= numEntries; i++) {
                    not_decorated_m.put(Integer.valueOf(i), "value" + i);
                }
            }
            m = new PassiveExpiringMap<>(new TestExpirationPolicy(), not_decorated_m);
        }
        else{
            m = new PassiveExpiringMap<>(new TestExpirationPolicy());
            if (!params.getBenchmark().contains("testPutMap")) {
                for (int i = 1; i <= numEntries; i++) {
                    m.put(Integer.valueOf(i), "value" + i);
                }
            }
        }

    }

    @Benchmark
    public void testPutMap() {
        for (int i = 1; i <= numEntries; i++) {
            m.put(Integer.valueOf(i), "value" + i);
        }
    }

    @Benchmark
    public void testRemoveMap() {
        for (int i = 1; i <= numEntries; i++) {
            m.remove(i);
        }
    }

    @Benchmark
    public void testClearMap() {
        m.clear();
    }

    /*public void testExpiration() {
        validateExpiration(new PassiveExpiringMap<String, String>(500), 500);
        validateExpiration(new PassiveExpiringMap<>(
                new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<String, String>(1, TimeUnit.SECONDS)), 1000);
    }

    public void testZeroTimeToLive() {
        // item should not be available
        final PassiveExpiringMap<String, String> m = new PassiveExpiringMap<>(0L);
        m.put("a", "b");
        m.get("a");
    }

    private void validateExpiration(final Map<String, String> map, final long timeout) {
        map.put("a", "b");

        try{
            Thread.sleep(2 * timeout);
        } catch (final InterruptedException e) {
            fail();
        }


    }*/

}
