package com.github.congcongcong250.coding2017.basicTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.congcongcong250.coding2017.basic.ArrayUtil;
import com.github.congcongcong250.coding2017.basic.SLinkedList;

public class SlinkedListTest {

	private SLinkedList testList;

	@Before
	public void setUp() throws Exception {
		testList = new SLinkedList();
	}

	@Test
	public void testgetElements() {
		int[] a = { 10, 23, 34, 36, 37, 41, 44, 46, 56, 67 };
		int[] b = { 0, 4, 6, 7 };
		int[] ac = {10, 37, 44, 46};
		SLinkedList indexList = new SLinkedList();
		for (int i : a) {
			testList.add(i);
		}

		for (int i : b) {
			indexList.add(i);
		}

		int[] res = testList.getElements(indexList);
		assertEquals(res, ac);
		for (int j = 0; j < res.length; j++) {
			System.out.print(res[j]+ ", ");
		}
	}
}
