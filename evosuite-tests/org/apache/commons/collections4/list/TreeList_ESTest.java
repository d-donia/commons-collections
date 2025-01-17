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
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.apache.commons.collections4.list.TreeList;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class TreeList_ESTest extends TreeList_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      TreeList<Integer> treeList0 = new TreeList<Integer>();
      TreeList.TreeListIterator<Integer> treeList_TreeListIterator0 = new TreeList.TreeListIterator<Integer>(treeList0, 63);
      Consumer<Object> consumer0 = (Consumer<Object>) mock(Consumer.class, new ViolatedAssumptionAnswer());
      treeList_TreeListIterator0.forEachRemaining(consumer0);
      assertEquals(63, treeList_TreeListIterator0.nextIndex());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      linkedList0.add((Integer) null);
      linkedList0.add((Integer) null);
      linkedList0.add((Integer) null);
      linkedList0.add((Integer) null);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.remove((Object) null);
      boolean boolean0 = treeList0.addAll((Collection<?>) linkedList0);
      assertEquals(7, treeList0.size());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      Integer integer1 = new Integer(1391);
      linkedList0.add(integer1);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      Integer integer2 = new Integer(1391);
      Predicate<Object> predicate0 = Predicate.isEqual((Object) integer2);
      treeList0.removeIf(predicate0);
      boolean boolean0 = treeList0.contains(integer1);
      assertEquals(4, treeList0.size());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.add(0, (Object) null);
      boolean boolean0 = treeList0.retainAll(treeList0);
      assertEquals(2, treeList0.size());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      TreeList<Integer> treeList0 = new TreeList<Integer>();
      Integer integer0 = new Integer(0);
      treeList0.add(0, integer0);
      TreeList<Object> treeList1 = new TreeList<Object>(treeList0);
      TreeList.TreeListIterator<Object> treeList_TreeListIterator0 = new TreeList.TreeListIterator<Object>(treeList1, 0);
      treeList_TreeListIterator0.next();
      assertTrue(treeList_TreeListIterator0.hasPrevious());
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      TreeList<Integer> treeList0 = new TreeList<Integer>();
      TreeList<Object> treeList1 = new TreeList<Object>(treeList0);
      TreeList.TreeListIterator<Object> treeList_TreeListIterator0 = new TreeList.TreeListIterator<Object>(treeList1, (-1));
      treeList_TreeListIterator0.checkModCount();
      assertEquals((-1), treeList_TreeListIterator0.nextIndex());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      TreeList<Integer> treeList0 = new TreeList<Integer>();
      Object[] objectArray0 = treeList0.toArray();
      assertEquals(0, objectArray0.length);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      TreeList<Object> treeList0 = new TreeList<Object>();
      int int0 = treeList0.size();
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      TreeList<Integer> treeList0 = new TreeList<Integer>();
      Integer integer0 = new Integer(0);
      treeList0.add(0, integer0);
      TreeList<Object> treeList1 = new TreeList<Object>(treeList0);
      int int0 = treeList1.size();
      assertFalse(treeList0.contains(int0));
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      TreeList<Integer> treeList0 = new TreeList<Integer>(linkedList0);
      TreeList<Object> treeList1 = new TreeList<Object>(linkedList0);
      treeList1.add(0, (Object) treeList0);
      treeList1.set(0, (Object) null);
      treeList1.set(0, (Object) null);
      assertEquals(1, treeList1.size());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      linkedList0.add((Integer) null);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.remove(0);
      assertEquals(0, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      TreeList<Integer> treeList0 = new TreeList<Integer>(linkedList0);
      TreeList<Object> treeList1 = new TreeList<Object>(treeList0);
      treeList1.set(0, linkedList0);
      LinkedList linkedList1 = (LinkedList)treeList1.remove(0);
      assertEquals(1, linkedList1.size());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      ListIterator<Object> listIterator0 = treeList0.listIterator(1);
      assertTrue(listIterator0.hasPrevious());
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      TreeList<Object> treeList0 = new TreeList<Object>();
      ListIterator<Object> listIterator0 = treeList0.listIterator(0);
      assertFalse(listIterator0.hasNext());
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      linkedList0.add((Integer) null);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      ListIterator<Object> listIterator0 = treeList0.listIterator();
      assertTrue(listIterator0.hasNext());
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      TreeList<Integer> treeList0 = new TreeList<Integer>();
      ListIterator<Integer> listIterator0 = treeList0.listIterator();
      assertFalse(listIterator0.hasNext());
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      TreeList<Integer> treeList0 = new TreeList<Integer>();
      Iterator<Integer> iterator0 = treeList0.iterator();
      assertNotNull(iterator0);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      Integer integer1 = new Integer(1391);
      linkedList0.add(integer1);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      int int0 = treeList0.indexOf(integer1);
      assertEquals(1, int0);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      linkedList0.add((Integer) null);
      linkedList0.add((Integer) null);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      Object object0 = treeList0.get(1);
      assertNull(object0);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.set(0, treeList0);
      treeList0.get(0);
      assertFalse(treeList0.contains(0));
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      List<Object> list0 = treeList0.subList(0, 3);
      // Undeclared exception!
      try { 
        treeList0.addAll((Collection<?>) list0);
        fail("Expecting exception: ConcurrentModificationException");
      
      } catch(ConcurrentModificationException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.util.SubList", e);
      }
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      TreeList<Integer> treeList0 = new TreeList<Integer>();
      // Undeclared exception!
      try { 
        treeList0.addAll((Collection<? extends Integer>) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      TreeList<Integer> treeList0 = new TreeList<Integer>();
      TreeList<Object> treeList1 = new TreeList<Object>();
      // Undeclared exception!
      try { 
        treeList1.add(283, (Object) treeList0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Invalid index:283, size=0
         //
         verifyException("org.apache.commons.collections4.list.TreeList", e);
      }
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      TreeList<Object> treeList0 = null;
      try {
        treeList0 = new TreeList<Object>((Collection<?>) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      linkedList0.add((Integer) null);
      linkedList0.add((Integer) null);
      linkedList0.add((Integer) null);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) treeList0);
      boolean boolean0 = treeList0.remove((Object) null);
      assertEquals(5, treeList0.size());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = linkedList0.pollLast();
      linkedList0.add((Integer) null);
      linkedList0.add((Integer) null);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      linkedList0.add((Integer) null);
      linkedList0.add((Integer) null);
      linkedList0.add((Integer) null);
      linkedList0.add(integer0);
      linkedList0.add((Integer) null);
      boolean boolean0 = treeList0.addAll((Collection<?>) linkedList0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      linkedList0.add((Integer) null);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      linkedList0.add((Integer) null);
      linkedList0.add((Integer) null);
      treeList0.addAll((Collection<?>) linkedList0);
      treeList0.toArray();
      assertEquals(4, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test27()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      int int0 = treeList0.indexOf(integer0);
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test28()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      TreeList<Integer> treeList0 = new TreeList<Integer>(linkedList0);
      Object object0 = new Object();
      int int0 = treeList0.indexOf(object0);
      assertEquals((-1), int0);
  }

  @Test(timeout = 4000)
  public void test29()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      // Undeclared exception!
      try { 
        treeList0.get(1);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Invalid index:1, size=0
         //
         verifyException("org.apache.commons.collections4.list.TreeList", e);
      }
  }

  @Test(timeout = 4000)
  public void test30()  throws Throwable  {
      TreeList<Object> treeList0 = new TreeList<Object>();
      TreeList.TreeListIterator<Object> treeList_TreeListIterator0 = new TreeList.TreeListIterator<Object>(treeList0, 2005);
      Integer integer0 = new Integer(1004);
      // Undeclared exception!
      try { 
        treeList_TreeListIterator0.set(integer0);
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.TreeList$TreeListIterator", e);
      }
  }

  @Test(timeout = 4000)
  public void test31()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      Comparator<Object> comparator0 = (Comparator<Object>) mock(Comparator.class, new ViolatedAssumptionAnswer());
      treeList0.sort(comparator0);
      assertTrue(treeList0.contains(0));
  }

  @Test(timeout = 4000)
  public void test32()  throws Throwable  {
      TreeList<Integer> treeList0 = new TreeList<Integer>();
      TreeList.TreeListIterator<Integer> treeList_TreeListIterator0 = new TreeList.TreeListIterator<Integer>(treeList0, 0);
      // Undeclared exception!
      try { 
        treeList_TreeListIterator0.remove();
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.TreeList$TreeListIterator", e);
      }
  }

  @Test(timeout = 4000)
  public void test33()  throws Throwable  {
      TreeList<Integer> treeList0 = new TreeList<Integer>();
      TreeList.TreeListIterator<Integer> treeList_TreeListIterator0 = new TreeList.TreeListIterator<Integer>(treeList0, (-1889));
      // Undeclared exception!
      try { 
        treeList_TreeListIterator0.previous();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // Already at start of list.
         //
         verifyException("org.apache.commons.collections4.list.TreeList$TreeListIterator", e);
      }
  }

  @Test(timeout = 4000)
  public void test34()  throws Throwable  {
      TreeList<Object> treeList0 = new TreeList<Object>();
      TreeList.TreeListIterator<Object> treeList_TreeListIterator0 = new TreeList.TreeListIterator<Object>(treeList0, 0);
      // Undeclared exception!
      try { 
        treeList_TreeListIterator0.next();
        fail("Expecting exception: NoSuchElementException");
      
      } catch(NoSuchElementException e) {
         //
         // No element at index 0.
         //
         verifyException("org.apache.commons.collections4.list.TreeList$TreeListIterator", e);
      }
  }

  @Test(timeout = 4000)
  public void test35()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      TreeList<Integer> treeList0 = new TreeList<Integer>(linkedList0);
      TreeList.TreeListIterator<Integer> treeList_TreeListIterator0 = new TreeList.TreeListIterator<Integer>(treeList0, 0);
      treeList0.add(integer0);
      // Undeclared exception!
      try { 
        treeList_TreeListIterator0.add(integer0);
        fail("Expecting exception: ConcurrentModificationException");
      
      } catch(ConcurrentModificationException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.collections4.list.TreeList$TreeListIterator", e);
      }
  }

  @Test(timeout = 4000)
  public void test36()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer((-1037));
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      linkedList0.add(integer0);
      treeList0.addAll((Collection<?>) linkedList0);
      boolean boolean0 = treeList0.addAll((Collection<?>) linkedList0);
      assertEquals(10, treeList0.size());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test37()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(1);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      treeList0.remove(1);
      assertEquals(5, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test38()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(1);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      Object object0 = treeList0.remove(1);
      assertEquals(1, object0);
  }

  @Test(timeout = 4000)
  public void test39()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(1);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      treeList0.remove(1);
      assertEquals(7, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test40()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(1);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.remove(1);
      assertEquals(3, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test41()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer((-318));
      linkedList0.add(integer0);
      Integer integer1 = new Integer(2887);
      linkedList0.add(integer1);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      Predicate<Object> predicate0 = Predicate.isEqual((Object) integer1);
      treeList0.removeIf(predicate0);
      treeList0.remove(1);
      assertEquals(1, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test42()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(2);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      treeList0.remove(2);
      treeList0.remove(2);
      assertEquals(4, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test43()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      boolean boolean0 = linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      treeList0.addAll((Collection<?>) treeList0);
      boolean boolean1 = treeList0.addAll((Collection<?>) treeList0);
      assertTrue(boolean1 == boolean0);
  }

  @Test(timeout = 4000)
  public void test44()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(2);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      treeList0.remove(2);
      assertEquals(3, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test45()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      linkedList0.add(integer0);
      treeList0.addAll(0, (Collection<?>) linkedList0);
      treeList0.remove(0);
      assertEquals(4, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test46()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      Integer integer1 = new Integer(1);
      treeList0.set(1, integer1);
      boolean boolean0 = treeList0.retainAll(linkedList0);
      assertEquals(3, treeList0.size());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test47()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      assertEquals(2, treeList0.size());
      
      Integer integer1 = new Integer(1);
      treeList0.set(1, integer1);
      boolean boolean0 = treeList0.retainAll(linkedList0);
      assertEquals(1, treeList0.size());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test48()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer((-1037));
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      treeList0.remove(0);
      assertEquals(3, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test49()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer((-1037));
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      treeList0.lastIndexOf(linkedList0);
      assertEquals(4, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test50()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      treeList0.toArray();
      assertEquals(4, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test51()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      boolean boolean0 = treeList0.contains(integer0);
      assertEquals(4, treeList0.size());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test52()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer((-1037));
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      int int0 = treeList0.lastIndexOf(linkedList0);
      assertEquals((-1), int0);
  }

  @Test(timeout = 4000)
  public void test53()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer((-1037));
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      TreeList<Object> treeList1 = new TreeList<Object>(treeList0);
      treeList1.addAll(0, (Collection<?>) linkedList0);
      assertEquals(4, treeList0.size());
      assertTrue(treeList1.contains((-1037)));
  }

  @Test(timeout = 4000)
  public void test54()  throws Throwable  {
      TreeList<Object> treeList0 = new TreeList<Object>();
      // Undeclared exception!
      try { 
        treeList0.listIterator((-3075));
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Invalid index:-3075, size=0
         //
         verifyException("org.apache.commons.collections4.list.TreeList", e);
      }
  }

  @Test(timeout = 4000)
  public void test55()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      Integer integer0 = new Integer(2);
      linkedList0.add(integer0);
      boolean boolean0 = treeList0.addAll((Collection<?>) linkedList0);
      assertEquals(1, treeList0.size());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test56()  throws Throwable  {
      TreeList<Object> treeList0 = new TreeList<Object>();
      boolean boolean0 = treeList0.addAll((Collection<?>) treeList0);
      assertFalse(boolean0);
      assertEquals(0, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test57()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer((-1037));
      linkedList0.add(integer0);
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      treeList0.addAll((Collection<?>) linkedList0);
      treeList0.addAll(0, (Collection<?>) linkedList0);
      assertEquals(6, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test58()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer((-1037));
      linkedList0.add(integer0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      boolean boolean0 = treeList0.addAll((Collection<?>) linkedList0);
      assertEquals(2, treeList0.size());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test59()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      TreeList<Integer> treeList0 = new TreeList<Integer>(linkedList0);
      TreeList.TreeListIterator<Integer> treeList_TreeListIterator0 = new TreeList.TreeListIterator<Integer>(treeList0, 0);
      treeList_TreeListIterator0.add(integer0);
      treeList_TreeListIterator0.add(integer0);
      assertEquals(2, treeList0.size());
      assertFalse(treeList_TreeListIterator0.hasNext());
  }

  @Test(timeout = 4000)
  public void test60()  throws Throwable  {
      TreeList<Integer> treeList0 = new TreeList<Integer>();
      TreeList<Object> treeList1 = new TreeList<Object>(treeList0);
      TreeList.TreeListIterator<Object> treeList_TreeListIterator0 = new TreeList.TreeListIterator<Object>(treeList1, 0);
      int int0 = treeList_TreeListIterator0.previousIndex();
      assertEquals((-1), int0);
      assertEquals(0, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test61()  throws Throwable  {
      TreeList<Object> treeList0 = new TreeList<Object>();
      treeList0.clear();
      assertEquals(0, treeList0.size());
  }

  @Test(timeout = 4000)
  public void test62()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      // Undeclared exception!
      try { 
        treeList0.remove(0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Invalid index:0, size=0
         //
         verifyException("org.apache.commons.collections4.list.TreeList", e);
      }
  }

  @Test(timeout = 4000)
  public void test63()  throws Throwable  {
      LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
      Integer integer0 = new Integer(0);
      TreeList<Object> treeList0 = new TreeList<Object>(linkedList0);
      // Undeclared exception!
      try { 
        treeList0.set(1, integer0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Invalid index:1, size=0
         //
         verifyException("org.apache.commons.collections4.list.TreeList", e);
      }
  }
}
