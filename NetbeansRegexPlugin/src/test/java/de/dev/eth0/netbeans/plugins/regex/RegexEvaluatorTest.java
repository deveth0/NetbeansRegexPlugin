/*
 * #%L
 * dev-eth0.de
 * %%
 * Copyright (C) 2016 dev-eth0.de
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package de.dev.eth0.netbeans.plugins.regex;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test for RegexEvaluator
 *
 * @author deveth0
 */
public class RegexEvaluatorTest {

  RegexEvaluator eval;

  @Before
  public void setUp() throws Exception {
    eval = new RegexEvaluator();
  }

  /**
   * Test of update method, of class RegexEvaluator.
   */
  @Test
  public void testInitialSetup() {
    assertNotNull(eval.groups);
    assertEquals(0, eval.groups.length);
  }

  @Test
  public void testNoMatch() {
    String testInput = "Foobar Ipsum Lorem";
    eval.update("Foo(.*) bar", 0, "", testInput);
    assertFalse(eval.matches);
    assertFalse(eval.find);
    assertEquals(0, eval.groups.length);
  }

  @Test
  public void testSingleGroup() {
    String testInput = "Foobar ipsum";
    eval.update("Foo(.*) ipsum", 0, "", testInput);
    assertTrue(eval.matches);
    assertTrue(eval.lookingAt);
    assertTrue(eval.find);
    assertEquals(2, eval.groups.length);
    assertEquals(testInput, eval.groups[0].group);
    assertEquals(0, eval.groups[0].start);
    assertEquals(testInput.length(), eval.groups[0].end);
    assertEquals("bar", eval.groups[1].group);
    assertEquals(3, eval.groups[1].start);
    assertEquals(6, eval.groups[1].end);
  }

  @Test
  public void testMultipleGroups() {
    String testInput = "Foobar Ipsum Lorem";
    eval.update("Foo(.*) (.*)", 0, "", testInput);
    assertTrue(eval.matches);
    assertTrue(eval.lookingAt);
    assertTrue(eval.find);
    assertEquals(3, eval.groups.length);
    assertEquals(testInput, eval.groups[0].group);
    assertEquals(0, eval.groups[0].start);
    assertEquals(testInput.length(), eval.groups[0].end);
    assertEquals("bar Ipsum", eval.groups[1].group);
    assertEquals(3, eval.groups[1].start);
    assertEquals(12, eval.groups[1].end);
    assertEquals("Lorem", eval.groups[2].group);
    assertEquals(13, eval.groups[2].start);
    assertEquals(testInput.length(), eval.groups[2].end);
  }

  @Test
  public void testReplace() {
    String testInput = "zzzdogzzzdogzzz";
    eval.update("dog", 0, "cat", testInput);
    assertEquals("zzzcatzzzdogzzz", eval.replaceFirst);
    assertEquals("zzzcatzzzcatzzz", eval.replaceAll);
  }

}
