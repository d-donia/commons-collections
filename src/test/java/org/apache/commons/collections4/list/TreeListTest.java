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
package org.apache.commons.collections4.list;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests
 *
 * @since 3.1
 */
public class TreeListTest<E> extends AbstractListTest<E> {

    public TreeListTest() {
        super(TreeListTest.class.getSimpleName());
    }

//    public static void main(String[] args) {
//        junit.textui.TestRunner.run(suite());
//        System.out.println("         add; toArray; iterator; insert; get; indexOf; remove");
//        System.out.print("   TreeList = ");
//        benchmark(new TreeList());
//        System.out.print("\n  ArrayList = ");
//        benchmark(new java.util.ArrayList());
//        System.out.print("\n LinkedList = ");
//        benchmark(new java.util.LinkedList());
//        System.out.print("\n NodeCachingLinkedList = ");
//        benchmark(new NodeCachingLinkedList());
//    }

    public static void benchmark(final List<? super Integer> l) {
        long startMillis = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            l.add(Integer.valueOf(i));
        }
        System.out.print(System.currentTimeMillis() - startMillis + ";");

        startMillis = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            l.toArray();
        }
        System.out.print(System.currentTimeMillis() - startMillis + ";");

        startMillis = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            final java.util.Iterator<? super Integer> it = l.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        System.out.print(System.currentTimeMillis() - startMillis + ";");

        startMillis = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            final int j = (int) (Math.random() * 100000);
            l.add(j, Integer.valueOf(-j));
        }
        System.out.print(System.currentTimeMillis() - startMillis + ";");

        startMillis = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            final int j = (int) (Math.random() * 110000);
            l.get(j);
        }
        System.out.print(System.currentTimeMillis() - startMillis + ";");

        startMillis = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            final int j = (int) (Math.random() * 100000);
            l.indexOf(Integer.valueOf(j));
        }
        System.out.print(System.currentTimeMillis() - startMillis + ";");

        startMillis = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            final int j = (int) (Math.random() * 100000);
            l.remove(j);
        }
        System.out.print(System.currentTimeMillis() - startMillis + ";");
    }

    @Override
    public TreeList<E> makeObject() {
        return new TreeList<>();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddMultiple() {
        final List<E> l = makeObject();
        l.add((E) "hugo");
        l.add((E) "erna");
        l.add((E) "daniel");
        l.add((E) "andres");
        l.add((E) "harald");
        l.add(0, null);
        assertNull(l.get(0));
        assertEquals("hugo", l.get(1));
        assertEquals("erna", l.get(2));
        assertEquals("daniel", l.get(3));
        assertEquals("andres", l.get(4));
        assertEquals("harald", l.get(5));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testRemove() {
        final List<E> l = makeObject();
        l.add((E) "hugo");
        l.add((E) "erna");
        l.add((E) "daniel");
        l.add((E) "andres");
        l.add((E) "harald");
        l.add(0, null);
        int i = 0;
        assertNull(l.get(i++));
        assertEquals("hugo", l.get(i++));
        assertEquals("erna", l.get(i++));
        assertEquals("daniel", l.get(i++));
        assertEquals("andres", l.get(i++));
        assertEquals("harald", l.get(i++));

        l.remove(0);
        i = 0;
        assertEquals("hugo", l.get(i++));
        assertEquals("erna", l.get(i++));
        assertEquals("daniel", l.get(i++));
        assertEquals("andres", l.get(i++));
        assertEquals("harald", l.get(i++));

        i = 0;
        l.remove(1);
        assertEquals("hugo", l.get(i++));
        assertEquals("daniel", l.get(i++));
        assertEquals("andres", l.get(i++));
        assertEquals("harald", l.get(i++));

        i = 0;
        l.remove(2);
        assertEquals("hugo", l.get(i++));
        assertEquals("daniel", l.get(i++));
        assertEquals("harald", l.get(i++));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testInsertBefore() {
        final List<E> l = makeObject();
        l.add((E) "erna");
        l.add(0, (E) "hugo");
        assertEquals("hugo", l.get(0));
        assertEquals("erna", l.get(1));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testIndexOf() {
        final List<E> l = makeObject();
        l.add((E) "0");
        l.add((E) "1");
        l.add((E) "2");
        l.add((E) "3");
        l.add((E) "4");
        l.add((E) "5");
        l.add((E) "6");
        assertEquals(0, l.indexOf("0"));
        assertEquals(1, l.indexOf("1"));
        assertEquals(2, l.indexOf("2"));
        assertEquals(3, l.indexOf("3"));
        assertEquals(4, l.indexOf("4"));
        assertEquals(5, l.indexOf("5"));
        assertEquals(6, l.indexOf("6"));

        l.set(1, (E) "0");
        assertEquals(0, l.indexOf("0"));

        l.set(3, (E) "3");
        assertEquals(3, l.indexOf("3"));
        l.set(2, (E) "3");
        assertEquals(2, l.indexOf("3"));
        l.set(1, (E) "3");
        assertEquals(1, l.indexOf("3"));
        l.set(0, (E) "3");
        assertEquals(0, l.indexOf("3"));
    }

//    public void testCheck() {
//        List l = makeEmptyList();
//        l.add("A1");
//        l.add("A2");
//        l.add("A3");
//        l.add("A4");
//        l.add("A5");
//        l.add("A6");
//    }

    @Test
    public void testBug35258() {
        final Object objectToRemove = Integer.valueOf(3);

        final List<Integer> treelist = new TreeList<>();
        treelist.add(Integer.valueOf(0));
        treelist.add(Integer.valueOf(1));
        treelist.add(Integer.valueOf(2));
        treelist.add(Integer.valueOf(3));
        treelist.add(Integer.valueOf(4));

        // this cause inconsistency in ListIterator()
        treelist.remove(objectToRemove);

        final ListIterator<Integer> li = treelist.listIterator();
        assertEquals(Integer.valueOf(0), li.next());
        assertEquals(Integer.valueOf(0), li.previous());
        assertEquals(Integer.valueOf(0), li.next());
        assertEquals(Integer.valueOf(1), li.next());
        // this caused error in bug 35258
        assertEquals(Integer.valueOf(1), li.previous());
        assertEquals(Integer.valueOf(1), li.next());
        assertEquals(Integer.valueOf(2), li.next());
        assertEquals(Integer.valueOf(2), li.previous());
        assertEquals(Integer.valueOf(2), li.next());
        assertEquals(Integer.valueOf(4), li.next());
        assertEquals(Integer.valueOf(4), li.previous());
        assertEquals(Integer.valueOf(4), li.next());
        assertFalse(li.hasNext());
    }

    @Test
    public void testBugCollections447() {
        final List<String> treeList = new TreeList<>();
        treeList.add("A");
        treeList.add("B");
        treeList.add("C");
        treeList.add("D");

        final ListIterator<String> li = treeList.listIterator();
        assertEquals("A", li.next());
        assertEquals("B", li.next());

        assertEquals("B", li.previous());

        li.remove(); // Deletes "B"

        // previous() after remove() should move to
        // the element before the one just removed
        assertEquals("A", li.previous());
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testIterationOrder() {
        // COLLECTIONS-433:
        // ensure that the iteration order of elements is correct
        // when initializing the TreeList with another collection

        for (int size = 1; size < 1000; size++) {
            final List<Integer> other = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                other.add(i);
            }
            final TreeList<Integer> l = new TreeList<>(other);
            final ListIterator<Integer> it = l.listIterator();
            int i = 0;
            while (it.hasNext()) {
                final Integer val = it.next();
                assertEquals(i++, val.intValue());
            }

            while (it.hasPrevious()) {
                final Integer val = it.previous();
                assertEquals(--i, val.intValue());
            }
        }
    }

    @Test
    @SuppressWarnings("boxing") // OK in test code
    public void testIterationOrderAfterAddAll() {
        // COLLECTIONS-433:
        // ensure that the iteration order of elements is correct
        // when calling addAll on the TreeList

        // to simulate different cases in addAll, do different runs where
        // the number of elements already in the list and being added by addAll differ

        final int size = 1000;
        for (int i = 0; i < 100; i++) {
            final List<Integer> other = new ArrayList<>(size);
            for (int j = i; j < size; j++) {
                other.add(j);
            }
            final TreeList<Integer> l = new TreeList<>();
            for (int j = 0; j < i; j++) {
                l.add(j);
            }

            l.addAll(other);

            final ListIterator<Integer> it = l.listIterator();
            int cnt = 0;
            while (it.hasNext()) {
                final Integer val = it.next();
                assertEquals(cnt++, val.intValue());
            }

            while (it.hasPrevious()) {
                final Integer val = it.previous();
                assertEquals(--cnt, val.intValue());
            }
        }
    }
  /* @org.junit.Test(
            timeout = 4000L
    )
    public void test00() throws Throwable {
        TreeList var1 = new TreeList();
        TreeList.TreeListIterator var2 = new TreeList.TreeListIterator(var1, 63);
        Consumer var3 = (Consumer)Mockito.mock(Consumer.class, new ViolatedAssumptionAnswer());
        var2.forEachRemaining(var3);
        Assert.assertEquals(63L, (long)var2.nextIndex());
    }
*/
    @org.junit.Test(
            timeout = 4000L
    )
    public void test01() throws Throwable {
        LinkedList var1 = new LinkedList();
        var1.add((Integer)null);
        var1.add((Integer)null);
        var1.add((Integer)null);
        var1.add((Integer)null);
        TreeList var2 = new TreeList(var1);
        var2.remove((Object)null);
        boolean var3 = var2.addAll(var1);
        Assert.assertEquals(7L, (long)var2.size());
        Assert.assertTrue(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test02() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        var1.add(var2);
        var1.add(var2);
        Integer var3 = new Integer(1391);
        var1.add(var3);
        TreeList var4 = new TreeList(var1);
        var4.addAll(var1);
        Integer var5 = new Integer(1391);
        Predicate var6 = Predicate.isEqual(var5);
        var4.removeIf(var6);
        boolean var7 = var4.contains(var3);
        Assert.assertEquals(4L, (long)var4.size());
        Assert.assertFalse(var7);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test03() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.add(0, (Object)null);
        boolean var4 = var3.retainAll(var3);
        Assert.assertEquals(2L, (long)var3.size());
        Assert.assertFalse(var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test04() throws Throwable {
        TreeList var1 = new TreeList();
        Integer var2 = new Integer(0);
        var1.add(0, var2);
        TreeList var3 = new TreeList(var1);
        TreeList.TreeListIterator var4 = new TreeList.TreeListIterator(var3, 0);
        var4.next();
        Assert.assertTrue(var4.hasPrevious());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test05() throws Throwable {
        TreeList var1 = new TreeList();
        TreeList var2 = new TreeList(var1);
        TreeList.TreeListIterator var3 = new TreeList.TreeListIterator(var2, -1);
        var3.checkModCount();
        Assert.assertEquals(-1L, (long)var3.nextIndex());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test06() throws Throwable {
        TreeList var1 = new TreeList();
        Object[] var2 = var1.toArray();
        Assert.assertEquals(0L, (long)var2.length);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test07() throws Throwable {
        TreeList var1 = new TreeList();
        int var2 = var1.size();
        Assert.assertEquals(0L, (long)var2);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test08() throws Throwable {
        TreeList var1 = new TreeList();
        Integer var2 = new Integer(0);
        var1.add(0, var2);
        TreeList var3 = new TreeList(var1);
        int var4 = var3.size();
        Assert.assertFalse(var1.contains(var4));
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test09() throws Throwable {
        LinkedList var1 = new LinkedList();
        TreeList var2 = new TreeList(var1);
        TreeList var3 = new TreeList(var1);
        var3.add(0, var2);
        var3.set(0, (Object)null);
        var3.set(0, (Object)null);
        Assert.assertEquals(1L, (long)var3.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test10() throws Throwable {
        LinkedList var1 = new LinkedList();
        var1.add((Integer)null);
        TreeList var2 = new TreeList(var1);
        var2.remove(0);
        Assert.assertEquals(0L, (long)var2.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test11() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        TreeList var4 = new TreeList(var3);
        var4.set(0, var1);
        LinkedList var5 = (LinkedList)var4.remove(0);
        Assert.assertEquals(1L, (long)var5.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test12() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        ListIterator var4 = var3.listIterator(1);
        Assert.assertTrue(var4.hasPrevious());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test13() throws Throwable {
        TreeList var1 = new TreeList();
        ListIterator var2 = var1.listIterator(0);
        Assert.assertFalse(var2.hasNext());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test14() throws Throwable {
        LinkedList var1 = new LinkedList();
        var1.add((Integer)null);
        TreeList var2 = new TreeList(var1);
        ListIterator var3 = var2.listIterator();
        Assert.assertTrue(var3.hasNext());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test15() throws Throwable {
        TreeList var1 = new TreeList();
        ListIterator var2 = var1.listIterator();
        Assert.assertFalse(var2.hasNext());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test16() throws Throwable {
        TreeList var1 = new TreeList();
        Iterator var2 = var1.iterator();
        Assert.assertNotNull(var2);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test17() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        var1.add(var2);
        Integer var3 = new Integer(1391);
        var1.add(var3);
        TreeList var4 = new TreeList(var1);
        int var5 = var4.indexOf(var3);
        Assert.assertEquals(1L, (long)var5);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test18() throws Throwable {
        LinkedList var1 = new LinkedList();
        var1.add((Integer)null);
        var1.add((Integer)null);
        TreeList var2 = new TreeList(var1);
        Object var3 = var2.get(1);
        Assert.assertNull(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test19() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.set(0, var3);
        var3.get(0);
        Assert.assertFalse(var3.contains(0));
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test20() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        List var4 = var3.subList(0, 3);

        try {
            var3.addAll(var4);
            Assert.fail("Expecting exception: ConcurrentModificationException");
        } catch (ConcurrentModificationException var6) {
           Assert.assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test21() throws Throwable {
        TreeList var1 = new TreeList();

        try {
            var1.addAll((Collection)null);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var3) {
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test22() throws Throwable {
        TreeList var1 = new TreeList();
        TreeList var2 = new TreeList();

        try {
            var2.add(283, var1);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var4) {
            Assert.assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test23() throws Throwable {
        Object var1 = null;
        try {
            new TreeList((Collection)null);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var3) {
            Assert.assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test24() throws Throwable {
        LinkedList var1 = new LinkedList();
        var1.add((Integer)null);
        var1.add((Integer)null);
        var1.add((Integer)null);
        TreeList var2 = new TreeList(var1);
        var2.addAll(var2);
        boolean var3 = var2.remove((Object)null);
        Assert.assertEquals(5L, (long)var2.size());
        Assert.assertTrue(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test25() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = (Integer)var1.pollLast();
        var1.add((Integer)null);
        var1.add((Integer)null);
        TreeList var3 = new TreeList(var1);
        var1.add((Integer)null);
        var1.add((Integer)null);
        var1.add((Integer)null);
        var1.add(var2);
        var1.add((Integer)null);
        boolean var4 = var3.addAll(var1);
        Assert.assertTrue(var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test26() throws Throwable {
        LinkedList var1 = new LinkedList();
        var1.add((Integer)null);
        TreeList var2 = new TreeList(var1);
        var1.add((Integer)null);
        var1.add((Integer)null);
        var2.addAll(var1);
        var2.toArray();
        Assert.assertEquals(4L, (long)var2.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test27() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        int var4 = var3.indexOf(var2);
        Assert.assertEquals(0L, (long)var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test28() throws Throwable {
        LinkedList var1 = new LinkedList();
        TreeList var2 = new TreeList(var1);
        Object var3 = new Object();
        int var4 = var2.indexOf(var3);
        Assert.assertEquals(-1L, (long)var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test29() throws Throwable {
        LinkedList var1 = new LinkedList();
        TreeList var2 = new TreeList(var1);

        try {
            var2.get(1);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var4) {
            Assert.assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test30() throws Throwable {
        TreeList var1 = new TreeList();
        TreeList.TreeListIterator var2 = new TreeList.TreeListIterator(var1, 2005);
        Integer var3 = new Integer(1004);

        try {
            var2.set(var3);
            Assert.fail("Expecting exception: IllegalStateException");
        } catch (IllegalStateException var5) {
            Assert.assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test31() throws Throwable {
        LinkedList<Integer> var1 = new LinkedList<>();
        Integer var2 = new Integer(0);
        var1.add(var2);
        TreeList<Integer> var3 = new TreeList<>(var1);

        Comparator<Integer> var4 = Comparator.comparing(Function.identity());
        var3.sort(var4);
        Assert.assertTrue(var3.contains(0));
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test32() throws Throwable {
        TreeList var1 = new TreeList();
        TreeList.TreeListIterator var2 = new TreeList.TreeListIterator(var1, 0);

        try {
            var2.remove();
            Assert.fail("Expecting exception: IllegalStateException");
        } catch (IllegalStateException var4) {
            Assert.assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test33() throws Throwable {
        TreeList var1 = new TreeList();
        TreeList.TreeListIterator var2 = new TreeList.TreeListIterator(var1, -1889);

        try {
            var2.previous();
            Assert.fail("Expecting exception: NoSuchElementException");
        } catch (NoSuchElementException var4) {
            Assert.assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test34() throws Throwable {
        TreeList var1 = new TreeList();
        TreeList.TreeListIterator var2 = new TreeList.TreeListIterator(var1, 0);

        try {
            var2.next();
            Assert.fail("Expecting exception: NoSuchElementException");
        } catch (NoSuchElementException var4) {
            Assert.assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test35() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        TreeList var3 = new TreeList(var1);
        TreeList.TreeListIterator var4 = new TreeList.TreeListIterator(var3, 0);
        var3.add(var2);

        try {
            var4.add(var2);
            Assert.fail("Expecting exception: ConcurrentModificationException");
        } catch (ConcurrentModificationException var6) {
            Assert.assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test36() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(-1037);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        var1.add(var2);
        var3.addAll(var1);
        boolean var4 = var3.addAll(var1);
        Assert.assertEquals(10L, (long)var3.size());
        Assert.assertTrue(var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test37() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(1);
        var1.add(var2);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        var3.remove(1);
        Assert.assertEquals(5L, (long)var3.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test38() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(1);
        var1.add(var2);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        Object var4 = var3.remove(1);
        Assert.assertEquals(1, var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test39() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(1);
        var1.add(var2);
        var1.add(var2);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        var3.remove(1);
        Assert.assertEquals(7L, (long)var3.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test40() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(1);
        var1.add(var2);
        var1.add(var2);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.remove(1);
        Assert.assertEquals(3L, (long)var3.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test41() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(-318);
        var1.add(var2);
        Integer var3 = new Integer(2887);
        var1.add(var3);
        TreeList var4 = new TreeList(var1);
        var4.addAll(var1);
        Predicate var5 = Predicate.isEqual(var3);
        var4.removeIf(var5);
        var4.remove(1);
        Assert.assertEquals(1L, (long)var4.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test42() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(2);
        var1.add(var2);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        var3.remove(2);
        var3.remove(2);
        Assert.assertEquals(4L, (long)var3.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test43() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        boolean var3 = var1.add(var2);
        var1.add(var2);
        TreeList var4 = new TreeList(var1);
        var4.addAll(var1);
        var4.addAll(var4);
        boolean var5 = var4.addAll(var4);
        Assert.assertTrue(var5 == var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test44() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(2);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        var3.remove(2);
        Assert.assertEquals(3L, (long)var3.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test45() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var1.add(var2);
        var3.addAll(0, var1);
        var3.remove(0);
        Assert.assertEquals(4L, (long)var3.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test46() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        Integer var4 = new Integer(1);
        var3.set(1, var4);
        boolean var5 = var3.retainAll(var1);
        Assert.assertEquals(3L, (long)var3.size());
        Assert.assertTrue(var5);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test47() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        Assert.assertEquals(2L, (long)var3.size());
        Integer var4 = new Integer(1);
        var3.set(1, var4);
        boolean var5 = var3.retainAll(var1);
        Assert.assertEquals(1L, (long)var3.size());
        Assert.assertTrue(var5);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test48() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(-1037);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        var3.remove(0);
        Assert.assertEquals(3L, (long)var3.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test49() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(-1037);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        var3.lastIndexOf(var1);
        Assert.assertEquals(4L, (long)var3.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test50() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        var3.toArray();
        Assert.assertEquals(4L, (long)var3.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test51() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        boolean var4 = var3.contains(var2);
        Assert.assertEquals(4L, (long)var3.size());
        Assert.assertTrue(var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test52() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(-1037);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        int var4 = var3.lastIndexOf(var1);
        Assert.assertEquals(-1L, (long)var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test53() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(-1037);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        TreeList var4 = new TreeList(var3);
        var4.addAll(0, var1);
        Assert.assertEquals(4L, (long)var3.size());
        Assert.assertTrue(var4.contains(-1037));
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test54() throws Throwable {
        TreeList var1 = new TreeList();

        try {
            var1.listIterator(-3075);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var3) {
            Assert.assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test55() throws Throwable {
        LinkedList var1 = new LinkedList();
        TreeList var2 = new TreeList(var1);
        Integer var3 = new Integer(2);
        var1.add(var3);
        boolean var4 = var2.addAll(var1);
        Assert.assertEquals(1L, (long)var2.size());
        Assert.assertTrue(var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test56() throws Throwable {
        TreeList var1 = new TreeList();
        boolean var2 = var1.addAll(var1);
        Assert.assertFalse(var2);
        Assert.assertEquals(0L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test57() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(-1037);
        var1.add(var2);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        var3.addAll(var1);
        var3.addAll(0, var1);
        Assert.assertEquals(6L, (long)var3.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test58() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(-1037);
        var1.add(var2);
        TreeList var3 = new TreeList(var1);
        boolean var4 = var3.addAll(var1);
        Assert.assertEquals(2L, (long)var3.size());
        Assert.assertTrue(var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test59() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        TreeList var3 = new TreeList(var1);
        TreeList.TreeListIterator var4 = new TreeList.TreeListIterator(var3, 0);
        var4.add(var2);
        var4.add(var2);
        Assert.assertEquals(2L, (long)var3.size());
        Assert.assertFalse(var4.hasNext());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test60() throws Throwable {
        TreeList var1 = new TreeList();
        TreeList var2 = new TreeList(var1);
        TreeList.TreeListIterator var3 = new TreeList.TreeListIterator(var2, 0);
        int var4 = var3.previousIndex();
        Assert.assertEquals(-1L, (long)var4);
        Assert.assertEquals(0L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test61() throws Throwable {
        TreeList var1 = new TreeList();
        var1.clear();
        Assert.assertEquals(0L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test62() throws Throwable {
        LinkedList var1 = new LinkedList();
        TreeList var2 = new TreeList(var1);

        try {
            var2.remove(0);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var4) {
            Assert.assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test63() throws Throwable {
        LinkedList var1 = new LinkedList();
        Integer var2 = new Integer(0);
        TreeList var3 = new TreeList(var1);

        try {
            var3.set(1, var2);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var5) {
            Assert.assertTrue(true);
        }

    }
}

