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
package org.apache.commons.collections4.map;

import org.apache.commons.collections4.MapIterator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * JUnit tests.
 *
 */
public class HashedMapTest<K, V> extends AbstractIterableMapTest<K, V> {

    public HashedMapTest() {
        super(HashedMapTest.class.getSimpleName());
    }

    @Override
    public HashedMap<K, V> makeObject() {
        return new HashedMap<>();
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testClone() {
        final HashedMap<K, V> map = new HashedMap<>(10);
        map.put((K) "1", (V) "1");
        final HashedMap<K, V> cloned = map.clone();
        assertEquals(map.size(), cloned.size());
        assertSame(map.get("1"), cloned.get("1"));
    }

    @Test
    public void testInternalState() {
        final HashedMap<Integer, Integer> map = new HashedMap<>(42, 0.75f);
        assertEquals(0.75f, map.loadFactor, 0.1f);
        assertEquals(0, map.size);
        assertEquals(64, map.data.length);
        assertEquals(48, map.threshold);
        assertEquals(0, map.modCount);

        // contract: the capacity is ensured when too many elements are added
        final HashedMap<Integer, Integer> tmpMap = new HashedMap<>();
        // we need to put at least the "threshold" number of elements
        // in order to double the capacity
        for (int i = 1; i <= map.threshold; i++) {
            tmpMap.put(i, i);
        }
        map.putAll(tmpMap);
        // the threshold has changed due to calling ensureCapacity
        assertEquals(96, map.threshold);
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk((java.io.Serializable) map, "src/test/resources/data/test/HashedMap.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk((java.io.Serializable) map, "src/test/resources/data/test/HashedMap.fullCollection.version4.obj");
//    }

    /**
     * Test for <a href="https://issues.apache.org/jira/browse/COLLECTIONS-323">COLLECTIONS-323</a>.
     */
    @Test
    public void testInitialCapacityZero() {
        final HashedMap<String, String> map = new HashedMap<>(0);
        assertEquals(1, map.data.length);
    }

    @Test
    @Timeout(4000)
    public void test000()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(1);
        Integer integer0 = new Integer(1);
        Integer integer1 = new Integer(1043);
        abstractHashedMap0.putIfAbsent(integer1, integer0);
        abstractHashedMap0.hashCode();
        assertEquals(1, abstractHashedMap0.size());
    }

    @Test
    @Timeout(4000)
    public void test001()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(1);
        abstractHashedMap0.ensureCapacity(1);
        assertEquals(0, abstractHashedMap0.size());
    }

    @Test
    @Timeout(4000)
    public void test002()  throws Throwable  {
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, BufferedInputStream> abstractHashedMap0 = null;
        try {
            abstractHashedMap0 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, BufferedInputStream>(31, (-259));
            fail("Expecting exception: IllegalArgumentException");

        } catch(IllegalArgumentException e) {
            //
            // Load factor must be greater than 0
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test003()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(1);
        Integer integer0 = new Integer(1);
        abstractHashedMap0.putIfAbsent(integer0, integer0);
        AbstractHashedMap.ValuesIterator<Integer> abstractHashedMap_ValuesIterator0 = new AbstractHashedMap.ValuesIterator<Integer>(abstractHashedMap0);
        abstractHashedMap_ValuesIterator0.next();
        assertEquals(1, abstractHashedMap0.size());
    }

    @Test
    @Timeout(4000)
    public void test004()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(12, 12, 12);
        abstractHashedMap0.clear();
        assertTrue(abstractHashedMap0.isEmpty());
    }

    @Test
    @Timeout(4000)
    public void test005()  throws Throwable  {
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Integer> abstractHashedMap0 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Integer>(404, 404);
        abstractHashedMap0.checkCapacity();
        assertTrue(abstractHashedMap0.isEmpty());
    }

    @Test
    @Timeout(4000)
    public void test006()  throws Throwable  {
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Map.Entry<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Map.Entry<Integer, Integer>>(1);
        AbstractHashedMap.EntrySetIterator<AbstractHashedMap<Integer, Integer>, Map.Entry<Integer, Integer>> abstractHashedMap_EntrySetIterator0 = new AbstractHashedMap.EntrySetIterator<AbstractHashedMap<Integer, Integer>, Map.Entry<Integer, Integer>>(abstractHashedMap0);
        // Undeclared exception!
        try {
            abstractHashedMap_EntrySetIterator0.nextEntry();
            fail("Expecting exception: NoSuchElementException");

        } catch(NoSuchElementException e) {
            //
            // No next() entry in the iteration
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test008()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(428, 0.0F, 428);
        AbstractHashedMap.EntrySetIterator<Integer, Integer> abstractHashedMap_EntrySetIterator0 = new AbstractHashedMap.EntrySetIterator<Integer, Integer>(abstractHashedMap0);
        // Undeclared exception!
        try {
            abstractHashedMap_EntrySetIterator0.next();
            fail("Expecting exception: NoSuchElementException");

        } catch(NoSuchElementException e) {
            //
            // No next() entry in the iteration
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test009()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>();
        abstractHashedMap0.init();
        assertEquals(0, abstractHashedMap0.size());
    }

    @Test
    @Timeout(4000)
    public void test010()  throws Throwable  {
        AbstractHashedMap<Map.Entry<Object, Integer>, Object> abstractHashedMap0 = new AbstractHashedMap<Map.Entry<Object, Integer>, Object>(12, 12, 1765);
        AbstractHashedMap.KeySet<Map.Entry<Object, Integer>> abstractHashedMap_KeySet0 = new AbstractHashedMap.KeySet<Map.Entry<Object, Integer>>(abstractHashedMap0);
        abstractHashedMap_KeySet0.clear();
        assertEquals(0, abstractHashedMap_KeySet0.size());
    }

    @Test
    @Timeout(4000)
    public void test011()  throws Throwable  {
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Integer> abstractHashedMap0 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Integer>();
        int int0 = abstractHashedMap0.size();
        assertEquals(0, int0);
    }

    @Test
    @Timeout(4000)
    public void test012()  throws Throwable  {
        Integer integer0 = new Integer(129);
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(1, 23.0F);
        abstractHashedMap0.putIfAbsent(integer0, integer0);
        abstractHashedMap0.keySet();
        assertEquals(1, abstractHashedMap0.size());
    }

    @Test
    @Timeout(4000)
    public void test013()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(787, 787, 787);
        int int0 = abstractHashedMap0.hashIndex((-338), 1);
        assertEquals(0, int0);
    }

    @Test
    @Timeout(4000)
    public void test014()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(1043);
        int int0 = abstractHashedMap0.hashIndex((-1138), 1043);
        assertEquals(2, int0);
    }

    @Test
    @Timeout(4000)
    public void test015()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(134);
        int int0 = abstractHashedMap0.hashIndex((-1582), (-1582));
        assertEquals((-1584), int0);
    }

