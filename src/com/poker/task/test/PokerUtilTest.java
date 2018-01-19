package com.poker.task.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.poker.task.PokerUtil;

public class PokerUtilTest {

	@Test
	public void testInitializeHand() {
	
		List<String> lstHand = new ArrayList<String>();
		lstHand.add("D5");
		lstHand.add("C8");
		lstHand.add("S6");
		lstHand.add("C7");
		lstHand.add("H9");
		
		Assert.assertNotNull(PokerUtil.initializeHand(lstHand));
				
	}

	@Test
	public void testDetermineSuit() {
		Assert.assertTrue("Hearts".equals(PokerUtil.determineSuit("H")));
		Assert.assertFalse("Clubs".equals(PokerUtil.determineSuit("S")));
	}

	@Test
	public void testDetermineValue() {
		Assert.assertEquals(6, PokerUtil.determineValue("6"));
		Assert.assertNotEquals(8, PokerUtil.determineValue("9"));
	}

	@Test
	public void testPlay() {
		
		List<String> lstHand = new ArrayList<String>();
		lstHand.add("DQ");
		lstHand.add("DK");
		lstHand.add("D9");
		lstHand.add("DJ");
		lstHand.add("D10");
		
	//	Assert.assertTrue(PokerUtil.play(lstHand, null));
		
	
	}

}
