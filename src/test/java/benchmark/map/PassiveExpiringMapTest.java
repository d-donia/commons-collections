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
import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.apache.commons.collections4.map.PassiveExpiringMap.ExpirationPolicy;
import org.openjdk.jmh.annotations.*;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;


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

    public PassiveExpiringMapTest() {
        super(PassiveExpiringMapTest.class.getSimpleName());
    }

//    public void testCreate() throws Exception {
//        writeExternalFormToDisk((java.io.Serializable) makeObject(),
//                "src/test/resources/data/test/PassiveExpiringMap.emptyCollection.version4.obj");
//
//        writeExternalFormToDisk((java.io.Serializable) makeFullMap(),
//                "src/test/resources/data/test/PassiveExpiringMap.fullCollection.version4.obj");
//    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    public Map<Integer, String> makeDecoratedTestMap() {
        final Map<Integer, String> m = new HashMap<>();
        m.put(Integer.valueOf(1), "one");
        m.put(Integer.valueOf(2), "two");
        m.put(Integer.valueOf(3), "three");
        m.put(Integer.valueOf(4), "four");
        m.put(Integer.valueOf(5), "five");
        m.put(Integer.valueOf(6), "six");
        return new PassiveExpiringMap<>(new TestExpirationPolicy(), m);
    }

    @Override

    public Map<K, V> makeObject() {
        return new PassiveExpiringMap<>();
    }

    @Override
    protected int getIterationBehaviour() {
        return AbstractCollectionTest.UNORDERED;
    }


    public Map<Integer, String> makeTestMap() {
        final Map<Integer, String> m =
                new PassiveExpiringMap<>(new TestExpirationPolicy());
        m.put(Integer.valueOf(1), "one");
        m.put(Integer.valueOf(2), "two");
        m.put(Integer.valueOf(3), "three");
        m.put(Integer.valueOf(4), "four");
        m.put(Integer.valueOf(5), "five");
        m.put(Integer.valueOf(6), "six");
        return m;
    }






    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 10, time = 5) @Measurement(iterations = 10, time = 5)



    public void testDecoratedMap() {
        // entries shouldn't expire
        final Map<Integer, String> m = makeDecoratedTestMap();

        // removing a single item

        m.remove(Integer.valueOf(2));
        // getting an item

        m.get(Integer.valueOf(2));

        // adding a single

        m.put(Integer.valueOf(2), "two");
        m.containsKey(Integer.valueOf(2));
        m.containsValue("two");
        // adding a single, odd item
        m.put(Integer.valueOf(1), "one-one");

    }




    public void testExpiration() {
        validateExpiration(new PassiveExpiringMap<String, String>(500), 500);
        validateExpiration(new PassiveExpiringMap<>(
                new PassiveExpiringMap.ConstantTimeToLiveExpirationPolicy<String, String>(1, TimeUnit.SECONDS)), 1000);
    }


    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 10, time = 5) @Measurement(iterations = 10, time = 5)
    public void testMap() {
        final Map<Integer, String> m = makeTestMap();

        // removing a single item

        m.remove(Integer.valueOf(2));
        // getting an item

        m.get(Integer.valueOf(2));

        // adding a single

        m.put(Integer.valueOf(2), "two");
        m.containsKey(Integer.valueOf(2));
        m.containsValue("two");
        // adding a single, odd item
        m.put(Integer.valueOf(1), "one-one");


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


    }

}