    @Test
    @Timeout(4000)
    public void test017()  throws Throwable  {
        AbstractHashedMap<Integer, Object> abstractHashedMap0 = new AbstractHashedMap<Integer, Object>(75);
        AbstractHashedMap.HashMapIterator<Integer, Object> abstractHashedMap_HashMapIterator0 = new AbstractHashedMap.HashMapIterator<Integer, Object>(abstractHashedMap0);
        AbstractHashedMap<Comparable<Integer>, AbstractHashedMap<Integer, Integer>> abstractHashedMap1 = new AbstractHashedMap<Comparable<Integer>, AbstractHashedMap<Integer, Integer>>(64, 64, 7);
        Object object0 = abstractHashedMap1.get(abstractHashedMap_HashMapIterator0);
        assertNull(object0);
    }

    @Test
    @Timeout(4000)
    public void test018()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(64);
        abstractHashedMap0.size = 5;
        Set<Map.Entry<Integer, Integer>> set0 = (Set<Map.Entry<Integer, Integer>>)abstractHashedMap0.entrySet();
        assertFalse(set0.isEmpty());
    }

    @Test
    @Timeout(4000)
    public void test019()  throws Throwable  {
        AbstractHashedMap<Object, Object> abstractHashedMap0 = new AbstractHashedMap<Object, Object>(3646, 3646, 7);
        abstractHashedMap0.createValuesIterator();
        assertTrue(abstractHashedMap0.isEmpty());
    }

    @Test
    @Timeout(4000)
    public void test020()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(428, 0.0F, 428);
        abstractHashedMap0.createEntrySetIterator();
        assertTrue(abstractHashedMap0.isEmpty());
    }

    @Test
    @Timeout(4000)
    public void test021()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(1);
        AbstractHashedMap<AbstractHashedMap<Object, Object>, AbstractHashedMap<Object, Integer>> abstractHashedMap1 = new AbstractHashedMap<AbstractHashedMap<Object, Object>, AbstractHashedMap<Object, Integer>>(1, 0.0F, (-1433));
        AbstractHashedMap abstractHashedMap2 = (AbstractHashedMap)abstractHashedMap1.convertKey(abstractHashedMap0);
        assertEquals(0, abstractHashedMap2.size());
    }

    @Test
    @Timeout(4000)
    public void test022()  throws Throwable  {
        AbstractHashedMap<Object, AbstractHashedMap<Object, Object>> abstractHashedMap0 = new AbstractHashedMap<Object, AbstractHashedMap<Object, Object>>(1, 49.4918F, 1);
        int int0 = abstractHashedMap0.calculateThreshold(1, 1);
        assertEquals(1, int0);
    }

    @Test
    @Timeout(4000)
    public void test023()  throws Throwable  {
        AbstractHashedMap<Comparable<Integer>, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Comparable<Integer>, AbstractHashedMap<Integer, Integer>>(33, 1);
        int int0 = abstractHashedMap0.calculateThreshold((-1), 259.0F);
        assertEquals((-259), int0);
    }

    @Test
    @Timeout(4000)
    public void test024()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>();
        int int0 = abstractHashedMap0.calculateNewCapacity((-510));
        assertEquals(1, int0);
    }

    @Test
    @Timeout(4000)
    public void test025()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(27);
        AbstractHashedMap.HashEntry<Integer, Integer>[] abstractHashedMap_HashEntryArray0 = (AbstractHashedMap.HashEntry<Integer, Integer>[]) Array.newInstance(AbstractHashedMap.HashEntry.class, 0);
        abstractHashedMap0.data = abstractHashedMap_HashEntryArray0;
        // Undeclared exception!
        try {
            abstractHashedMap0.remove((Object) null);
            fail("Expecting exception: ArrayIndexOutOfBoundsException");

        } catch(ArrayIndexOutOfBoundsException e) {
            //
            // -8147762
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test026()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(1);
        // Undeclared exception!
        try {
            abstractHashedMap0.putAll((Map<? extends Integer, ? extends Integer>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test027()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>();
        Integer integer0 = new Integer(27);
        // Undeclared exception!
        try {
            abstractHashedMap0.put(integer0, integer0);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test028()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>();
        Integer integer0 = new Integer(0);
        // Undeclared exception!
        try {
            abstractHashedMap0.isEqualValue((Object) null, integer0);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test029()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>();
        Integer integer0 = new Integer((-140));
        // Undeclared exception!
        try {
            abstractHashedMap0.isEqualKey((Object) null, integer0);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test030()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(0);
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Integer> abstractHashedMap1 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Integer>();
        // Undeclared exception!
        try {
            abstractHashedMap1.getEntry(abstractHashedMap0);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test031()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(0, 2771.604F, 15);
        Object object0 = new Object();
        // Undeclared exception!
        try {
            abstractHashedMap0.getEntry(object0);
            fail("Expecting exception: ArrayIndexOutOfBoundsException");

        } catch(ArrayIndexOutOfBoundsException e) {
            //
            // -8147762
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test032()  throws Throwable  {
        AbstractHashedMap<Object, Comparable<Object>> abstractHashedMap0 = new AbstractHashedMap<Object, Comparable<Object>>(2865);
        AbstractHashedMap<Comparable<Object>, AbstractHashedMap<Integer, Integer>> abstractHashedMap1 = new AbstractHashedMap<Comparable<Object>, AbstractHashedMap<Integer, Integer>>();
        // Undeclared exception!
        try {
            abstractHashedMap1.get(abstractHashedMap0);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test033()  throws Throwable  {
        AbstractHashedMap<Comparable<Integer>, Integer> abstractHashedMap0 = new AbstractHashedMap<Comparable<Integer>, Integer>();
        // Undeclared exception!
        try {
            abstractHashedMap0.entryKey((AbstractHashedMap.HashEntry<Comparable<Integer>, Integer>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test034()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>();
        // Undeclared exception!
        try {
            abstractHashedMap0.ensureCapacity((-1));
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test035()  throws Throwable  {
        AbstractHashedMap<Integer, Object> abstractHashedMap0 = new AbstractHashedMap<Integer, Object>(64);
        // Undeclared exception!
        try {
            abstractHashedMap0.doWriteObject((ObjectOutputStream) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test036()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(129);
        // Undeclared exception!
        try {
            abstractHashedMap0.doReadObject((ObjectInputStream) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test037()  throws Throwable  {
        AbstractHashedMap<Comparable<Integer>, Map.Entry<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Comparable<Integer>, Map.Entry<Integer, Integer>>(16384, 1045.3469F);
        // Undeclared exception!
        abstractHashedMap0.containsValue((Object) null);
    }

    @Test
    @Timeout(4000)
    public void test038()  throws Throwable  {
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Map.Entry<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Map.Entry<Integer, Integer>>();
        // Undeclared exception!
        try {
            abstractHashedMap0.checkCapacity();
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test039()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>();
        Integer integer0 = new Integer(1048);
        // Undeclared exception!
        try {
            abstractHashedMap0.addMapping(1048, 1048, integer0, integer0);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test040()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(27);
        Integer integer0 = new Integer(240);
        AbstractHashedMap<Integer, Integer> abstractHashedMap1 = new AbstractHashedMap<Integer, Integer>();
        // Undeclared exception!
        try {
            abstractHashedMap0.addMapping(32, 27, integer0, abstractHashedMap1);
            fail("Expecting exception: ArrayIndexOutOfBoundsException");

        } catch(ArrayIndexOutOfBoundsException e) {
            //
            // 32
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test041()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = null;
        try {
            abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>((Map<? extends Integer, ? extends AbstractHashedMap<Integer, Integer>>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test042()  throws Throwable  {
        AbstractHashedMap<Object, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = null;
        try {
            abstractHashedMap0 = new AbstractHashedMap<Object, AbstractHashedMap<Integer, Integer>>((-2195), (-2195), (-2195));
            fail("Expecting exception: NegativeArraySizeException");

        } catch(NegativeArraySizeException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test043()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>();
        AbstractHashedMap<Integer, Integer> abstractHashedMap1 = new AbstractHashedMap<Integer, Integer>(1, (-1494.9F), 1043);
        boolean boolean0 = abstractHashedMap1.equals(abstractHashedMap0);
        assertTrue(boolean0);
        assertEquals(0, abstractHashedMap1.size());
    }

    @Test
    @Timeout(4000)
    public void test044()  throws Throwable  {
        AbstractHashedMap<Comparable<Integer>, Map.Entry<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Comparable<Integer>, Map.Entry<Integer, Integer>>(16384, 1045.3469F);
        MapIterator<Comparable<Integer>, Map.Entry<Integer, Integer>> mapIterator0 = abstractHashedMap0.mapIterator();
        assertFalse(mapIterator0.hasNext());
    }

    @Test
    @Timeout(4000)
    public void test045()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(27);
        Integer integer0 = abstractHashedMap0.remove((Object) null);
        assertNull(integer0);
    }

    @Test
    @Timeout(4000)
    public void test046()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(129);
        boolean boolean0 = abstractHashedMap0.containsKey((Object) null);
        assertFalse(boolean0);
    }

    @Test
    @Timeout(4000)
    public void test047()  throws Throwable  {
        AbstractHashedMap<Object, Comparable<Integer>> abstractHashedMap0 = new AbstractHashedMap<Object, Comparable<Integer>>();
        boolean boolean0 = abstractHashedMap0.isEmpty();
        assertTrue(boolean0);
    }

    @Test
    @Timeout(4000)
    public void test048()  throws Throwable  {
        AbstractHashedMap<Integer, Object> abstractHashedMap0 = new AbstractHashedMap<Integer, Object>(75);
        AbstractHashedMap.HashMapIterator<Integer, Object> abstractHashedMap_HashMapIterator0 = new AbstractHashedMap.HashMapIterator<Integer, Object>(abstractHashedMap0);
        AbstractHashedMap.HashEntry<Integer, Object> abstractHashedMap_HashEntry0 = new AbstractHashedMap.HashEntry<Integer, Object>((AbstractHashedMap.HashEntry<Integer, Object>) null, 75, abstractHashedMap_HashMapIterator0, (Object) null);
        AbstractMap.SimpleEntry<Integer, Object> abstractMap_SimpleEntry0 = new AbstractMap.SimpleEntry<Integer, Object>(abstractHashedMap_HashEntry0);
    }

    @Test
    @Timeout(4000)
    public void test049()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>();
        // Undeclared exception!
        try {
            abstractHashedMap0.clear();
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test050()  throws Throwable  {
        AbstractHashedMap<Integer, Object> abstractHashedMap0 = new AbstractHashedMap<Integer, Object>();
        String string0 = abstractHashedMap0.toString();
        assertTrue(abstractHashedMap0.isEmpty());
        assertEquals("{}", string0);
    }

    @Test
    @Timeout(4000)
    public void test051()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(12, 12, 12);
        AbstractHashedMap<Integer, Integer> abstractHashedMap1 = new AbstractHashedMap<Integer, Integer>(12);
        Integer integer0 = new Integer(12);
        abstractHashedMap1.putIfAbsent(integer0, integer0);
        abstractHashedMap1.equals(abstractHashedMap0);
        assertEquals(1, abstractHashedMap1.size());
    }

    @Test
    @Timeout(4000)
    public void test052()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(1043);
        boolean boolean0 = abstractHashedMap0.equals(abstractHashedMap0);
        assertTrue(boolean0);
    }

    @Test
    @Timeout(4000)
    public void test054()  throws Throwable  {
        AbstractHashedMap<Comparable<Integer>, Object> abstractHashedMap0 = new AbstractHashedMap<Comparable<Integer>, Object>(3646, 3646, 7);
        AbstractHashedMap.EntrySetIterator<Comparable<Integer>, Object> abstractHashedMap_EntrySetIterator0 = new AbstractHashedMap.EntrySetIterator<Comparable<Integer>, Object>(abstractHashedMap0);
        String string0 = abstractHashedMap_EntrySetIterator0.toString();
        assertEquals("Iterator[]", string0);
    }

    @Test
    @Timeout(4000)
    public void test055()  throws Throwable  {
        HashMap<Integer, Integer> hashMap0 = new HashMap<Integer, Integer>();
        AbstractHashedMap<Comparable<Integer>, Integer> abstractHashedMap0 = new AbstractHashedMap<Comparable<Integer>, Integer>(hashMap0);
        AbstractHashedMap.HashMapIterator<Comparable<Integer>, Integer> abstractHashedMap_HashMapIterator0 = new AbstractHashedMap.HashMapIterator<Comparable<Integer>, Integer>(abstractHashedMap0);
        // Undeclared exception!
        try {
            abstractHashedMap_HashMapIterator0.remove();
            fail("Expecting exception: IllegalStateException");

        } catch(IllegalStateException e) {
            //
            // remove() can only be called once after next()
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test056()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(1);
        abstractHashedMap0.ensureCapacity(48);
        Integer integer0 = new Integer(1);
        abstractHashedMap0.putIfAbsent(integer0, integer0);
        abstractHashedMap0.hashCode();
        assertEquals(1, abstractHashedMap0.size());
    }

    @Test
    @Timeout(4000)
    public void test057()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(0);
        AbstractHashedMap.ValuesIterator<Integer> abstractHashedMap_ValuesIterator0 = new AbstractHashedMap.ValuesIterator<Integer>(abstractHashedMap0);
        Integer integer0 = new Integer(117);
        abstractHashedMap0.putIfAbsent(integer0, integer0);
        // Undeclared exception!
        try {
            abstractHashedMap_ValuesIterator0.next();
            fail("Expecting exception: ConcurrentModificationException");

        } catch(ConcurrentModificationException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test058()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(1);
        Integer integer0 = new Integer(1);
        abstractHashedMap0.putIfAbsent(integer0, integer0);
        AbstractHashedMap.Values<Integer> abstractHashedMap_Values0 = new AbstractHashedMap.Values<Integer>(abstractHashedMap0);
        abstractHashedMap_Values0.iterator();
        assertEquals(1, abstractHashedMap0.size());
    }

    @Test
    @Timeout(4000)
    public void test059()  throws Throwable  {
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, AbstractHashedMap<Integer, Integer>>();
        Collection<AbstractHashedMap<Integer, Integer>> collection0 = abstractHashedMap0.values();
        assertNotNull(collection0);
    }

    @Test
    @Timeout(4000)
    public void test060()  throws Throwable  {
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, AbstractHashedMap<Integer, Integer>>();
        AbstractHashedMap.Values<AbstractHashedMap<Integer, Integer>> abstractHashedMap_Values0 = new AbstractHashedMap.Values<AbstractHashedMap<Integer, Integer>>(abstractHashedMap0);
        abstractHashedMap0.values = abstractHashedMap_Values0;
        Collection<AbstractHashedMap<Integer, Integer>> collection0 = abstractHashedMap0.values();
        assertNotNull(collection0);
    }

    @Test
    @Timeout(4000)
    public void test061()  throws Throwable  {
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, AbstractHashedMap<Integer, Integer>>();
        assertTrue(abstractHashedMap0.isEmpty());

        Iterator<AbstractHashedMap<Integer, Integer>> iterator0 = abstractHashedMap0.createKeySetIterator();
        AbstractHashedMap<Object, Object> abstractHashedMap1 = new AbstractHashedMap<Object, Object>(abstractHashedMap0);
        boolean boolean0 = abstractHashedMap1.equals(iterator0);
        assertFalse(boolean0);
        assertEquals(0, abstractHashedMap1.size());
    }

    @Test
    @Timeout(4000)
    public void test062()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(125);
        AbstractHashedMap<Object, Comparable<Integer>> abstractHashedMap1 = new AbstractHashedMap<Object, Comparable<Integer>>(abstractHashedMap0);
        AbstractHashedMap<Object, Object> abstractHashedMap2 = new AbstractHashedMap<Object, Object>(abstractHashedMap1);
        abstractHashedMap2.keySet();
        Set<Object> set0 = abstractHashedMap2.keySet();
        assertNotNull(set0);
        assertEquals(0, set0.size());
    }

    @Test
    @Timeout(4000)
    public void test063()  throws Throwable  {
        AbstractHashedMap<Integer, Object> abstractHashedMap0 = new AbstractHashedMap<Integer, Object>(64);
        AbstractHashedMap.EntrySet<Integer, Object> abstractHashedMap_EntrySet0 = new AbstractHashedMap.EntrySet<Integer, Object>(abstractHashedMap0);
        Integer integer0 = new Integer(64);
        boolean boolean0 = abstractHashedMap_EntrySet0.remove(integer0);
        assertFalse(boolean0);
    }

    @Test
    @Timeout(4000)
    public void test064()  throws Throwable  {
        AbstractHashedMap<Object, AbstractHashedMap<Integer, Object>> abstractHashedMap0 = new AbstractHashedMap<Object, AbstractHashedMap<Integer, Object>>(5);
        AbstractHashedMap.EntrySet<Object, AbstractHashedMap<Integer, Object>> abstractHashedMap_EntrySet0 = new AbstractHashedMap.EntrySet<Object, AbstractHashedMap<Integer, Object>>(abstractHashedMap0);
        boolean boolean0 = abstractHashedMap_EntrySet0.contains(abstractHashedMap0);
        assertFalse(boolean0);
    }

    @Test
    @Timeout(4000)
    public void test066()  throws Throwable  {
        AbstractHashedMap<Comparable<Integer>, AbstractHashedMap<BufferedInputStream, Integer>> abstractHashedMap0 = new AbstractHashedMap<Comparable<Integer>, AbstractHashedMap<BufferedInputStream, Integer>>(1, 1);
        AbstractHashedMap.HashMapIterator<Comparable<Integer>, AbstractHashedMap<BufferedInputStream, Integer>> abstractHashedMap_HashMapIterator0 = new AbstractHashedMap.HashMapIterator<Comparable<Integer>, AbstractHashedMap<BufferedInputStream, Integer>>(abstractHashedMap0);
        HashMap<BufferedInputStream, Integer> hashMap0 = new HashMap<BufferedInputStream, Integer>();
        AbstractHashedMap<BufferedInputStream, Integer> abstractHashedMap1 = new AbstractHashedMap<BufferedInputStream, Integer>(hashMap0);
        // Undeclared exception!
        try {
            abstractHashedMap_HashMapIterator0.setValue(abstractHashedMap1);
            fail("Expecting exception: IllegalStateException");

        } catch(IllegalStateException e) {
            //
            // setValue() can only be called after next() and before remove()
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test067()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>();
        AbstractHashedMap<Comparable<Integer>, Integer> abstractHashedMap1 = new AbstractHashedMap<Comparable<Integer>, Integer>(abstractHashedMap0);
        AbstractHashedMap.HashMapIterator<Comparable<Integer>, Integer> abstractHashedMap_HashMapIterator0 = new AbstractHashedMap.HashMapIterator<Comparable<Integer>, Integer>(abstractHashedMap1);
        // Undeclared exception!
        try {
            abstractHashedMap_HashMapIterator0.getValue();
            fail("Expecting exception: IllegalStateException");

        } catch(IllegalStateException e) {
            //
            // getValue() can only be called after next() and before remove()
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test068()  throws Throwable  {
        AbstractHashedMap<Integer, BufferedInputStream> abstractHashedMap0 = new AbstractHashedMap<Integer, BufferedInputStream>(1, 1, 1);
        AbstractHashedMap.HashMapIterator<Integer, BufferedInputStream> abstractHashedMap_HashMapIterator0 = new AbstractHashedMap.HashMapIterator<Integer, BufferedInputStream>(abstractHashedMap0);
        // Undeclared exception!
        try {
            abstractHashedMap_HashMapIterator0.getKey();
            fail("Expecting exception: IllegalStateException");

        } catch(IllegalStateException e) {
            //
            // getKey() can only be called after next() and before remove()
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test069()  throws Throwable  {
        AbstractHashedMap<Integer, Object> abstractHashedMap0 = new AbstractHashedMap<Integer, Object>(41);
        Integer integer0 = new Integer(41);
        abstractHashedMap0.put(integer0, integer0);
        abstractHashedMap0.mapIterator();
        assertFalse(abstractHashedMap0.isEmpty());
    }

    @Test
    @Timeout(4000)
    public void test070()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(1);
        Integer integer0 = new Integer(1);
        abstractHashedMap0.put(integer0, integer0);
        abstractHashedMap0.ensureCapacity(36);
        assertEquals(1, abstractHashedMap0.size());
    }

    @Test
    @Timeout(4000)
    public void test071()  throws Throwable  {
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Map.Entry<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Map.Entry<Integer, Integer>>(2659, 2659, 1);
        // Undeclared exception!
        try {
            abstractHashedMap0.removeEntry((AbstractHashedMap.HashEntry<AbstractHashedMap<Integer, Integer>, Map.Entry<Integer, Integer>>) null, 2290, (AbstractHashedMap.HashEntry<AbstractHashedMap<Integer, Integer>, Map.Entry<Integer, Integer>>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test072()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>();
        AbstractHashedMap<Comparable<Integer>, Integer> abstractHashedMap1 = new AbstractHashedMap<Comparable<Integer>, Integer>(abstractHashedMap0);
        AbstractHashedMap.HashMapIterator<Comparable<Integer>, Integer> abstractHashedMap_HashMapIterator0 = new AbstractHashedMap.HashMapIterator<Comparable<Integer>, Integer>(abstractHashedMap1);
        AbstractHashedMap<Object, Object> abstractHashedMap2 = new AbstractHashedMap<Object, Object>(abstractHashedMap1);
        boolean boolean0 = abstractHashedMap0.isEqualValue(abstractHashedMap_HashMapIterator0, abstractHashedMap2);
        assertEquals(0, abstractHashedMap0.size());
        assertFalse(boolean0);
    }

    @Test
    @Timeout(4000)
    public void test073()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>();
        HashMap<Integer, AbstractHashedMap<Integer, Integer>> hashMap0 = new HashMap<Integer, AbstractHashedMap<Integer, Integer>>();
        AbstractHashedMap<Object, Object> abstractHashedMap1 = new AbstractHashedMap<Object, Object>();
        boolean boolean0 = abstractHashedMap1.isEqualValue(hashMap0, abstractHashedMap0);
        assertTrue(boolean0);
    }

    @Test
    @Timeout(4000)
    public void test074()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>();
        boolean boolean0 = abstractHashedMap0.isEqualValue(abstractHashedMap0, abstractHashedMap0);
        assertTrue(boolean0);
    }

    @Test
    @Timeout(4000)
    public void test075()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(1, 1);
        AbstractHashedMap<Integer, Object> abstractHashedMap1 = new AbstractHashedMap<Integer, Object>(abstractHashedMap0);
        boolean boolean0 = abstractHashedMap0.isEqualKey(abstractHashedMap1, abstractHashedMap0);
        assertTrue(boolean0);
        assertEquals(0, abstractHashedMap1.size());
    }

    @Test
    @Timeout(4000)
    public void test076()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(12, 12, 12);
        Integer integer0 = new Integer((-140));
        boolean boolean0 = abstractHashedMap0.isEqualKey(abstractHashedMap0, integer0);
        assertFalse(boolean0);
    }

    @Test
    @Timeout(4000)
    public void test077()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(16);
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, AbstractHashedMap<Integer, Integer>> abstractHashedMap1 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, AbstractHashedMap<Integer, Integer>>();
        boolean boolean0 = abstractHashedMap0.isEqualKey(abstractHashedMap1, abstractHashedMap1);
        assertTrue(boolean0);
    }

    @Test
    @Timeout(4000)
    public void test078()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>();
        // Undeclared exception!
        try {
            abstractHashedMap0.remove((Object) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test079()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(241);
        AbstractHashedMap<Comparable<Integer>, Map.Entry<Integer, Integer>> abstractHashedMap1 = new AbstractHashedMap<Comparable<Integer>, Map.Entry<Integer, Integer>>(241, 241);
        boolean boolean0 = abstractHashedMap1.containsValue(abstractHashedMap0);
        assertFalse(boolean0);
    }

    @Test
    @Timeout(4000)
    public void test080()  throws Throwable  {
        AbstractHashedMap<Comparable<Integer>, Map.Entry<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Comparable<Integer>, Map.Entry<Integer, Integer>>(1, 129);
        boolean boolean0 = abstractHashedMap0.containsValue((Object) null);
        assertFalse(boolean0);
    }

    @Test
    @Timeout(4000)
    public void test081()  throws Throwable  {
        AbstractHashedMap<Comparable<Integer>, Map.Entry<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Comparable<Integer>, Map.Entry<Integer, Integer>>();
        // Undeclared exception!
        try {
            abstractHashedMap0.containsValue((Object) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test082()  throws Throwable  {
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = null;
        try {
            abstractHashedMap0 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, AbstractHashedMap<Integer, Integer>>(0, 0);
            fail("Expecting exception: IllegalArgumentException");

        } catch(IllegalArgumentException e) {
            //
            // Load factor must be greater than 0
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test083()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = null;
        try {
            abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>((-1725));
            fail("Expecting exception: IllegalArgumentException");

        } catch(IllegalArgumentException e) {
            //
            // Initial capacity must be a non negative number
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test084()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(134);
        AbstractHashedMap.KeySet<Integer> abstractHashedMap_KeySet0 = new AbstractHashedMap.KeySet<Integer>(abstractHashedMap0);
        int int0 = abstractHashedMap_KeySet0.size();
        assertEquals(0, int0);
    }

    @Test
    @Timeout(4000)
    public void test085()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(1);
        AbstractHashedMap<Object, AbstractHashedMap<Integer, Object>> abstractHashedMap1 = new AbstractHashedMap<Object, AbstractHashedMap<Integer, Object>>(5);
        AbstractHashedMap.KeySet<Object> abstractHashedMap_KeySet0 = new AbstractHashedMap.KeySet<Object>(abstractHashedMap1);
        boolean boolean0 = abstractHashedMap_KeySet0.remove(abstractHashedMap0);
        assertFalse(boolean0);
    }

    @Test
    @Timeout(4000)
    public void test086()  throws Throwable  {
        AbstractHashedMap.KeySet<Object> abstractHashedMap_KeySet0 = new AbstractHashedMap.KeySet<Object>((AbstractHashedMap<Object, ?>) null);
        Integer integer0 = new Integer((-1297));
        Predicate<Object> predicate0 = Predicate.isEqual((Object) integer0);
        // Undeclared exception!
        try {
            abstractHashedMap_KeySet0.removeIf(predicate0);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test087()  throws Throwable  {
        AbstractHashedMap<Integer, ObjectInputStream> abstractHashedMap0 = new AbstractHashedMap<Integer, ObjectInputStream>();
        AbstractHashedMap<ObjectInputStream, Integer> abstractHashedMap1 = new AbstractHashedMap<ObjectInputStream, Integer>();
        AbstractHashedMap.KeySet<ObjectInputStream> abstractHashedMap_KeySet0 = new AbstractHashedMap.KeySet<ObjectInputStream>(abstractHashedMap1);
        // Undeclared exception!
        try {
            abstractHashedMap_KeySet0.contains(abstractHashedMap0);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test088()  throws Throwable  {
        AbstractHashedMap<Object, Integer> abstractHashedMap0 = new AbstractHashedMap<Object, Integer>(1);
        AbstractHashedMap.KeySetIterator<Object> abstractHashedMap_KeySetIterator0 = new AbstractHashedMap.KeySetIterator<Object>(abstractHashedMap0);
        // Undeclared exception!
        try {
            abstractHashedMap_KeySetIterator0.next();
            fail("Expecting exception: NoSuchElementException");

        } catch(NoSuchElementException e) {
            //
            // No next() entry in the iteration
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test089()  throws Throwable  {
        Integer integer0 = new Integer(1);
        AbstractHashedMap<Object, Comparable<Object>> abstractHashedMap0 = new AbstractHashedMap<Object, Comparable<Object>>();
        AbstractHashedMap.Values<Comparable<Object>> abstractHashedMap_Values0 = new AbstractHashedMap.Values<Comparable<Object>>(abstractHashedMap0);
        // Undeclared exception!
        try {
            abstractHashedMap_Values0.contains(integer0);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test090()  throws Throwable  {
        AbstractHashedMap<Object, Object> abstractHashedMap0 = new AbstractHashedMap<Object, Object>(14, 0.75F);
        AbstractHashedMap.Values<Object> abstractHashedMap_Values0 = new AbstractHashedMap.Values<Object>(abstractHashedMap0);
        abstractHashedMap_Values0.clear();
        assertEquals(0, abstractHashedMap_Values0.size());
    }

    @Test
    @Timeout(4000)
    public void test091()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(10);
        AbstractHashedMap<Comparable<Integer>, Integer> abstractHashedMap1 = new AbstractHashedMap<Comparable<Integer>, Integer>(abstractHashedMap0);
        AbstractHashedMap.HashMapIterator<Comparable<Integer>, Integer> abstractHashedMap_HashMapIterator0 = new AbstractHashedMap.HashMapIterator<Comparable<Integer>, Integer>(abstractHashedMap1);
        // Undeclared exception!
        try {
            abstractHashedMap_HashMapIterator0.next();
            fail("Expecting exception: NoSuchElementException");

        } catch(NoSuchElementException e) {
            //
            // No next() entry in the iteration
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test092()  throws Throwable  {
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Integer> abstractHashedMap0 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Integer>(0);
        Integer integer0 = new Integer(0);
        AbstractHashedMap.HashEntry<AbstractHashedMap<Integer, Integer>, Integer> abstractHashedMap_HashEntry0 = new AbstractHashedMap.HashEntry<AbstractHashedMap<Integer, Integer>, Integer>((AbstractHashedMap.HashEntry<AbstractHashedMap<Integer, Integer>, Integer>) null, 0, integer0, integer0);
        abstractHashedMap0.updateEntry(abstractHashedMap_HashEntry0, integer0);
        assertTrue(abstractHashedMap0.isEmpty());
    }

    @Test
    @Timeout(4000)
    public void test093()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(7557);
        AbstractHashedMap.EntrySet<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap_EntrySet0 = new AbstractHashedMap.EntrySet<Integer, AbstractHashedMap<Integer, Integer>>(abstractHashedMap0);
        abstractHashedMap_EntrySet0.iterator();
        assertTrue(abstractHashedMap0.isEmpty());
    }

    @Test
    @Timeout(4000)
    public void test094()  throws Throwable  {
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Integer> abstractHashedMap0 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Integer>(665, 64);
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Object> abstractHashedMap1 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Object>(abstractHashedMap0);
        AbstractHashedMap.EntrySet<AbstractHashedMap<Integer, Integer>, Object> abstractHashedMap_EntrySet0 = new AbstractHashedMap.EntrySet<AbstractHashedMap<Integer, Integer>, Object>(abstractHashedMap1);
        int int0 = abstractHashedMap_EntrySet0.size();
        assertEquals(0, int0);
    }

    @Test
    @Timeout(4000)
    public void test095()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(1);
        Integer integer0 = new Integer(1);
        AbstractHashedMap<Integer, Integer> abstractHashedMap1 = new AbstractHashedMap<Integer, Integer>();
        abstractHashedMap0.addMapping(0, 0, integer0, abstractHashedMap1);
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap2 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(abstractHashedMap0);
        assertEquals(1, abstractHashedMap0.size());
        assertFalse(abstractHashedMap2.isEmpty());
    }

    @Test
    @Timeout(4000)
    public void test096()  throws Throwable  {
        AbstractHashedMap<Map.Entry<Integer, Integer>, Integer> abstractHashedMap0 = new AbstractHashedMap<Map.Entry<Integer, Integer>, Integer>(0);
        AbstractHashedMap.EntrySet<Map.Entry<Integer, Integer>, Integer> abstractHashedMap_EntrySet0 = new AbstractHashedMap.EntrySet<Map.Entry<Integer, Integer>, Integer>(abstractHashedMap0);
        abstractHashedMap_EntrySet0.clear();
        assertEquals(0, abstractHashedMap_EntrySet0.size());
    }

    @Test
    @Timeout(4000)
    public void test097()  throws Throwable  {
        AbstractHashedMap<ObjectInputStream, Integer> abstractHashedMap0 = new AbstractHashedMap<ObjectInputStream, Integer>();
        // Undeclared exception!
        try {
            abstractHashedMap0.entryHashCode((AbstractHashedMap.HashEntry<ObjectInputStream, Integer>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test098()  throws Throwable  {
        AbstractHashedMap<Comparable<Integer>, Object> abstractHashedMap0 = new AbstractHashedMap<Comparable<Integer>, Object>(3646, 3646, 7);
        AbstractHashedMap.HashEntry<Comparable<Integer>, Object> abstractHashedMap_HashEntry0 = new AbstractHashedMap.HashEntry<Comparable<Integer>, Object>((AbstractHashedMap.HashEntry<Comparable<Integer>, Object>) null, 0, abstractHashedMap0, abstractHashedMap0);
        abstractHashedMap0.removeMapping(abstractHashedMap_HashEntry0, 0, abstractHashedMap_HashEntry0);
        assertFalse(abstractHashedMap0.isEmpty());
    }

    @Test
    @Timeout(4000)
    public void test099()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(59);
        abstractHashedMap0.putAll(abstractHashedMap0);
        assertEquals(0, abstractHashedMap0.size());
    }

    @Test
    @Timeout(4000)
    public void test100()  throws Throwable  {
        AbstractHashedMap<Comparable<Integer>, Comparable<Integer>> abstractHashedMap0 = new AbstractHashedMap<Comparable<Integer>, Comparable<Integer>>(5111);
        // Undeclared exception!
        try {
            abstractHashedMap0.entryValue((AbstractHashedMap.HashEntry<Comparable<Integer>, Comparable<Integer>>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test101()  throws Throwable  {
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, AbstractHashedMap<Integer, Integer>>();
        AbstractHashedMap<Object, Integer> abstractHashedMap1 = new AbstractHashedMap<Object, Integer>(34, 34);
        AbstractHashedMap.HashEntry<Object, Integer> abstractHashedMap_HashEntry0 = abstractHashedMap1.createEntry((AbstractHashedMap.HashEntry<Object, Integer>) null, 34, abstractHashedMap0, (Integer) null);
        assertNotNull(abstractHashedMap_HashEntry0);
    }

    @Test
    @Timeout(4000)
    public void test102()  throws Throwable  {
        AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<Integer, AbstractHashedMap<Integer, Integer>>(0);
        AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Integer> abstractHashedMap1 = new AbstractHashedMap<AbstractHashedMap<Integer, Integer>, Integer>(0);
        AbstractHashedMap.HashEntry<AbstractHashedMap<Integer, Integer>, Integer> abstractHashedMap_HashEntry0 = abstractHashedMap1.getEntry(abstractHashedMap0);
        Integer integer0 = new Integer(0);
        // Undeclared exception!
        try {
            abstractHashedMap1.updateEntry(abstractHashedMap_HashEntry0, integer0);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test103()  throws Throwable  {
        AbstractHashedMap<BufferedInputStream, AbstractHashedMap<Integer, Integer>> abstractHashedMap0 = new AbstractHashedMap<BufferedInputStream, AbstractHashedMap<Integer, Integer>>(1466);
        // Undeclared exception!
        try {
            abstractHashedMap0.entryNext((AbstractHashedMap.HashEntry<BufferedInputStream, AbstractHashedMap<Integer, Integer>>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test104()  throws Throwable  {
        AbstractHashedMap<Integer, Integer> abstractHashedMap0 = new AbstractHashedMap<Integer, Integer>(1043);
        AbstractHashedMap.Values<Integer> abstractHashedMap_Values0 = new AbstractHashedMap.Values<Integer>(abstractHashedMap0);
        int int0 = abstractHashedMap_Values0.size();
        assertEquals(0, int0);
    }

    @Test
    @Timeout(4000)
    public void test106()  throws Throwable  {
        AbstractHashedMap<Comparable<Integer>, Integer> abstractHashedMap0 = new AbstractHashedMap<Comparable<Integer>, Integer>();
        // Undeclared exception!
        try {
            abstractHashedMap0.clone();
            fail("Expecting exception: InternalError");

        } catch(InternalError e) {
            //
            // no message in exception (getMessage() returned null)
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
    public void test107()  throws Throwable  {
        AbstractHashedMap<Integer, Object> abstractHashedMap0 = new AbstractHashedMap<Integer, Object>(7, 0, (-1));
        AbstractHashedMap.HashMapIterator<Integer, Object> abstractHashedMap_HashMapIterator0 = new AbstractHashedMap.HashMapIterator<Integer, Object>(abstractHashedMap0);
        AbstractHashedMap.HashEntry<Integer, Object> abstractHashedMap_HashEntry0 = abstractHashedMap_HashMapIterator0.currentEntry();
        // Undeclared exception!
        try {
            abstractHashedMap0.addEntry(abstractHashedMap_HashEntry0, 18);
            fail("Expecting exception: ArrayIndexOutOfBoundsException");

        } catch(ArrayIndexOutOfBoundsException e) {
            //
            // 18
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
}

}
