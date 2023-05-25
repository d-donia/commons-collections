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
package org.apache.commons.collections4.set;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollectionTest;
import org.apache.commons.collections4.functors.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Extension of {@link AbstractSortedSetTest} for exercising the {@link TransformedSortedSet}
 * implementation.
 *
 * @since 3.0
 */
public class TransformedSortedSetTest<E> extends AbstractSortedSetTest<E> {

    public TransformedSortedSetTest() {
        super(TransformedSortedSetTest.class.getSimpleName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public SortedSet<E> makeObject() {
        return TransformedSortedSet.transformingSortedSet(new TreeSet<E>(), (Transformer<E, E>) TransformedCollectionTest.NOOP_TRANSFORMER);
    }

    @Override
    @SuppressWarnings("unchecked")
    public SortedSet<E> makeFullCollection() {
        final SortedSet<E> set = new TreeSet<>(Arrays.asList(getFullElements()));
        return TransformedSortedSet.transformingSortedSet(set, (Transformer<E, E>) TransformedCollectionTest.NOOP_TRANSFORMER);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testTransformedSet() {
        final SortedSet<E> set = TransformedSortedSet.transformingSortedSet(new TreeSet<E>(),
                (Transformer<E, E>) TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(0, set.size());
        final E[] els = (E[]) new Object[] { "1", "3", "5", "7", "2", "4", "6" };
        for (int i = 0; i < els.length; i++) {
            set.add(els[i]);
            assertEquals(i + 1, set.size());
            assertTrue(set.contains(Integer.valueOf((String) els[i])));
        }

        assertTrue(set.remove(Integer.valueOf((String) els[0])));
    }

    @Test
    public void testTransformedSet_decorateTransform() {
        final Set<Object> originalSet = new TreeSet<>();
        final Object[] els = {"1", "3", "5", "7", "2", "4", "6"};
        Collections.addAll(originalSet, els);
        final Set<?> set = TransformedSet.transformedSet(originalSet, TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(els.length, set.size());
        for (final Object el : els) {
            assertTrue(set.contains(Integer.valueOf((String) el)));
        }

        assertTrue(set.remove(Integer.valueOf((String) els[0])));
    }

    @Test
    @Timeout(4000)
    public void test00()  throws Throwable  {
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        Transformer<Object, Object> transformer0 = ConstantTransformer.constantTransformer((Object) treeSet0);
        TransformedSortedSet<Object> transformedSortedSet0 = TransformedSortedSet.transformingSortedSet((SortedSet<Object>) treeSet0, (Transformer<? super Object, ?>) transformer0);
        SortedSet<Object> sortedSet0 = transformedSortedSet0.getSortedSet();
        assertTrue(sortedSet0.isEmpty());
    }

    @Test
    @Timeout(4000)
    public void test01()  throws Throwable  {
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        Transformer<Object, Object> transformer0 = ConstantTransformer.constantTransformer((Object) treeSet0);
        TransformedSortedSet<Object> transformedSortedSet0 = TransformedSortedSet.transformingSortedSet((SortedSet<Object>) treeSet0, (Transformer<? super Object, ?>) transformer0);
        Integer integer0 = new Integer((-1642));
        SortedSet<Object> sortedSet0 = transformedSortedSet0.tailSet(integer0);
        assertFalse(sortedSet0.contains((-1642)));
    }

    @Test
    @Timeout(4000)
    public void test04()  throws Throwable  {
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        Class<Object>[] classArray0 = (Class<Object>[]) Array.newInstance(Class.class, 2);
        Transformer<Object, Integer> transformer0 = InvokerTransformer.invokerTransformer("XcBf{qYSGM~O3", (Class<?>[]) classArray0, (Object[]) classArray0);
        TransformedSortedSet<Integer> transformedSortedSet0 = TransformedSortedSet.transformedSortedSet((SortedSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) transformer0);
        Comparator<? super Integer> comparator0 = transformedSortedSet0.comparator();
        assertNull(comparator0);
    }

    @Test
    @Timeout(4000)
    public void test06()  throws Throwable  {
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        // Undeclared exception!
        try {
            TransformedSortedSet.transformingSortedSet((SortedSet<Object>) treeSet0, (Transformer<? super Object, ?>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // transformer
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test07()  throws Throwable  {
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        // Undeclared exception!
        try {
            TransformedSortedSet.transformedSortedSet((SortedSet<Object>) treeSet0, (Transformer<? super Object, ?>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // transformer
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test08()  throws Throwable  {
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        Transformer<Object, Integer> transformer0 = ConstantTransformer.nullTransformer();
        TransformedSortedSet<Integer> transformedSortedSet0 = TransformedSortedSet.transformingSortedSet((SortedSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) transformer0);
        // Undeclared exception!
        try {
            transformedSortedSet0.tailSet((Integer) null);
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
    public void test09()  throws Throwable  {
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        Closure<Integer> closure0 = NOPClosure.nopClosure();
        Integer integer0 = new Integer(3818);
        ClosureTransformer<Integer> closureTransformer0 = new ClosureTransformer<Integer>(closure0);
        TransformedSortedSet<Integer> transformedSortedSet0 = TransformedSortedSet.transformedSortedSet((SortedSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) closureTransformer0);
        // Undeclared exception!
        try {
            transformedSortedSet0.subSet((Integer) null, integer0);
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
    public void test10()  throws Throwable  {
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        Closure<Integer> closure0 = NOPClosure.nopClosure();
        Integer integer0 = new Integer(3794);
        ClosureTransformer<Integer> closureTransformer0 = new ClosureTransformer<Integer>(closure0);
        TransformedSortedSet<Integer> transformedSortedSet0 = TransformedSortedSet.transformedSortedSet((SortedSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) closureTransformer0);
        Integer integer1 = new Integer(2147483645);
        // Undeclared exception!
        try {
            transformedSortedSet0.subSet(integer1, integer0);
            fail("Expecting exception: IllegalArgumentException");

        } catch(IllegalArgumentException e) {
            //
            // fromKey > toKey
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test11()  throws Throwable  {
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        Transformer<Object, Object> transformer0 = ConstantTransformer.constantTransformer((Object) treeSet0);
        TransformedSortedSet<Object> transformedSortedSet0 = TransformedSortedSet.transformingSortedSet((SortedSet<Object>) treeSet0, (Transformer<? super Object, ?>) transformer0);
        // Undeclared exception!
        try {
            transformedSortedSet0.subSet(transformer0, treeSet0);
            fail("Expecting exception: ClassCastException");

        } catch(ClassCastException e) {
            //
            // org.apache.commons.collections4.functors.ConstantTransformer cannot be cast to java.lang.Comparable
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test12()  throws Throwable  {
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        Transformer<Object, Object> transformer0 = ConstantTransformer.constantTransformer((Object) treeSet0);
        TransformedSortedSet<Object> transformedSortedSet0 = TransformedSortedSet.transformingSortedSet((SortedSet<Object>) treeSet0, (Transformer<? super Object, ?>) transformer0);
        Object object0 = new Object();
        // Undeclared exception!
        try {
            transformedSortedSet0.headSet(object0);
            fail("Expecting exception: ClassCastException");

        } catch(ClassCastException e) {
            //
            // java.lang.Object cannot be cast to java.lang.Comparable
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test13()  throws Throwable  {
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        TransformedSortedSet<Integer> transformedSortedSet0 = null;
        try {
            transformedSortedSet0 = new TransformedSortedSet<Integer>(treeSet0, (Transformer<? super Integer, ? extends Integer>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // transformer
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test16()  throws Throwable  {
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        Closure<Integer> closure0 = NOPClosure.nopClosure();
        Integer integer0 = new Integer(3818);
        ClosureTransformer<Integer> closureTransformer0 = new ClosureTransformer<Integer>(closure0);
        TransformedSortedSet<Integer> transformedSortedSet0 = TransformedSortedSet.transformedSortedSet((SortedSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) closureTransformer0);
        SortedSet<Integer> sortedSet0 = transformedSortedSet0.headSet(integer0);
        assertTrue(sortedSet0.isEmpty());
    }

    @Test
    @Timeout(4000)
    public void test20()  throws Throwable  {
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        Closure<Integer> closure0 = NOPClosure.nopClosure();
        ClosureTransformer<Integer> closureTransformer0 = new ClosureTransformer<Integer>(closure0);
        TransformedSortedSet<Integer> transformedSortedSet0 = TransformedSortedSet.transformedSortedSet((SortedSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) closureTransformer0);
        // Undeclared exception!
        try {
            transformedSortedSet0.headSet((Integer) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertTrue(true);
        }
    }

    @Override
    public String getCompatibilityVersion() {
        return "4";
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/TransformedSortedSet.emptyCollection.version4.obj");
//        resetFull();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/TransformedSortedSet.fullCollection.version4.obj");
//    }

}
