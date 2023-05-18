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

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import org.apache.commons.collections4.list.AbstractLinkedList;
import org.apache.commons.collections4.list.CursorableLinkedList;
import org.apache.commons.collections4.list.NodeCachingLinkedList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockFileOutputStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class AbstractLinkedList_ESTest extends AbstractLinkedList_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test000()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(cursorableLinkedList0, 0, 0);
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      cursorableLinkedList0.removeNode(abstractLinkedList_Node0);
      // Undeclared exception!
      try { 
        abstractLinkedList_LinkedSubList0.addAll(0, (Collection<?>) cursorableLinkedList0);
        fail("Expecting exception: ConcurrentModificationException");
      
      } catch(ConcurrentModificationException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedSubList", e);
      }
  }

  @Test(timeout = 4000)
  public void test001()  throws Throwable  {
      CursorableLinkedList<NodeCachingLinkedList<ByteArrayInputStream>> cursorableLinkedList0 = new CursorableLinkedList<NodeCachingLinkedList<ByteArrayInputStream>>();
      NodeCachingLinkedList<ByteArrayInputStream> nodeCachingLinkedList0 = new NodeCachingLinkedList<ByteArrayInputStream>(0);
      cursorableLinkedList0.add(nodeCachingLinkedList0);
      cursorableLinkedList0.subList(1, 1);
      assertEquals(1, cursorableLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test002()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      byte[] byteArray0 = new byte[3];
      ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
      nodeCachingLinkedList0.add((Object) byteArrayInputStream0);
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>(nodeCachingLinkedList0);
      cursorableLinkedList0.add((Object) nodeCachingLinkedList0);
      boolean boolean0 = cursorableLinkedList0.retainAll(nodeCachingLinkedList0);
      assertEquals(1, nodeCachingLinkedList0.size());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test003()  throws Throwable  {
      NodeCachingLinkedList<NodeCachingLinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<NodeCachingLinkedList<Object>>(0);
      NodeCachingLinkedList<Object> nodeCachingLinkedList1 = new NodeCachingLinkedList<Object>(0);
      AbstractLinkedList.LinkedSubList<NodeCachingLinkedList<Object>> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<NodeCachingLinkedList<Object>>(nodeCachingLinkedList0, 0, 0);
      abstractLinkedList_LinkedSubList0.add(nodeCachingLinkedList1);
      abstractLinkedList_LinkedSubList0.add(nodeCachingLinkedList1);
      assertTrue(nodeCachingLinkedList0.contains(nodeCachingLinkedList1));
      
      abstractLinkedList_LinkedSubList0.clear();
      assertEquals(0, nodeCachingLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test004()  throws Throwable  {
      NodeCachingLinkedList<DataInputStream> nodeCachingLinkedList0 = new NodeCachingLinkedList<DataInputStream>();
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.get(2217);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Couldn't get the node: index (2217) greater than the size of the list (0).
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test005()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) nodeCachingLinkedList0);
      nodeCachingLinkedList0.add((Object) null);
      nodeCachingLinkedList0.hashCode();
      assertFalse(nodeCachingLinkedList0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test006()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) nodeCachingLinkedList0);
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList1 = new NodeCachingLinkedList<LinkedList<Object>>();
      boolean boolean0 = nodeCachingLinkedList1.equals(nodeCachingLinkedList0);
      assertEquals(1, nodeCachingLinkedList0.size());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test007()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(cursorableLinkedList0, 0, 0);
      boolean boolean0 = abstractLinkedList_LinkedSubList0.addAll(0, (Collection<?>) cursorableLinkedList0);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test008()  throws Throwable  {
      NodeCachingLinkedList<NodeCachingLinkedList<DataInputStream>> nodeCachingLinkedList0 = new NodeCachingLinkedList<NodeCachingLinkedList<DataInputStream>>();
      AbstractLinkedList.Node<NodeCachingLinkedList<DataInputStream>> abstractLinkedList_Node0 = nodeCachingLinkedList0.createNode((NodeCachingLinkedList<DataInputStream>) null);
      nodeCachingLinkedList0.updateNode(abstractLinkedList_Node0, (NodeCachingLinkedList<DataInputStream>) null);
      assertTrue(nodeCachingLinkedList0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test009()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>(0);
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      nodeCachingLinkedList0.addNodeAfter(abstractLinkedList_Node0, abstractLinkedList_Node0);
      assertEquals(1, nodeCachingLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test010()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      cursorableLinkedList0.addNode(abstractLinkedList_Node0, abstractLinkedList_Node0);
      assertEquals(1, cursorableLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test011()  throws Throwable  {
      NodeCachingLinkedList<ByteArrayInputStream> nodeCachingLinkedList0 = new NodeCachingLinkedList<ByteArrayInputStream>(0);
      CursorableLinkedList<ByteArrayInputStream> cursorableLinkedList0 = new CursorableLinkedList<ByteArrayInputStream>(nodeCachingLinkedList0);
      byte[] byteArray0 = new byte[0];
      ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
      cursorableLinkedList0.add(0, byteArrayInputStream0);
      assertFalse(cursorableLinkedList0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test012()  throws Throwable  {
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      Object object0 = new Object();
      abstractLinkedList_Node0.setValue(object0);
  }

  @Test(timeout = 4000)
  public void test013()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(cursorableLinkedList0, 0, 0);
      AbstractLinkedList.LinkedSubListIterator<Object> abstractLinkedList_LinkedSubListIterator0 = new AbstractLinkedList.LinkedSubListIterator<Object>(abstractLinkedList_LinkedSubList0, 0);
      // Undeclared exception!
      try { 
        abstractLinkedList_LinkedSubListIterator0.remove();
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedListIterator", e);
      }
  }

  @Test(timeout = 4000)
  public void test014()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      CursorableLinkedList.Cursor<Object> cursorableLinkedList_Cursor0 = cursorableLinkedList0.cursor();
      boolean boolean0 = cursorableLinkedList_Cursor0.hasPrevious();
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test015()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      CursorableLinkedList.Cursor<Object> cursorableLinkedList_Cursor0 = cursorableLinkedList0.cursor();
      cursorableLinkedList_Cursor0.add(cursorableLinkedList0);
      assertFalse(cursorableLinkedList0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test016()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>(cursorableLinkedList0);
      nodeCachingLinkedList0.init();
      assertEquals(0, nodeCachingLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test017()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) nodeCachingLinkedList0);
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>(nodeCachingLinkedList0);
      cursorableLinkedList0.toArray();
      assertEquals(1, nodeCachingLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test018()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      Object object0 = new Object();
      cursorableLinkedList0.add(object0);
      int int0 = cursorableLinkedList0.size();
      assertEquals(1, int0);
  }

  @Test(timeout = 4000)
  public void test019()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) nodeCachingLinkedList0);
      byte[] byteArray0 = new byte[4];
      ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0, (byte)1, (-405));
      nodeCachingLinkedList0.set(0, byteArrayInputStream0);
      assertEquals(1, nodeCachingLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test020()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      NodeCachingLinkedList<DataInputStream> nodeCachingLinkedList0 = new NodeCachingLinkedList<DataInputStream>();
      cursorableLinkedList0.add((Object) nodeCachingLinkedList0);
      cursorableLinkedList0.remove(0);
      assertEquals(0, cursorableLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test021()  throws Throwable  {
      CursorableLinkedList<LinkedList<Object>> cursorableLinkedList0 = new CursorableLinkedList<LinkedList<Object>>();
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>(cursorableLinkedList0);
      ListIterator<LinkedList<Object>> listIterator0 = nodeCachingLinkedList0.listIterator(0);
      assertFalse(listIterator0.hasPrevious());
  }

  @Test(timeout = 4000)
  public void test022()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList1 = new NodeCachingLinkedList<Object>(nodeCachingLinkedList0);
      ListIterator<Object> listIterator0 = nodeCachingLinkedList1.listIterator();
      assertFalse(listIterator0.hasPrevious());
  }

  @Test(timeout = 4000)
  public void test023()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      Object object0 = new Object();
      boolean boolean0 = cursorableLinkedList0.isEqualValue(object0, object0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test024()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      Object object0 = new Object();
      boolean boolean0 = nodeCachingLinkedList0.isEqualValue((Object) null, object0);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test025()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>(nodeCachingLinkedList0);
      boolean boolean0 = cursorableLinkedList0.isEmpty();
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test026()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = cursorableLinkedList0.getNode(0, true);
      cursorableLinkedList0.removeNode(abstractLinkedList_Node0);
      int int0 = cursorableLinkedList0.size();
      assertFalse(cursorableLinkedList0.isEmpty());
      assertEquals((-1), int0);
  }

  @Test(timeout = 4000)
  public void test027()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      cursorableLinkedList0.add((Object) null);
      cursorableLinkedList0.getLast();
      assertEquals(1, cursorableLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test028()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) null);
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>(nodeCachingLinkedList0);
      Object object0 = cursorableLinkedList0.getFirst();
      assertNull(object0);
  }

  @Test(timeout = 4000)
  public void test029()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      AbstractLinkedList.LinkedSubList<LinkedList<Object>> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<LinkedList<Object>>(nodeCachingLinkedList0, 0, 0);
      nodeCachingLinkedList0.createSubListIterator(abstractLinkedList_LinkedSubList0);
      assertTrue(nodeCachingLinkedList0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test030()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = cursorableLinkedList0.createNode(nodeCachingLinkedList0);
      assertNotNull(abstractLinkedList_Node0);
  }

  @Test(timeout = 4000)
  public void test031()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = cursorableLinkedList0.createHeaderNode();
      assertNotNull(abstractLinkedList_Node0);
  }

  @Test(timeout = 4000)
  public void test032()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.updateNode((AbstractLinkedList.Node<LinkedList<Object>>) null, linkedList0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test033()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>(cursorableLinkedList0);
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.toArray((Object[]) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test034()  throws Throwable  {
      NodeCachingLinkedList<ByteArrayInputStream> nodeCachingLinkedList0 = new NodeCachingLinkedList<ByteArrayInputStream>();
      byte[] byteArray0 = new byte[1];
      ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(byteArray0);
      nodeCachingLinkedList0.add(byteArrayInputStream0);
      LinkedList<NodeCachingLinkedList<ByteArrayInputStream>>[] linkedListArray0 = (LinkedList<NodeCachingLinkedList<ByteArrayInputStream>>[]) Array.newInstance(LinkedList.class, 2);
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.toArray(linkedListArray0);
        fail("Expecting exception: ArrayStoreException");
      
      } catch(ArrayStoreException e) {
         //
         // java.io.ByteArrayInputStream
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test035()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      cursorableLinkedList0.removeNode(abstractLinkedList_Node0);
      // Undeclared exception!
      try { 
        cursorableLinkedList0.toArray();
        fail("Expecting exception: NegativeArraySizeException");
      
      } catch(NegativeArraySizeException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test036()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      DataInputStream dataInputStream0 = new DataInputStream((InputStream) null);
      cursorableLinkedList0.add((Object) dataInputStream0);
      cursorableLinkedList0.removeNode(abstractLinkedList_Node0);
      // Undeclared exception!
      try { 
        cursorableLinkedList0.toArray();
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // 0
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test037()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>(0);
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(nodeCachingLinkedList0, 0, 0);
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>(abstractLinkedList_LinkedSubList0);
      // Undeclared exception!
      try { 
        cursorableLinkedList0.removeNode((AbstractLinkedList.Node<Object>) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // node
         //
         verifyException("java.util.Objects", e);
      }
  }

  @Test(timeout = 4000)
  public void test038()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>(nodeCachingLinkedList0);
      cursorableLinkedList0.add((Object) nodeCachingLinkedList0);
      // Undeclared exception!
      try { 
        cursorableLinkedList0.removeAll((Collection<?>) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test039()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(cursorableLinkedList0, 0, 0);
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      cursorableLinkedList0.removeNode(abstractLinkedList_Node0);
      // Undeclared exception!
      try { 
        cursorableLinkedList0.removeAll(abstractLinkedList_LinkedSubList0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Couldn't get the node: index (0) greater than the size of the list (-1).
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test040()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      // Undeclared exception!
      try { 
        cursorableLinkedList0.listIterator(1133);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Couldn't get the node: index (1133) greater than the size of the list (0).
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test041()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      cursorableLinkedList0.removeNode(abstractLinkedList_Node0);
      // Undeclared exception!
      try { 
        cursorableLinkedList0.listIterator();
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Couldn't get the node: index (0) greater than the size of the list (-1).
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test042()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      cursorableLinkedList0.removeNode(abstractLinkedList_Node0);
      // Undeclared exception!
      try { 
        cursorableLinkedList0.hashCode();
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Couldn't get the node: index (0) greater than the size of the list (-1).
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test043()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      try { 
        nodeCachingLinkedList0.getNode(0, false);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Couldn't get the node: index (0) is the size of the list.
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test044()  throws Throwable  {
      NodeCachingLinkedList<NodeCachingLinkedList<DataInputStream>> nodeCachingLinkedList0 = new NodeCachingLinkedList<NodeCachingLinkedList<DataInputStream>>((-3643));
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.doWriteObject((ObjectOutputStream) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test045()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      // Undeclared exception!
      try { 
        cursorableLinkedList0.doReadObject((ObjectInputStream) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test046()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.createSubListListIterator((AbstractLinkedList.LinkedSubList<LinkedList<Object>>) null, (-1016));
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedSubListIterator", e);
      }
  }

  @Test(timeout = 4000)
  public void test047()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      // Undeclared exception!
      try { 
        cursorableLinkedList0.createSubListIterator((AbstractLinkedList.LinkedSubList<Object>) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.CursorableLinkedList$SubCursor", e);
      }
  }

  @Test(timeout = 4000)
  public void test048()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(cursorableLinkedList0, 0, 0);
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      cursorableLinkedList0.removeNode(abstractLinkedList_Node0);
      // Undeclared exception!
      try { 
        cursorableLinkedList0.createSubListIterator(abstractLinkedList_LinkedSubList0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Couldn't get the node: index (0) greater than the size of the list (-1).
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test049()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>(161);
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(nodeCachingLinkedList0, 0, 0);
      // Undeclared exception!
      try { 
        cursorableLinkedList0.createSubListIterator(abstractLinkedList_LinkedSubList0);
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // org.apache.commons.collections4.list.NodeCachingLinkedList cannot be cast to org.apache.commons.collections4.list.CursorableLinkedList
         //
         verifyException("org.apache.commons.collections4.list.CursorableLinkedList$SubCursor", e);
      }
  }

  @Test(timeout = 4000)
  public void test050()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>(cursorableLinkedList0);
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.containsAll((Collection<?>) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test051()  throws Throwable  {
      NodeCachingLinkedList<DataInputStream> nodeCachingLinkedList0 = new NodeCachingLinkedList<DataInputStream>();
      nodeCachingLinkedList0.header = null;
      Object object0 = new Object();
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.contains(object0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test052()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.addNodeBefore((AbstractLinkedList.Node<Object>) null, (Object) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // insertBeforeNode
         //
         verifyException("java.util.Objects", e);
      }
  }

  @Test(timeout = 4000)
  public void test053()  throws Throwable  {
      CursorableLinkedList<LinkedList<Object>> cursorableLinkedList0 = new CursorableLinkedList<LinkedList<Object>>();
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      // Undeclared exception!
      try { 
        cursorableLinkedList0.addNodeAfter((AbstractLinkedList.Node<LinkedList<Object>>) null, linkedList0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test054()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      // Undeclared exception!
      try { 
        cursorableLinkedList0.addNode((AbstractLinkedList.Node<Object>) null, (AbstractLinkedList.Node<Object>) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // nodeToInsert
         //
         verifyException("java.util.Objects", e);
      }
  }

  @Test(timeout = 4000)
  public void test055()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      LinkedList<LinkedList<Object>> linkedList0 = new LinkedList<LinkedList<Object>>();
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.addAll(20, (Collection<? extends LinkedList<Object>>) linkedList0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Couldn't get the node: index (20) greater than the size of the list (0).
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test056()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      Object object0 = new Object();
      boolean boolean0 = cursorableLinkedList0.equals(object0);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test057()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      nodeCachingLinkedList0.addFirst(linkedList0);
      nodeCachingLinkedList0.addAll(0, (Collection<? extends LinkedList<Object>>) nodeCachingLinkedList0);
      assertFalse(nodeCachingLinkedList0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test058()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      Object[] objectArray0 = new Object[0];
      Object[] objectArray1 = nodeCachingLinkedList0.toArray(objectArray0);
      assertSame(objectArray1, objectArray0);
  }

  @Test(timeout = 4000)
  public void test059()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList1 = new NodeCachingLinkedList<Object>(nodeCachingLinkedList0);
      boolean boolean0 = nodeCachingLinkedList1.addFirst((Object) null);
      assertTrue(boolean0);
      
      Object[] objectArray0 = new Object[2];
      Object[] objectArray1 = nodeCachingLinkedList1.toArray(objectArray0);
      assertEquals(2, objectArray1.length);
  }

  @Test(timeout = 4000)
  public void test060()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      nodeCachingLinkedList0.add((LinkedList<Object>) null);
      NodeCachingLinkedList<Object> nodeCachingLinkedList1 = new NodeCachingLinkedList<Object>();
      int int0 = nodeCachingLinkedList0.indexOf(nodeCachingLinkedList1);
      assertEquals(1, nodeCachingLinkedList0.size());
      assertEquals((-1), int0);
  }

  @Test(timeout = 4000)
  public void test061()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>(20);
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      nodeCachingLinkedList0.add(linkedList0);
      int int0 = nodeCachingLinkedList0.indexOf(linkedList0);
      assertEquals(1, nodeCachingLinkedList0.size());
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test062()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      Object object0 = new Object();
      cursorableLinkedList0.add(object0);
      cursorableLinkedList0.removeAllNodes();
      assertTrue(cursorableLinkedList0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test063()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      NodeCachingLinkedList<DataInputStream> nodeCachingLinkedList1 = new NodeCachingLinkedList<DataInputStream>();
      Predicate<Object> predicate0 = Predicate.isEqual((Object) nodeCachingLinkedList0);
      boolean boolean0 = nodeCachingLinkedList1.removeIf(predicate0);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test064()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>(cursorableLinkedList0);
      boolean boolean0 = cursorableLinkedList0.addLast(nodeCachingLinkedList0);
      assertEquals(1, cursorableLinkedList0.size());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test065()  throws Throwable  {
      CursorableLinkedList<LinkedList<Object>> cursorableLinkedList0 = new CursorableLinkedList<LinkedList<Object>>();
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      // Undeclared exception!
      try { 
        cursorableLinkedList0.add((-3937), linkedList0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Couldn't get the node: index (-3937) less than zero.
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test066()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      int int0 = cursorableLinkedList0.size();
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test067()  throws Throwable  {
      LinkedList<DataInputStream> linkedList0 = new LinkedList<DataInputStream>();
      CursorableLinkedList<DataInputStream> cursorableLinkedList0 = new CursorableLinkedList<DataInputStream>(linkedList0);
      Iterator<DataInputStream> iterator0 = cursorableLinkedList0.iterator();
      assertNotNull(iterator0);
  }

  @Test(timeout = 4000)
  public void test068()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      boolean boolean0 = nodeCachingLinkedList0.addAll((Collection<?>) nodeCachingLinkedList0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test069()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(cursorableLinkedList0, 0, 0);
      cursorableLinkedList0.add((Object) abstractLinkedList_LinkedSubList0);
      // Undeclared exception!
      try { 
        cursorableLinkedList0.retainAll(abstractLinkedList_LinkedSubList0);
        fail("Expecting exception: ConcurrentModificationException");
      
      } catch(ConcurrentModificationException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedSubList", e);
      }
  }

  @Test(timeout = 4000)
  public void test070()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(cursorableLinkedList0, 0, 0);
      // Undeclared exception!
      try { 
        abstractLinkedList_LinkedSubList0.addAll((-1835), (Collection<?>) cursorableLinkedList0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Index '-1835' out of bounds for size '0'
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedSubList", e);
      }
  }

  @Test(timeout = 4000)
  public void test071()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      boolean boolean0 = nodeCachingLinkedList0.addFirst(linkedList0);
      NodeCachingLinkedList<Object> nodeCachingLinkedList1 = new NodeCachingLinkedList<Object>(0);
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(nodeCachingLinkedList1, 0, 0);
      boolean boolean1 = abstractLinkedList_LinkedSubList0.addAll((Collection<?>) nodeCachingLinkedList0);
      assertTrue(boolean1 == boolean0);
  }

  @Test(timeout = 4000)
  public void test072()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      // Undeclared exception!
      try { 
        cursorableLinkedList0.subList(2, (-2835));
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // fromIndex(2) > toIndex(-2835)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedSubList", e);
      }
  }

  @Test(timeout = 4000)
  public void test073()  throws Throwable  {
      CursorableLinkedList<LinkedList<DataInputStream>> cursorableLinkedList0 = new CursorableLinkedList<LinkedList<DataInputStream>>();
      AbstractLinkedList.LinkedSubList<LinkedList<DataInputStream>> abstractLinkedList_LinkedSubList0 = null;
      try {
        abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<LinkedList<DataInputStream>>(cursorableLinkedList0, 964, 1884);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // toIndex = 1884
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedSubList", e);
      }
  }

  @Test(timeout = 4000)
  public void test074()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      CursorableLinkedList.Cursor<Object> cursorableLinkedList_Cursor0 = cursorableLinkedList0.cursor();
      // Undeclared exception!
      try { 
        cursorableLinkedList_Cursor0.previous();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // Already at start of list.
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedListIterator", e);
      }
  }

  @Test(timeout = 4000)
  public void test075()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(cursorableLinkedList0, 0, 0);
      AbstractLinkedList.LinkedSubListIterator<Object> abstractLinkedList_LinkedSubListIterator0 = new AbstractLinkedList.LinkedSubListIterator<Object>(abstractLinkedList_LinkedSubList0, 0);
      // Undeclared exception!
      try { 
        abstractLinkedList_LinkedSubListIterator0.next();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // No element at index 0.
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedListIterator", e);
      }
  }

  @Test(timeout = 4000)
  public void test076()  throws Throwable  {
      CursorableLinkedList<NodeCachingLinkedList<Object>> cursorableLinkedList0 = new CursorableLinkedList<NodeCachingLinkedList<Object>>();
      AbstractLinkedList.LinkedListIterator<NodeCachingLinkedList<Object>> abstractLinkedList_LinkedListIterator0 = new AbstractLinkedList.LinkedListIterator<NodeCachingLinkedList<Object>>(cursorableLinkedList0, 0);
      // Undeclared exception!
      try { 
        abstractLinkedList_LinkedListIterator0.remove();
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedListIterator", e);
      }
  }

  @Test(timeout = 4000)
  public void test077()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(cursorableLinkedList0, 0, 0);
      AbstractLinkedList.LinkedSubListIterator<Object> abstractLinkedList_LinkedSubListIterator0 = new AbstractLinkedList.LinkedSubListIterator<Object>(abstractLinkedList_LinkedSubList0, 0);
      DataInputStream dataInputStream0 = new DataInputStream((InputStream) null);
      abstractLinkedList_LinkedSubList0.add((Object) dataInputStream0);
      // Undeclared exception!
      try { 
        abstractLinkedList_LinkedSubListIterator0.previous();
        fail("Expecting exception: ConcurrentModificationException");
      
      } catch(ConcurrentModificationException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedListIterator", e);
      }
  }

  @Test(timeout = 4000)
  public void test078()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) "org.apache.commons.collections4.list.NodeCachingLinkedList");
      MockFileOutputStream mockFileOutputStream0 = new MockFileOutputStream("org.apache.commons.collections4.list.NodeCachingLinkedList");
      ObjectOutputStream objectOutputStream0 = new ObjectOutputStream(mockFileOutputStream0);
      nodeCachingLinkedList0.doWriteObject(objectOutputStream0);
      assertEquals(1, nodeCachingLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test079()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(cursorableLinkedList0, 0, 0);
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      cursorableLinkedList0.removeNode(abstractLinkedList_Node0);
      // Undeclared exception!
      try { 
        cursorableLinkedList0.retainAll(abstractLinkedList_LinkedSubList0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Couldn't get the node: index (0) greater than the size of the list (-1).
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test080()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) null);
      nodeCachingLinkedList0.add((Object) nodeCachingLinkedList0);
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>(nodeCachingLinkedList0);
      String string0 = cursorableLinkedList0.toString();
      assertEquals(2, nodeCachingLinkedList0.size());
      assertEquals("[null, [null, (this Collection)]]", string0);
  }

  @Test(timeout = 4000)
  public void test081()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>(nodeCachingLinkedList0);
      cursorableLinkedList0.add((Object) nodeCachingLinkedList0);
      cursorableLinkedList0.toString();
      assertEquals(1, cursorableLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test082()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      String string0 = cursorableLinkedList0.toString();
      assertTrue(cursorableLinkedList0.isEmpty());
      assertEquals("[]", string0);
  }

  @Test(timeout = 4000)
  public void test083()  throws Throwable  {
      NodeCachingLinkedList<DataInputStream> nodeCachingLinkedList0 = new NodeCachingLinkedList<DataInputStream>();
      PipedInputStream pipedInputStream0 = new PipedInputStream();
      DataInputStream dataInputStream0 = new DataInputStream(pipedInputStream0);
      nodeCachingLinkedList0.add(dataInputStream0);
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList1 = new NodeCachingLinkedList<LinkedList<Object>>();
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      nodeCachingLinkedList1.add(linkedList0);
      boolean boolean0 = nodeCachingLinkedList1.equals(nodeCachingLinkedList0);
      assertEquals(1, nodeCachingLinkedList1.size());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test084()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) nodeCachingLinkedList0);
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      cursorableLinkedList0.addNodeBefore(abstractLinkedList_Node0, abstractLinkedList_Node0);
      boolean boolean0 = nodeCachingLinkedList0.equals(cursorableLinkedList0);
      assertEquals(1, cursorableLinkedList0.size());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test085()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) nodeCachingLinkedList0);
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>(nodeCachingLinkedList0);
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      nodeCachingLinkedList0.add((Object) cursorableLinkedList0);
      cursorableLinkedList0.addNodeBefore(abstractLinkedList_Node0, abstractLinkedList_Node0);
      boolean boolean0 = cursorableLinkedList0.retainAll(nodeCachingLinkedList0);
      assertEquals(2, nodeCachingLinkedList0.size());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test086()  throws Throwable  {
      NodeCachingLinkedList<DataInputStream> nodeCachingLinkedList0 = new NodeCachingLinkedList<DataInputStream>();
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList1 = new NodeCachingLinkedList<LinkedList<Object>>();
      nodeCachingLinkedList1.add((LinkedList<Object>) null);
      boolean boolean0 = nodeCachingLinkedList1.equals(nodeCachingLinkedList0);
      assertEquals(1, nodeCachingLinkedList1.size());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test087()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      boolean boolean0 = nodeCachingLinkedList0.equals(nodeCachingLinkedList0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test088()  throws Throwable  {
      NodeCachingLinkedList<DataInputStream> nodeCachingLinkedList0 = new NodeCachingLinkedList<DataInputStream>();
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.removeLast();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test089()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>((-1148));
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      nodeCachingLinkedList0.add(linkedList0);
      nodeCachingLinkedList0.removeLast();
      assertTrue(nodeCachingLinkedList0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test090()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>(1702);
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.removeFirst();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test091()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      // Undeclared exception!
      try { 
        cursorableLinkedList0.getLast();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test092()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      Object object0 = new Object();
      cursorableLinkedList0.add(object0);
      cursorableLinkedList0.getLast();
      assertEquals(1, cursorableLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test093()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      // Undeclared exception!
      try { 
        cursorableLinkedList0.getFirst();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test094()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) cursorableLinkedList0);
      nodeCachingLinkedList0.getFirst();
      assertEquals(1, nodeCachingLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test095()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>(nodeCachingLinkedList0);
      cursorableLinkedList0.add((Object) nodeCachingLinkedList0);
      boolean boolean0 = cursorableLinkedList0.removeAll(nodeCachingLinkedList0);
      assertEquals(1, cursorableLinkedList0.size());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test096()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) nodeCachingLinkedList0);
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>(nodeCachingLinkedList0);
      boolean boolean0 = cursorableLinkedList0.removeAll(nodeCachingLinkedList0);
      assertEquals(1, nodeCachingLinkedList0.size());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test097()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) nodeCachingLinkedList0);
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      boolean boolean0 = nodeCachingLinkedList0.remove((Object) abstractLinkedList_Node0);
      assertEquals(1, nodeCachingLinkedList0.size());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test098()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      cursorableLinkedList0.add((Object) cursorableLinkedList0);
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>(cursorableLinkedList0);
      boolean boolean0 = nodeCachingLinkedList0.containsAll(cursorableLinkedList0);
      assertEquals(1, cursorableLinkedList0.size());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test099()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>((-1016));
      boolean boolean0 = cursorableLinkedList0.add((Object) nodeCachingLinkedList0);
      boolean boolean1 = nodeCachingLinkedList0.containsAll(cursorableLinkedList0);
      assertFalse(boolean1 == boolean0);
      assertFalse(boolean1);
  }

  @Test(timeout = 4000)
  public void test100()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>(2347);
      boolean boolean0 = cursorableLinkedList0.add((Object) nodeCachingLinkedList0);
      assertTrue(boolean0);
      
      CursorableLinkedList.Cursor<Object> cursorableLinkedList_Cursor0 = cursorableLinkedList0.cursor();
      Object object0 = cursorableLinkedList_Cursor0.next();
      assertNotNull(object0);
      
      int int0 = cursorableLinkedList0.lastIndexOf(object0);
      assertEquals(0, int0);
      assertFalse(cursorableLinkedList0.contains(2347));
  }

  @Test(timeout = 4000)
  public void test101()  throws Throwable  {
      NodeCachingLinkedList<DataInputStream> nodeCachingLinkedList0 = new NodeCachingLinkedList<DataInputStream>();
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList1 = new NodeCachingLinkedList<LinkedList<Object>>();
      nodeCachingLinkedList1.add((LinkedList<Object>) null);
      int int0 = nodeCachingLinkedList1.lastIndexOf(nodeCachingLinkedList0);
      assertEquals(1, nodeCachingLinkedList1.size());
      assertEquals((-1), int0);
  }

  @Test(timeout = 4000)
  public void test102()  throws Throwable  {
      NodeCachingLinkedList<DataInputStream> nodeCachingLinkedList0 = new NodeCachingLinkedList<DataInputStream>();
      int int0 = nodeCachingLinkedList0.lastIndexOf(nodeCachingLinkedList0);
      assertEquals((-1), int0);
  }

  @Test(timeout = 4000)
  public void test103()  throws Throwable  {
      CursorableLinkedList<NodeCachingLinkedList<Object>> cursorableLinkedList0 = new CursorableLinkedList<NodeCachingLinkedList<Object>>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>(cursorableLinkedList0);
      cursorableLinkedList0.add(nodeCachingLinkedList0);
      NodeCachingLinkedList<DataInputStream> nodeCachingLinkedList1 = new NodeCachingLinkedList<DataInputStream>();
      boolean boolean0 = cursorableLinkedList0.contains(nodeCachingLinkedList1);
      assertEquals(1, cursorableLinkedList0.size());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test104()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) nodeCachingLinkedList0);
      Object object0 = new Object();
      boolean boolean0 = nodeCachingLinkedList0.contains(object0);
      assertEquals(1, nodeCachingLinkedList0.size());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test105()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      cursorableLinkedList0.removeNode(abstractLinkedList_Node0);
      // Undeclared exception!
      try { 
        cursorableLinkedList0.toString();
        fail("Expecting exception: NegativeArraySizeException");
      
      } catch(NegativeArraySizeException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.lang.AbstractStringBuilder", e);
      }
  }

  @Test(timeout = 4000)
  public void test106()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>(0);
      AbstractLinkedList.Node<NodeCachingLinkedList<Object>> abstractLinkedList_Node0 = new AbstractLinkedList.Node<NodeCachingLinkedList<Object>>(nodeCachingLinkedList0);
      AbstractLinkedList.Node<NodeCachingLinkedList<Object>> abstractLinkedList_Node1 = abstractLinkedList_Node0.getPreviousNode();
      assertNull(abstractLinkedList_Node1);
  }

  @Test(timeout = 4000)
  public void test107()  throws Throwable  {
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      abstractLinkedList_Node0.setNextNode((AbstractLinkedList.Node<Object>) null);
  }

  @Test(timeout = 4000)
  public void test108()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>(nodeCachingLinkedList0);
      AbstractLinkedList.Node<Object> abstractLinkedList_Node1 = abstractLinkedList_Node0.getNextNode();
      assertNull(abstractLinkedList_Node1);
  }

  @Test(timeout = 4000)
  public void test109()  throws Throwable  {
      AbstractLinkedList.Node<Object> abstractLinkedList_Node0 = new AbstractLinkedList.Node<Object>();
      Object object0 = new Object();
      AbstractLinkedList.Node<Object> abstractLinkedList_Node1 = new AbstractLinkedList.Node<Object>(abstractLinkedList_Node0, abstractLinkedList_Node0, object0);
      abstractLinkedList_Node0.setPreviousNode(abstractLinkedList_Node1);
  }

  @Test(timeout = 4000)
  public void test110()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(cursorableLinkedList0, 0, 0);
      AbstractLinkedList.LinkedSubListIterator<Object> abstractLinkedList_LinkedSubListIterator0 = new AbstractLinkedList.LinkedSubListIterator<Object>(abstractLinkedList_LinkedSubList0, 0);
      // Undeclared exception!
      try { 
        abstractLinkedList_LinkedSubListIterator0.previous();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // Already at start of list.
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedListIterator", e);
      }
  }

  @Test(timeout = 4000)
  public void test111()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.add((Object) nodeCachingLinkedList0);
      Comparator<Object> comparator0 = (Comparator<Object>) mock(Comparator.class, new ViolatedAssumptionAnswer());
      nodeCachingLinkedList0.sort(comparator0);
      assertEquals(1, nodeCachingLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test112()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      List<LinkedList<Object>> list0 = nodeCachingLinkedList0.subList(0, 0);
      boolean boolean0 = nodeCachingLinkedList0.equals(list0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test113()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList1 = new NodeCachingLinkedList<Object>(nodeCachingLinkedList0);
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(nodeCachingLinkedList1, 0, 0);
      // Undeclared exception!
      try { 
        abstractLinkedList_LinkedSubList0.remove(230);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Index '230' out of bounds for size '0'
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedSubList", e);
      }
  }

  @Test(timeout = 4000)
  public void test114()  throws Throwable  {
      NodeCachingLinkedList<ByteArrayInputStream> nodeCachingLinkedList0 = new NodeCachingLinkedList<ByteArrayInputStream>();
      AbstractLinkedList.LinkedSubList<ByteArrayInputStream> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<ByteArrayInputStream>(nodeCachingLinkedList0, 0, 0);
      List<ByteArrayInputStream> list0 = abstractLinkedList_LinkedSubList0.subList(0, 0);
      assertEquals(0, list0.size());
      assertTrue(nodeCachingLinkedList0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test115()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList1 = new NodeCachingLinkedList<Object>(nodeCachingLinkedList0);
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(nodeCachingLinkedList1, 0, 0);
      // Undeclared exception!
      try { 
        abstractLinkedList_LinkedSubList0.get(0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Index '0' out of bounds for size '0'
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedSubList", e);
      }
  }

  @Test(timeout = 4000)
  public void test116()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList1 = new NodeCachingLinkedList<Object>(nodeCachingLinkedList0);
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(nodeCachingLinkedList1, 0, 0);
      AbstractLinkedList.LinkedSubListIterator<Object> abstractLinkedList_LinkedSubListIterator0 = new AbstractLinkedList.LinkedSubListIterator<Object>(abstractLinkedList_LinkedSubList0, 0);
      abstractLinkedList_LinkedSubListIterator0.add(nodeCachingLinkedList0);
      abstractLinkedList_LinkedSubList0.get(0);
      assertEquals(1, nodeCachingLinkedList1.size());
  }

  @Test(timeout = 4000)
  public void test117()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      // Undeclared exception!
      try { 
        cursorableLinkedList0.subList((-594), 0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // fromIndex = -594
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList$LinkedSubList", e);
      }
  }

  @Test(timeout = 4000)
  public void test118()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>((-7));
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.set((-7), cursorableLinkedList0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Couldn't get the node: index (-7) less than zero.
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test119()  throws Throwable  {
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>();
      nodeCachingLinkedList0.clear();
      assertEquals(0, nodeCachingLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test120()  throws Throwable  {
      LinkedList<DataInputStream> linkedList0 = new LinkedList<DataInputStream>();
      NodeCachingLinkedList<DataInputStream> nodeCachingLinkedList0 = new NodeCachingLinkedList<DataInputStream>(linkedList0);
      // Undeclared exception!
      try { 
        nodeCachingLinkedList0.remove(0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Couldn't get the node: index (0) is the size of the list.
         //
         verifyException("org.apache.commons.collections4.list.AbstractLinkedList", e);
      }
  }

  @Test(timeout = 4000)
  public void test121()  throws Throwable  {
      NodeCachingLinkedList<LinkedList<Object>> nodeCachingLinkedList0 = new NodeCachingLinkedList<LinkedList<Object>>();
      LinkedList<Object> linkedList0 = new LinkedList<Object>();
      nodeCachingLinkedList0.addFirst(linkedList0);
      nodeCachingLinkedList0.get(0);
      assertEquals(1, nodeCachingLinkedList0.size());
  }

  @Test(timeout = 4000)
  public void test122()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      Object[] objectArray0 = cursorableLinkedList0.toArray();
      assertEquals(0, objectArray0.length);
  }

  @Test(timeout = 4000)
  public void test123()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      NodeCachingLinkedList<Object> nodeCachingLinkedList0 = new NodeCachingLinkedList<Object>(2086);
      nodeCachingLinkedList0.add((Object) cursorableLinkedList0);
      assertFalse(nodeCachingLinkedList0.isEmpty());
      
      boolean boolean0 = nodeCachingLinkedList0.remove((Object) cursorableLinkedList0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test124()  throws Throwable  {
      CursorableLinkedList<Object> cursorableLinkedList0 = new CursorableLinkedList<Object>();
      AbstractLinkedList.LinkedSubList<Object> abstractLinkedList_LinkedSubList0 = new AbstractLinkedList.LinkedSubList<Object>(cursorableLinkedList0, 0, 0);
      cursorableLinkedList0.add((Object) abstractLinkedList_LinkedSubList0);
      assertEquals(1, cursorableLinkedList0.size());
      
      cursorableLinkedList0.removeFirst();
      assertEquals(0, cursorableLinkedList0.size());
  }
}
