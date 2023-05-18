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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import java.util.Comparator;


/**
 * Test case for {@link AbstractLinkedList}.
 */
public abstract class AbstractLinkedListTest<E> extends AbstractListTest<E> {

    public AbstractLinkedListTest(final String testName) {
        super(testName);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testRemoveFirst() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isRemoveSupported()) {
            try {
                list.removeFirst();
            } catch (final UnsupportedOperationException ex) {
            }
        }

        list.addAll(Arrays.asList((E[]) new String[]{"value1", "value2"}));
        assertEquals("value1", list.removeFirst());
        checkNodes();
        list.addLast((E) "value3");
        checkNodes();
        assertEquals("value2", list.removeFirst());
        assertEquals("value3", list.removeFirst());
        checkNodes();
        list.addLast((E) "value4");
        checkNodes();
        assertEquals("value4", list.removeFirst());
        checkNodes();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testRemoveLast() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isRemoveSupported()) {
            try {
                list.removeLast();
            } catch (final UnsupportedOperationException ex) {
            }
        }

        list.addAll(Arrays.asList((E[]) new String[]{"value1", "value2"}));
        assertEquals("value2", list.removeLast());
        list.addFirst((E) "value3");
        checkNodes();
        assertEquals("value1", list.removeLast());
        assertEquals("value3", list.removeLast());
        list.addFirst((E) "value4");
        checkNodes();
        assertEquals("value4", list.removeFirst());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAddNodeAfter() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        if (!isAddSupported()) {
            try {
                list.addFirst(null);
            } catch (final UnsupportedOperationException ex) {
            }
        }

        list.addFirst((E) "value1");
        list.addNodeAfter(list.getNode(0, false), (E) "value2");
        assertEquals("value1", list.getFirst());
        assertEquals("value2", list.getLast());
        list.removeFirst();
        checkNodes();
        list.addNodeAfter(list.getNode(0, false), (E) "value3");
        checkNodes();
        assertEquals("value2", list.getFirst());
        assertEquals("value3", list.getLast());
        list.addNodeAfter(list.getNode(0, false), (E) "value4");
        checkNodes();
        assertEquals("value2", list.getFirst());
        assertEquals("value3", list.getLast());
        assertEquals("value4", list.get(1));
        list.addNodeAfter(list.getNode(2, false), (E) "value5");
        checkNodes();
        assertEquals("value2", list.getFirst());
        assertEquals("value4", list.get(1));
        assertEquals("value3", list.get(2));
        assertEquals("value5", list.getLast());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testRemoveNode() {
        resetEmpty();
        if (!isAddSupported() || !isRemoveSupported()) {
            return;
        }
        final AbstractLinkedList<E> list = getCollection();

        list.addAll(Arrays.asList((E[]) new String[]{"value1", "value2"}));
        list.removeNode(list.getNode(0, false));
        checkNodes();
        assertEquals("value2", list.getFirst());
        assertEquals("value2", list.getLast());
        list.addFirst((E) "value1");
        list.addFirst((E) "value0");
        checkNodes();
        list.removeNode(list.getNode(1, false));
        assertEquals("value0", list.getFirst());
        assertEquals("value2", list.getLast());
        checkNodes();
        list.removeNode(list.getNode(1, false));
        assertEquals("value0", list.getFirst());
        assertEquals("value0", list.getLast());
        checkNodes();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetNode() {
        resetEmpty();
        final AbstractLinkedList<E> list = getCollection();
        // get marker
        assertEquals(list.getNode(0, true).previous, list.getNode(0, true).next);
        assertThrows(IndexOutOfBoundsException.class, () -> list.getNode(0, false),
                "Expecting IndexOutOfBoundsException.");
        list.addAll(Arrays.asList((E[]) new String[]{"value1", "value2"}));
        checkNodes();
        list.addFirst((E) "value0");
        checkNodes();
        list.removeNode(list.getNode(1, false));
        checkNodes();
        assertThrows(IndexOutOfBoundsException.class, () -> list.getNode(2, false),
                "Expecting IndexOutOfBoundsException.");
        assertThrows(IndexOutOfBoundsException.class, () -> list.getNode(-1, false),
                "Expecting IndexOutOfBoundsException.");
        assertThrows(IndexOutOfBoundsException.class, () -> list.getNode(3, true),
                "Expecting IndexOutOfBoundsException.");
    }

    protected void checkNodes() {
        final AbstractLinkedList<E> list = getCollection();
        for (int i = 0; i < list.size; i++) {
            assertEquals(list.getNode(i, false).next, list.getNode(i + 1, true));
            if (i < list.size - 1) {
                assertEquals(list.getNode(i + 1, false).previous,
                        list.getNode(i, false));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractLinkedList<E> getCollection() {
        return (AbstractLinkedList<E>) super.getCollection();
    }


    @org.junit.Test(
            timeout = 4000L
    )
    public void test000() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        AbstractLinkedList.Node var3 = new AbstractLinkedList.Node();
        var1.removeNode(var3);

        try {
            var2.addAll(0, var1);
            Assert.fail("Expecting exception: ConcurrentModificationException");
        } catch (ConcurrentModificationException var5) {
        Assert.assertTrue(true);
        }

    }
    public void test001() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(0);
        var1.add(var2);
        var1.subList(1, 1);
        Assert.assertEquals(1L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test002() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        byte[] var2 = new byte[3];
        ByteArrayInputStream var3 = new ByteArrayInputStream(var2);
        var1.add(var3);
        CursorableLinkedList var4 = new CursorableLinkedList(var1);
        var4.add(var1);
        boolean var5 = var4.retainAll(var1);
        Assert.assertEquals(1L, (long)var1.size());
        Assert.assertTrue(var5);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test003() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList(0);
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(0);
        AbstractLinkedList.LinkedSubList var3 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        var3.add(var2);
        var3.add(var2);
        Assert.assertTrue(var1.contains(var2));
        var3.clear();
        Assert.assertEquals(0L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test004() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();

        try {
            var1.get(2217);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test005() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add(var1);
        var1.add((Object)null);
      //  var1.hashCode();
        Assert.assertFalse(var1.isEmpty());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test006() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add(var1);
        NodeCachingLinkedList var2 = new NodeCachingLinkedList();
        boolean var3 = var2.equals(var1);
        Assert.assertEquals(1L, (long)var1.size());
        Assert.assertFalse(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test007() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        boolean var3 = var2.addAll(0, var1);
        Assert.assertFalse(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test008() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        AbstractLinkedList.Node var2 = var1.createNode((NodeCachingLinkedList)null);
        var1.updateNode(var2, (NodeCachingLinkedList)null);
        Assert.assertTrue(var1.isEmpty());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test009() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList(0);
        AbstractLinkedList.Node var2 = new AbstractLinkedList.Node();
        var1.addNodeAfter(var2, var2);
        Assert.assertEquals(1L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test010() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.Node var2 = new AbstractLinkedList.Node();
        var1.addNode(var2, var2);
        Assert.assertEquals(1L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test011() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList(0);
        CursorableLinkedList var2 = new CursorableLinkedList(var1);
        byte[] var3 = new byte[0];
        ByteArrayInputStream var4 = new ByteArrayInputStream(var3);
        var2.add(0, var4);
        Assert.assertFalse(var2.isEmpty());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test012() throws Throwable {
        AbstractLinkedList.Node var1 = new AbstractLinkedList.Node();
        Object var2 = new Object();
        var1.setValue(var2);
        assertEquals(var2, var1.getValue());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test013() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        AbstractLinkedList.LinkedSubListIterator var3 = new AbstractLinkedList.LinkedSubListIterator(var2, 0);

        try {
            var3.remove();
            Assert.fail("Expecting exception: IllegalStateException");
        } catch (IllegalStateException var5) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test014() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        CursorableLinkedList.Cursor var2 = var1.cursor();
        boolean var3 = var2.hasPrevious();
        Assert.assertFalse(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test015() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        CursorableLinkedList.Cursor var2 = var1.cursor();
        var2.add(var1);
        Assert.assertFalse(var1.isEmpty());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test016() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(var1);
        var2.init();
        Assert.assertEquals(0L, (long)var2.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test017() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add(var1);
        CursorableLinkedList var2 = new CursorableLinkedList(var1);
        var2.toArray();
        Assert.assertEquals(1L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test018() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        Object var2 = new Object();
        var1.add(var2);
        int var3 = var1.size();
        Assert.assertEquals(1L, (long)var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test019() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add(var1);
        byte[] var2 = new byte[4];
        ByteArrayInputStream var3 = new ByteArrayInputStream(var2, 1, -405);
        var1.set(0, var3);
        Assert.assertEquals(1L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test020() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList();
        var1.add(var2);
        var1.remove(0);
        Assert.assertEquals(0L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test021() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(var1);
        ListIterator var3 = var2.listIterator(0);
        Assert.assertFalse(var3.hasPrevious());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test022() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(var1);
        ListIterator var3 = var2.listIterator();
        Assert.assertFalse(var3.hasPrevious());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test023() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        Object var2 = new Object();
        boolean var3 = var1.isEqualValue(var2, var2);
        Assert.assertTrue(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test024() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        Object var2 = new Object();
        boolean var3 = var1.isEqualValue((Object)null, var2);
        Assert.assertFalse(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test025() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        CursorableLinkedList var2 = new CursorableLinkedList(var1);
        boolean var3 = var2.isEmpty();
        Assert.assertTrue(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test026() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.Node var2 = var1.getNode(0, true);
        var1.removeNode(var2);
        int var3 = var1.size();
        Assert.assertFalse(var1.isEmpty());
        Assert.assertEquals(-1L, (long)var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test027() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        var1.add((Object)null);
        var1.getLast();
        Assert.assertEquals(1L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test028() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add((Object)null);
        CursorableLinkedList var2 = new CursorableLinkedList(var1);
        Object var3 = var2.getFirst();
        Assert.assertNull(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test029() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        var1.createSubListIterator(var2);
        Assert.assertTrue(var1.isEmpty());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test030() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        CursorableLinkedList var2 = new CursorableLinkedList();
        AbstractLinkedList.Node var3 = var2.createNode(var1);
        Assert.assertNotNull(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test031() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.Node var2 = var1.createHeaderNode();
        Assert.assertNotNull(var2);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test032() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        LinkedList var2 = new LinkedList();

        try {
            var1.updateNode((AbstractLinkedList.Node)null, var2);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test033() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(var1);

        try {
            var2.toArray((Object[])null);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test034() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        byte[] var2 = new byte[1];
        ByteArrayInputStream var3 = new ByteArrayInputStream(var2);
        var1.add(var3);
        LinkedList[] var4 = (LinkedList[])((LinkedList[])Array.newInstance(LinkedList.class, 2));

        try {
            var1.toArray(var4);
            Assert.fail("Expecting exception: ArrayStoreException");
        } catch (ArrayStoreException var6) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test035() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.Node var2 = new AbstractLinkedList.Node();
        var1.removeNode(var2);

        try {
            var1.toArray();
            Assert.fail("Expecting exception: NegativeArraySizeException");
        } catch (NegativeArraySizeException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test036() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.Node var2 = new AbstractLinkedList.Node();
        DataInputStream var3 = new DataInputStream((InputStream)null);
        var1.add(var3);
        var1.removeNode(var2);

        try {
            var1.toArray();
            Assert.fail("Expecting exception: ArrayIndexOutOfBoundsException");
        } catch (ArrayIndexOutOfBoundsException var5) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test037() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList(0);
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        CursorableLinkedList var3 = new CursorableLinkedList(var2);

        try {
            var3.removeNode((AbstractLinkedList.Node)null);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var5) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test038() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        CursorableLinkedList var2 = new CursorableLinkedList(var1);
        var2.add(var1);

        try {
            var2.removeAll((Collection)null);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var4) {
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test039() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        AbstractLinkedList.Node var3 = new AbstractLinkedList.Node();
        var1.removeNode(var3);

        try {
            var1.removeAll(var2);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var5) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test040() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();

        try {
            var1.listIterator(1133);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test041() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.Node var2 = new AbstractLinkedList.Node();
        var1.removeNode(var2);

        try {
            var1.listIterator();
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test042() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.Node var2 = new AbstractLinkedList.Node();
        var1.removeNode(var2);

        try {
            var1.hashCode();
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test043() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();

        try {
            var1.getNode(0, false);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test044() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList(-3643);

        try {
            var1.doWriteObject((ObjectOutputStream)null);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test045() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();

        try {
            var1.doReadObject((ObjectInputStream)null);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test046() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();

        try {
            var1.createSubListListIterator((AbstractLinkedList.LinkedSubList)null, -1016);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test047() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();

        try {
            var1.createSubListIterator((AbstractLinkedList.LinkedSubList)null);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test048() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        AbstractLinkedList.Node var3 = new AbstractLinkedList.Node();
        var1.removeNode(var3);

        try {
            var1.createSubListIterator(var2);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var5) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test049() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(161);
        AbstractLinkedList.LinkedSubList var3 = new AbstractLinkedList.LinkedSubList(var2, 0, 0);

        try {
            var1.createSubListIterator(var3);
            Assert.fail("Expecting exception: ClassCastException");
        } catch (ClassCastException var5) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test050() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(var1);

        try {
            var2.containsAll((Collection)null);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test051() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.header = null;
        Object var2 = new Object();

        try {
            var1.contains(var2);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var4) {
            assertTrue(true);
        }

    }
    @org.junit.Test(
            timeout = 4000L
    )
    public void test052() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();

        try {
            var1.addNodeBefore((AbstractLinkedList.Node)null, (Object)null);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test053() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        LinkedList var2 = new LinkedList();

        try {
            var1.addNodeAfter((AbstractLinkedList.Node)null, var2);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test054() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();

        try {
            var1.addNode((AbstractLinkedList.Node)null, (AbstractLinkedList.Node)null);
            Assert.fail("Expecting exception: NullPointerException");
        } catch (NullPointerException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test055() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        LinkedList var2 = new LinkedList();

        try {
            var1.addAll(20, var2);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test056() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        Object var2 = new Object();
        boolean var3 = var1.equals(var2);
        Assert.assertFalse(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test057() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        LinkedList var2 = new LinkedList();
        var1.addFirst(var2);
        var1.addAll(0, var1);
        Assert.assertFalse(var1.isEmpty());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test058() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        Object[] var2 = new Object[0];
        Object[] var3 = var1.toArray(var2);
        Assert.assertSame(var3, var2);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test059() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(var1);
        boolean var3 = var2.addFirst((Object)null);
        Assert.assertTrue(var3);
        Object[] var4 = new Object[2];
        Object[] var5 = var2.toArray(var4);
        Assert.assertEquals(2L, (long)var5.length);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test060() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add((LinkedList)null);
        NodeCachingLinkedList var2 = new NodeCachingLinkedList();
        int var3 = var1.indexOf(var2);
        Assert.assertEquals(1L, (long)var1.size());
        Assert.assertEquals(-1L, (long)var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test061() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList(20);
        LinkedList var2 = new LinkedList();
        var1.add(var2);
        int var3 = var1.indexOf(var2);
        Assert.assertEquals(1L, (long)var1.size());
        Assert.assertEquals(0L, (long)var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test062() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        Object var2 = new Object();
        var1.add(var2);
        var1.removeAllNodes();
        Assert.assertTrue(var1.isEmpty());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test063() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList();
        Predicate var3 = Predicate.isEqual(var1);
        boolean var4 = var2.removeIf(var3);
        Assert.assertFalse(var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test064() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(var1);
        boolean var3 = var1.addLast(var2);
        Assert.assertEquals(1L, (long)var1.size());
        Assert.assertTrue(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test065() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        LinkedList var2 = new LinkedList();

        try {
            var1.add(-3937, var2);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test066() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        int var2 = var1.size();
        Assert.assertEquals(0L, (long)var2);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test067() throws Throwable {
        LinkedList var1 = new LinkedList();
        CursorableLinkedList var2 = new CursorableLinkedList(var1);
        Iterator var3 = var2.iterator();
        Assert.assertNotNull(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test068() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        boolean var2 = var1.addAll(var1);
        Assert.assertTrue(var2);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test069() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        var1.add(var2);

        try {
            var1.retainAll(var2);
            Assert.fail("Expecting exception: ConcurrentModificationException");
        } catch (ConcurrentModificationException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test070() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);

        try {
            var2.addAll(-1835, var1);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test071() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        LinkedList var2 = new LinkedList();
        boolean var3 = var1.addFirst(var2);
        NodeCachingLinkedList var4 = new NodeCachingLinkedList(0);
        AbstractLinkedList.LinkedSubList var5 = new AbstractLinkedList.LinkedSubList(var4, 0, 0);
        boolean var6 = var5.addAll(var1);
        Assert.assertTrue(var6 == var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test072() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();

        try {
            var1.subList(2, -2835);
            Assert.fail("Expecting exception: IllegalArgumentException");
        } catch (IllegalArgumentException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test073() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        Object var2 = null;

        try {
            new AbstractLinkedList.LinkedSubList(var1, 964, 1884);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test074() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        CursorableLinkedList.Cursor var2 = var1.cursor();

        try {
            var2.previous();
            Assert.fail("Expecting exception: NoSuchElementException");
        } catch (NoSuchElementException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test075() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        AbstractLinkedList.LinkedSubListIterator var3 = new AbstractLinkedList.LinkedSubListIterator(var2, 0);

        try {
            var3.next();
            Assert.fail("Expecting exception: NoSuchElementException");
        } catch (NoSuchElementException var5) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test076() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.LinkedListIterator var2 = new AbstractLinkedList.LinkedListIterator(var1, 0);

        try {
            var2.remove();
            Assert.fail("Expecting exception: IllegalStateException");
        } catch (IllegalStateException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test077() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        AbstractLinkedList.LinkedSubListIterator var3 = new AbstractLinkedList.LinkedSubListIterator(var2, 0);
        DataInputStream var4 = new DataInputStream((InputStream)null);
        var2.add(var4);

        try {
            var3.previous();
            Assert.fail("Expecting exception: ConcurrentModificationException");
        } catch (ConcurrentModificationException var6) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test078() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add("org.apache.commons.collections4.list.NodeCachingLinkedList");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream var3 = new ObjectOutputStream(outputStream);
        var1.doWriteObject(var3);
        Assert.assertEquals(1L, (long) var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test079() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        AbstractLinkedList.Node var3 = new AbstractLinkedList.Node();
        var1.removeNode(var3);

        try {
            var1.retainAll(var2);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var5) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test080() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add((Object)null);
        var1.add(var1);
        CursorableLinkedList var2 = new CursorableLinkedList(var1);
        String var3 = var2.toString();
        Assert.assertEquals(2L, (long)var1.size());
        Assert.assertEquals("[null, [null, (this Collection)]]", var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test081() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        CursorableLinkedList var2 = new CursorableLinkedList(var1);
        var2.add(var1);
        var2.toString();
        Assert.assertEquals(1L, (long)var2.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test082() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        String var2 = var1.toString();
        Assert.assertTrue(var1.isEmpty());
        Assert.assertEquals("[]", var2);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test083() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        PipedInputStream var2 = new PipedInputStream();
        DataInputStream var3 = new DataInputStream(var2);
        var1.add(var3);
        NodeCachingLinkedList var4 = new NodeCachingLinkedList();
        LinkedList var5 = new LinkedList();
        var4.add(var5);
        boolean var6 = var4.equals(var1);
        Assert.assertEquals(1L, (long)var4.size());
        Assert.assertFalse(var6);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test084() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add(var1);
        CursorableLinkedList var2 = new CursorableLinkedList();
        AbstractLinkedList.Node var3 = new AbstractLinkedList.Node();
        var2.addNodeBefore(var3, var3);
        boolean var4 = var1.equals(var2);
        Assert.assertEquals(1L, (long)var2.size());
        Assert.assertFalse(var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test085() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add(var1);
        CursorableLinkedList var2 = new CursorableLinkedList(var1);
        AbstractLinkedList.Node var3 = new AbstractLinkedList.Node();
        var1.add(var2);
        var2.addNodeBefore(var3, var3);
        boolean var4 = var2.retainAll(var1);
        Assert.assertEquals(2L, (long)var1.size());
        Assert.assertFalse(var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test086() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList();
        var2.add((LinkedList)null);
        boolean var3 = var2.equals(var1);
        Assert.assertEquals(1L, (long)var2.size());
        Assert.assertFalse(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test087() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        boolean var2 = var1.equals(var1);
        Assert.assertTrue(var2);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test088() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();

        try {
            var1.removeLast();
            Assert.fail("Expecting exception: NoSuchElementException");
        } catch (NoSuchElementException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test089() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList(-1148);
        LinkedList var2 = new LinkedList();
        var1.add(var2);
        var1.removeLast();
        Assert.assertTrue(var1.isEmpty());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test090() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList(1702);

        try {
            var1.removeFirst();
            Assert.fail("Expecting exception: NoSuchElementException");
        } catch (NoSuchElementException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test091() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();

        try {
            var1.getLast();
            Assert.fail("Expecting exception: NoSuchElementException");
        } catch (NoSuchElementException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test092() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        Object var2 = new Object();
        var1.add(var2);
        var1.getLast();
        Assert.assertEquals(1L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test093() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();

        try {
            var1.getFirst();
            Assert.fail("Expecting exception: NoSuchElementException");
        } catch (NoSuchElementException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test094() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList();
        var2.add(var1);
        var2.getFirst();
        Assert.assertEquals(1L, (long)var2.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test095() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        CursorableLinkedList var2 = new CursorableLinkedList(var1);
        var2.add(var1);
        boolean var3 = var2.removeAll(var1);
        Assert.assertEquals(1L, (long)var2.size());
        Assert.assertFalse(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test096() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add(var1);
        CursorableLinkedList var2 = new CursorableLinkedList(var1);
        boolean var3 = var2.removeAll(var1);
        Assert.assertEquals(1L, (long)var1.size());
        Assert.assertTrue(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test097() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add(var1);
        AbstractLinkedList.Node var2 = new AbstractLinkedList.Node();
        boolean var3 = var1.remove(var2);
        Assert.assertEquals(1L, (long)var1.size());
        Assert.assertFalse(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test098() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        var1.add(var1);
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(var1);
        boolean var3 = var2.containsAll(var1);
        Assert.assertEquals(1L, (long)var1.size());
        Assert.assertTrue(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test099() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(-1016);
        boolean var3 = var1.add(var2);
        boolean var4 = var2.containsAll(var1);
        Assert.assertFalse(var4 == var3);
        Assert.assertFalse(var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test100() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(2347);
        boolean var3 = var1.add(var2);
        Assert.assertTrue(var3);
        CursorableLinkedList.Cursor var4 = var1.cursor();
        Object var5 = var4.next();
        Assert.assertNotNull(var5);
        int var6 = var1.lastIndexOf(var5);
        Assert.assertEquals(0L, (long)var6);
        Assert.assertFalse(var1.contains(2347));
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test101() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList();
        var2.add((LinkedList)null);
        int var3 = var2.lastIndexOf(var1);
        Assert.assertEquals(1L, (long)var2.size());
        Assert.assertEquals(-1L, (long)var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test102() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        int var2 = var1.lastIndexOf(var1);
        Assert.assertEquals(-1L, (long)var2);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test103() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(var1);
        var1.add(var2);
        NodeCachingLinkedList var3 = new NodeCachingLinkedList();
        boolean var4 = var1.contains(var3);
        Assert.assertEquals(1L, (long)var1.size());
        Assert.assertTrue(var4);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test104() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add(var1);
        Object var2 = new Object();
        boolean var3 = var1.contains(var2);
        Assert.assertEquals(1L, (long)var1.size());
        Assert.assertFalse(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test105() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.Node var2 = new AbstractLinkedList.Node();
        var1.removeNode(var2);

        try {
            var1.toString();
            Assert.fail("Expecting exception: NegativeArraySizeException");
        } catch (NegativeArraySizeException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test106() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList(0);
        AbstractLinkedList.Node var2 = new AbstractLinkedList.Node(var1);
        AbstractLinkedList.Node var3 = var2.getPreviousNode();
        Assert.assertNull(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test107() throws Throwable {
        AbstractLinkedList.Node var1 = new AbstractLinkedList.Node();
        var1.setNextNode((AbstractLinkedList.Node)null);
        Assert.assertNull(var1.getNextNode());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test108() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        AbstractLinkedList.Node var2 = new AbstractLinkedList.Node(var1);
        AbstractLinkedList.Node var3 = var2.getNextNode();
        Assert.assertNull(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test109() throws Throwable {
        AbstractLinkedList.Node var1 = new AbstractLinkedList.Node();
        Object var2 = new Object();
        AbstractLinkedList.Node var3 = new AbstractLinkedList.Node(var1, var1, var2);
        var1.setPreviousNode(var3);
        assertEquals(var1.getPreviousNode(), var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test110() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        AbstractLinkedList.LinkedSubListIterator var3 = new AbstractLinkedList.LinkedSubListIterator(var2, 0);

        try {
            var3.previous();
            Assert.fail("Expecting exception: NoSuchElementException");
        } catch (NoSuchElementException var5) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test111() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.add(var1);
        Comparator<NodeCachingLinkedList> var2 = Comparator.comparingInt(AbstractLinkedList::size);
        var1.sort(var2);
        Assert.assertEquals(1L, (long) var1.size());
    }



    @org.junit.Test(
            timeout = 4000L
    )
    public void test112() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        List var2 = var1.subList(0, 0);
        boolean var3 = var1.equals(var2);
        Assert.assertTrue(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test113() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(var1);
        AbstractLinkedList.LinkedSubList var3 = new AbstractLinkedList.LinkedSubList(var2, 0, 0);

        try {
            var3.remove(230);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var5) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test114() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        List var3 = var2.subList(0, 0);
        Assert.assertEquals(0L, (long)var3.size());
        Assert.assertTrue(var1.isEmpty());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test115() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(var1);
        AbstractLinkedList.LinkedSubList var3 = new AbstractLinkedList.LinkedSubList(var2, 0, 0);

        try {
            var3.get(0);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var5) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test116() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(var1);
        AbstractLinkedList.LinkedSubList var3 = new AbstractLinkedList.LinkedSubList(var2, 0, 0);
        AbstractLinkedList.LinkedSubListIterator var4 = new AbstractLinkedList.LinkedSubListIterator(var3, 0);
        var4.add(var1);
        var3.get(0);
        Assert.assertEquals(1L, (long)var2.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test117() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();

        try {
            var1.subList(-594, 0);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var3) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test118() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(-7);

        try {
            var2.set(-7, var1);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test119() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        var1.clear();
        Assert.assertEquals(0L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test120() throws Throwable {
        LinkedList var1 = new LinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(var1);

        try {
            var2.remove(0);
            Assert.fail("Expecting exception: IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException var4) {
            assertTrue(true);
        }

    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test121() throws Throwable {
        NodeCachingLinkedList var1 = new NodeCachingLinkedList();
        LinkedList var2 = new LinkedList();
        var1.addFirst(var2);
        var1.get(0);
        Assert.assertEquals(1L, (long)var1.size());
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test122() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        Object[] var2 = var1.toArray();
        Assert.assertEquals(0L, (long)var2.length);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test123() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        NodeCachingLinkedList var2 = new NodeCachingLinkedList(2086);
        var2.add(var1);
        Assert.assertFalse(var2.isEmpty());
        boolean var3 = var2.remove(var1);
        Assert.assertTrue(var3);
    }

    @org.junit.Test(
            timeout = 4000L
    )
    public void test124() throws Throwable {
        CursorableLinkedList var1 = new CursorableLinkedList();
        AbstractLinkedList.LinkedSubList var2 = new AbstractLinkedList.LinkedSubList(var1, 0, 0);
        var1.add(var2);
        Assert.assertEquals(1L, (long)var1.size());
        var1.removeFirst();
        Assert.assertEquals(0L, (long)var1.size());
    }
}



