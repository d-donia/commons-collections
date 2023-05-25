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

import java.util.*;

import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollectionTest;
import org.apache.commons.collections4.functors.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Extension of {@link AbstractNavigableSetTest} for exercising the
 * {@link TransformedNavigableSet} implementation.
 *
 * @since 4.1
 */
public class TransformedNavigableSetTest<E> extends AbstractNavigableSetTest<E> {

    public TransformedNavigableSetTest() {
        super(TransformedNavigableSetTest.class.getSimpleName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public NavigableSet<E> makeObject() {
        return TransformedNavigableSet.transformingNavigableSet(new TreeSet<E>(),
                (Transformer<E, E>) TransformedCollectionTest.NOOP_TRANSFORMER);
    }

    @Override
    @SuppressWarnings("unchecked")
    public NavigableSet<E> makeFullCollection() {
        final NavigableSet<E> set = new TreeSet<>(Arrays.asList(getFullElements()));
        return TransformedNavigableSet.transformingNavigableSet(set,
                (Transformer<E, E>) TransformedCollectionTest.NOOP_TRANSFORMER);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testTransformedSet() {
        final NavigableSet<E> set = TransformedNavigableSet.transformingNavigableSet(new TreeSet<E>(),
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
        final Set<?> set = TransformedSet.transformedSet(originalSet,
                TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(els.length, set.size());
        for (final Object el : els) {
            assertTrue(set.contains(Integer.valueOf((String) el)));
        }

        assertTrue(set.remove(Integer.valueOf((String) els[0])));
    }

    @Override
    public String getCompatibilityVersion() {
        return "4.1";
    }

//    public void testCreate() throws Exception {
//        resetEmpty();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/TransformedNavigableSet.emptyCollection.version4.1.obj");
//        resetFull();
//        writeExternalFormToDisk((java.io.Serializable) getCollection(), "src/test/resources/data/test/TransformedNavigableSet.fullCollection.version4.1.obj");
//    }
    @Test
    @Timeout(4000)
     void test00()  throws Throwable  {
        TreeSet<TreeSet<Object>> treeSet0 = new TreeSet<TreeSet<Object>>();
        TreeSet<Object> treeSet1 = new TreeSet<Object>();
        ConstantTransformer<Object, TreeSet<Object>> constantTransformer0 = new ConstantTransformer<Object, TreeSet<Object>>(treeSet1);
        TransformedNavigableSet<TreeSet<Object>> transformedNavigableSet0 = TransformedNavigableSet.transformingNavigableSet((NavigableSet<TreeSet<Object>>) treeSet0, (Transformer<? super TreeSet<Object>, ? extends TreeSet<Object>>) constantTransformer0);
        // Undeclared exception!
        try {
            transformedNavigableSet0.subSet(treeSet1, false, treeSet1, true);
            fail("Expecting exception: ClassCastException");

        } catch(ClassCastException e) {
            //
            // java.util.TreeSet cannot be cast to java.lang.Comparable
            //
          assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
     void test01()  throws Throwable  {
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        Transformer<Object, Integer> transformer0 = InvokerTransformer.invokerTransformer("");
        TransformedNavigableSet<Integer> transformedNavigableSet0 = new TransformedNavigableSet<Integer>(treeSet0, transformer0);
        NavigableSet<Integer> navigableSet0 = transformedNavigableSet0.decorated();
        assertEquals(0, navigableSet0.size());
    }

    @Test
    @Timeout(4000)
     void test02()  throws Throwable  {
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        Transformer<Object, Object> transformer0 = InvokerTransformer.invokerTransformer("");
        TransformedNavigableSet<Object> transformedNavigableSet0 = new TransformedNavigableSet<Object>(treeSet0, transformer0);
        Integer integer0 = new Integer(3283);
        NavigableSet<Object> navigableSet0 = transformedNavigableSet0.tailSet((Object) integer0, false);
        assertEquals(0, navigableSet0.size());
    }

    @Test
    @Timeout(4000)
     void test03()  throws Throwable  {
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        Transformer<Object, Integer> transformer0 = InvokerTransformer.invokerTransformer("7i@O");
        TransformedNavigableSet<Object> transformedNavigableSet0 = TransformedNavigableSet.transformingNavigableSet((NavigableSet<Object>) treeSet0, (Transformer<? super Object, ?>) transformer0);
        Integer integer0 = new Integer(4);
        NavigableSet<Object> navigableSet0 = transformedNavigableSet0.headSet((Object) integer0, true);
        assertEquals(0, navigableSet0.size());
    }

    @Test
    @Timeout(4000)
     void test04()  throws Throwable  {
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        treeSet0.add("7i@O");
        Transformer<Object, Integer> transformer0 = InvokerTransformer.invokerTransformer("7i@O");
        TransformedNavigableSet<Object> transformedNavigableSet0 = TransformedNavigableSet.transformingNavigableSet((NavigableSet<Object>) treeSet0, (Transformer<? super Object, ?>) transformer0);
        Object object0 = transformedNavigableSet0.ceiling("7i@O");
        assertEquals("7i@O", object0);
    }

    @Test
    @Timeout(4000)
     void test05()  throws Throwable  {
        Transformer<Object, Integer> transformer0 = InvokerTransformer.invokerTransformer("");
        // Undeclared exception!
        try {
            TransformedNavigableSet.transformingNavigableSet((NavigableSet<Object>) null, (Transformer<? super Object, ?>) transformer0);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // collection
            //
            assertTrue(true);

        }
    }
    @Test
    @Timeout(4000)
     void test06()  throws Throwable  {
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        Integer integer0 = new Integer(993);
        treeSet0.add(integer0);
        Transformer<Object, Integer> transformer0 = ExceptionTransformer.exceptionTransformer();
        // Undeclared exception!
        try {
            TransformedNavigableSet.transformedNavigableSet((NavigableSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) transformer0);
            fail("Expecting exception: RuntimeException");

        } catch(RuntimeException e) {
            //
            // ExceptionTransformer invoked
            //
            assertTrue(true);

        }
    }

    @Test
    @Timeout(4000)
     void test07()  throws Throwable  {
        Transformer<Object, Integer> transformer0 = InvokerTransformer.invokerTransformer("7i@O");
        // Undeclared exception!
        try {
            TransformedNavigableSet.transformedNavigableSet((NavigableSet<Integer>) null, (Transformer<? super Integer, ? extends Integer>) transformer0);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // collection
            //
            assertTrue(true);

        }
    }
  /*  @Test
    @Timeout(4000)
    public void test08()  throws Throwable  {
        Integer integer0 = new Integer(1613);
        ConstantTransformer<Object, Integer> constantTransformer0 = new ConstantTransformer<Object, Integer>(integer0);
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        treeSet0.add(integer0);
        Predicate<Object>[] predicateArray0 = (Predicate<Object>[]) Array.newInstance(Predicate.class, 1);
        Comparator<Object> comparator0 = (Comparator<Object>) mock(Comparator.class, new ViolatedAssumptionAnswer());
        doReturn(0).when(comparator0).compare(any() , any());
        ComparatorPredicate.Criterion comparatorPredicate_Criterion0 = ComparatorPredicate.Criterion.GREATER_OR_EQUAL;
        Predicate<Object> predicate0 = ComparatorPredicate.comparatorPredicate((Object) constantTransformer0, comparator0, (ComparatorPredicate.Criterion) comparatorPredicate_Criterion0);
        predicateArray0[0] = predicate0;
        Transformer<Object, Integer>[] transformerArray0 = (Transformer<Object, Integer>[]) Array.newInstance(Transformer.class, 0);
        SwitchTransformer<Integer, Integer> switchTransformer0 = new SwitchTransformer<Integer, Integer>(predicateArray0, transformerArray0, constantTransformer0);
        // Undeclared exception!
        try {
            TransformedNavigableSet.transformedNavigableSet((NavigableSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) switchTransformer0);
            fail("Expecting exception: ArrayIndexOutOfBoundsException");

        } catch(ArrayIndexOutOfBoundsException e) {
            //
            // 0
            //
            verifyException("org.apache.commons.collections4.functors.SwitchTransformer", e);
        }
    }*/

    @Test
    @Timeout(4000)
     void test09()  throws Throwable  {
        TreeSet<TreeSet<Integer>> treeSet0 = new TreeSet<TreeSet<Integer>>();
        Transformer<Object, TreeSet<Integer>> transformer0 = ConstantTransformer.nullTransformer();
        TransformedNavigableSet<TreeSet<Integer>> transformedNavigableSet0 = TransformedNavigableSet.transformingNavigableSet((NavigableSet<TreeSet<Integer>>) treeSet0, (Transformer<? super TreeSet<Integer>, ? extends TreeSet<Integer>>) transformer0);
        TreeSet<Integer> treeSet1 = new TreeSet<Integer>();
        // Undeclared exception!
        try {
            transformedNavigableSet0.tailSet(treeSet1, true);
            fail("Expecting exception: ClassCastException");

        } catch(ClassCastException e) {
            //
            // java.util.TreeSet cannot be cast to java.lang.Comparable
            //
            assertTrue(true);

        }
    }

    @Test
    @Timeout(4000)
     void test10()  throws Throwable  {
        Transformer<Boolean, Boolean> transformer0 = ExceptionTransformer.exceptionTransformer();
        TreeSet<Boolean> treeSet0 = new TreeSet<Boolean>();
        TransformedNavigableSet<Boolean> transformedNavigableSet0 = TransformedNavigableSet.transformingNavigableSet((NavigableSet<Boolean>) treeSet0, (Transformer<? super Boolean, ? extends Boolean>) transformer0);
        // Undeclared exception!
        try {
            transformedNavigableSet0.subSet((Boolean) null, false, (Boolean) null, false);
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
     void test11()  throws Throwable  {
        Integer integer0 = new Integer(0);
        ConstantTransformer<Object, Integer> constantTransformer0 = new ConstantTransformer<Object, Integer>(integer0);
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        TransformedNavigableSet<Integer> transformedNavigableSet0 = TransformedNavigableSet.transformedNavigableSet((NavigableSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) constantTransformer0);
        Integer integer1 = new Integer(2);
        // Undeclared exception!
        try {
            transformedNavigableSet0.subSet(integer1, true, integer0, true);
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
     void test12()  throws Throwable  {
        Integer integer0 = new Integer((-1));
        ConstantTransformer<Object, Integer> constantTransformer0 = new ConstantTransformer<Object, Integer>(integer0);
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        treeSet0.add(integer0);
        TransformedNavigableSet<Integer> transformedNavigableSet0 = new TransformedNavigableSet<Integer>(treeSet0, constantTransformer0);
        // Undeclared exception!
        try {
            transformedNavigableSet0.lower((Integer) null);
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
     void test13()  throws Throwable  {
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        treeSet0.add("7i@O");
        Transformer<Object, Integer> transformer0 = InvokerTransformer.invokerTransformer("7i@O");
        TransformedNavigableSet<Object> transformedNavigableSet0 = TransformedNavigableSet.transformingNavigableSet((NavigableSet<Object>) treeSet0, (Transformer<? super Object, ?>) transformer0);
        Integer integer0 = new Integer(4);
        // Undeclared exception!
        try {
            transformedNavigableSet0.lower(integer0);
            fail("Expecting exception: ClassCastException");

        } catch(ClassCastException e) {
            //
            // no message in exception (getMessage() returned null)
            //
        }
    }

    @Test
    @Timeout(4000)
     void test14()  throws Throwable  {
        Integer integer0 = new Integer((-1));
        ConstantTransformer<Object, Integer> constantTransformer0 = new ConstantTransformer<Object, Integer>(integer0);
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        LinkedHashSet<Predicate<Object>> linkedHashSet0 = new LinkedHashSet<Predicate<Object>>();
        Predicate<Object> predicate0 = OnePredicate.onePredicate((Collection<? extends Predicate<? super Object>>) linkedHashSet0);
        PredicateTransformer<Predicate<Boolean>> predicateTransformer0 = new PredicateTransformer<Predicate<Boolean>>(predicate0);
        AnyPredicate<Boolean> anyPredicate0 = new AnyPredicate<Boolean>((Predicate<? super Boolean>[]) null);
        Boolean boolean0 = predicateTransformer0.transform(anyPredicate0);
        treeSet0.add(boolean0);
        TransformedNavigableSet<Object> transformedNavigableSet0 = TransformedNavigableSet.transformedNavigableSet((NavigableSet<Object>) treeSet0, (Transformer<? super Object, ?>) constantTransformer0);
        // Undeclared exception!
        try {
            transformedNavigableSet0.higher(treeSet0);
            fail("Expecting exception: ClassCastException");

        } catch(ClassCastException e) {
            //
            // java.util.TreeSet cannot be cast to java.lang.Comparable
            //
            assertTrue(true);

        }
    }

    @Test
    @Timeout(4000)
     void test15()  throws Throwable  {
        Transformer<Object, Boolean> transformer0 = ConstantTransformer.nullTransformer();
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        TransformedNavigableSet<Object> transformedNavigableSet0 = TransformedNavigableSet.transformingNavigableSet((NavigableSet<Object>) treeSet0, (Transformer<? super Object, ?>) transformer0);
        // Undeclared exception!
        try {
            transformedNavigableSet0.headSet((Object) transformer0, false);
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
     void test16()  throws Throwable  {
        Integer integer0 = new Integer(0);
        ConstantTransformer<Object, Integer> constantTransformer0 = new ConstantTransformer<Object, Integer>(integer0);
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        treeSet0.add(integer0);
        TransformedNavigableSet<Integer> transformedNavigableSet0 = TransformedNavigableSet.transformedNavigableSet((NavigableSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) constantTransformer0);
        // Undeclared exception!
        try {
            transformedNavigableSet0.floor((Integer) null);
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
     void test17()  throws Throwable  {
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        Integer integer0 = new Integer(0);
        ConstantTransformer<Object, Integer> constantTransformer0 = new ConstantTransformer<Object, Integer>(integer0);
        treeSet0.add(integer0);
        TransformedNavigableSet<Integer> transformedNavigableSet0 = TransformedNavigableSet.transformedNavigableSet((NavigableSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) constantTransformer0);
        // Undeclared exception!
        try {
            transformedNavigableSet0.ceiling((Integer) null);
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
     void test18()  throws Throwable  {
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        TransformedNavigableSet<Object> transformedNavigableSet0 = null;
        try {
            transformedNavigableSet0 = new TransformedNavigableSet<Object>(treeSet0, (Transformer<? super Object, ?>) null);
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
     void test19()  throws Throwable  {
        Integer integer0 = new Integer(1);
        ConstantTransformer<Object, Integer> constantTransformer0 = new ConstantTransformer<Object, Integer>(integer0);
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        treeSet0.add(integer0);
        TransformedNavigableSet<Integer> transformedNavigableSet0 = TransformedNavigableSet.transformedNavigableSet((NavigableSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) constantTransformer0);
        // Undeclared exception!
        try {
            transformedNavigableSet0.higher((Integer) null);
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
     void test20()  throws Throwable  {
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        Integer integer0 = new Integer(0);
        ConstantTransformer<Object, Integer> constantTransformer0 = new ConstantTransformer<Object, Integer>(integer0);
        TransformedNavigableSet<Integer> transformedNavigableSet0 = TransformedNavigableSet.transformedNavigableSet((NavigableSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) constantTransformer0);
        Integer integer1 = transformedNavigableSet0.ceiling((Integer) null);
        assertNull(integer1);
    }

    @Test
    @Timeout(4000)
     void test21()  throws Throwable  {
        Integer integer0 = new Integer(0);
        ConstantTransformer<Object, Integer> constantTransformer0 = new ConstantTransformer<Object, Integer>(integer0);
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        TransformedNavigableSet<Integer> transformedNavigableSet0 = TransformedNavigableSet.transformedNavigableSet((NavigableSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) constantTransformer0);
        Integer integer1 = transformedNavigableSet0.floor((Integer) null);
        assertNull(integer1);
    }

    @Test
    @Timeout(4000)
     void test22()  throws Throwable  {
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        Transformer<Object, Integer> transformer0 = InvokerTransformer.invokerTransformer("7i@O");
        TransformedNavigableSet<Object> transformedNavigableSet0 = TransformedNavigableSet.transformingNavigableSet((NavigableSet<Object>) treeSet0, (Transformer<? super Object, ?>) transformer0);
        NavigableSet<Object> navigableSet0 = transformedNavigableSet0.descendingSet();
        assertFalse(navigableSet0.contains("7i@O"));
    }

    @Test
    @Timeout(4000)
     void test23()  throws Throwable  {
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        Integer integer0 = new Integer(0);
        ConstantTransformer<Object, Integer> constantTransformer0 = new ConstantTransformer<Object, Integer>(integer0);
        TransformedNavigableSet<Integer> transformedNavigableSet0 = TransformedNavigableSet.transformedNavigableSet((NavigableSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) constantTransformer0);
        Integer integer1 = transformedNavigableSet0.lower(integer0);
        assertNull(integer1);
    }

    @Test
    @Timeout(4000)
     void test24()  throws Throwable  {
        Transformer<Boolean, Boolean> transformer0 = CloneTransformer.cloneTransformer();
        TreeSet<Boolean> treeSet0 = new TreeSet<Boolean>();
        TransformedNavigableSet<Boolean> transformedNavigableSet0 = TransformedNavigableSet.transformedNavigableSet((NavigableSet<Boolean>) treeSet0, (Transformer<? super Boolean, ? extends Boolean>) transformer0);
        Boolean boolean0 = transformedNavigableSet0.pollLast();
        assertNull(boolean0);
    }

    @Test
    @Timeout(4000)
     void test25()  throws Throwable  {
        Transformer<Boolean, Boolean> transformer0 = ExceptionTransformer.exceptionTransformer();
        TreeSet<Boolean> treeSet0 = new TreeSet<Boolean>();
        TransformedNavigableSet<Boolean> transformedNavigableSet0 = TransformedNavigableSet.transformingNavigableSet((NavigableSet<Boolean>) treeSet0, (Transformer<? super Boolean, ? extends Boolean>) transformer0);
        // Undeclared exception!
        try {
            transformedNavigableSet0.headSet((Boolean) null, true);
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
     void test26()  throws Throwable  {
        TreeSet<Boolean> treeSet0 = new TreeSet<Boolean>();
        Transformer<Boolean, Boolean> transformer0 = ExceptionTransformer.exceptionTransformer();
        TransformedNavigableSet<Boolean> transformedNavigableSet0 = TransformedNavigableSet.transformingNavigableSet((NavigableSet<Boolean>) treeSet0, (Transformer<? super Boolean, ? extends Boolean>) transformer0);
        // Undeclared exception!
        try {
            transformedNavigableSet0.tailSet((Boolean) null, true);
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
     void test27()  throws Throwable  {
        Integer integer0 = new Integer(0);
        ConstantTransformer<Object, Integer> constantTransformer0 = new ConstantTransformer<Object, Integer>(integer0);
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        TransformedNavigableSet<Integer> transformedNavigableSet0 = TransformedNavigableSet.transformedNavigableSet((NavigableSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) constantTransformer0);
        NavigableSet<Integer> navigableSet0 = transformedNavigableSet0.subSet(integer0, true, integer0, true);
        assertEquals(0, navigableSet0.size());
    }
    @Test
    @Timeout(4000)
     void test28()  throws Throwable  {
        Integer integer0 = new Integer(0);
        ConstantTransformer<Object, Integer> constantTransformer0 = new ConstantTransformer<Object, Integer>(integer0);
        TreeSet<Object> treeSet0 = new TreeSet<Object>();
        TransformedNavigableSet<Object> transformedNavigableSet0 = TransformedNavigableSet.transformedNavigableSet((NavigableSet<Object>) treeSet0, (Transformer<? super Object, ?>) constantTransformer0);
        Iterator<Object> iterator0 = transformedNavigableSet0.descendingIterator();
        assertNotNull(iterator0);
    }

    @Test
    @Timeout(4000)
     void test29()  throws Throwable  {
        Transformer<Boolean, Boolean> transformer0 = CloneTransformer.cloneTransformer();
        TreeSet<Boolean> treeSet0 = new TreeSet<Boolean>();
        TransformedNavigableSet<Boolean> transformedNavigableSet0 = TransformedNavigableSet.transformingNavigableSet((NavigableSet<Boolean>) treeSet0, (Transformer<? super Boolean, ? extends Boolean>) transformer0);
        Boolean boolean0 = transformedNavigableSet0.pollFirst();
        assertNull(boolean0);
    }
    @Test
    @Timeout(4000)
     void test30()  throws Throwable  {
        Integer integer0 = new Integer(1);
        ConstantTransformer<Object, Integer> constantTransformer0 = new ConstantTransformer<Object, Integer>(integer0);
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>();
        TransformedNavigableSet<Integer> transformedNavigableSet0 = TransformedNavigableSet.transformedNavigableSet((NavigableSet<Integer>) treeSet0, (Transformer<? super Integer, ? extends Integer>) constantTransformer0);
        Integer integer1 = transformedNavigableSet0.higher((Integer) null);
        assertNull(integer1);
    }
}
