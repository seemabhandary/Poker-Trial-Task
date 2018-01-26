package com.poker.task.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.poker.task.Card;
import com.poker.task.Hand;
import com.poker.task.PokerConstants;
import com.poker.task.PokerUtil;

public class PokerUtilTest {

	@Test
	public void testInitializeHand() {

		List<String> lstCards = new ArrayList<String>();
		lstCards.add("D1");
		lstCards.add("SA");
		lstCards.add("SK");
		lstCards.add("S10");
		lstCards.add("SQ");
		Assert.assertNotNull(PokerUtil.initializeHand(lstCards));

	}

	@Test
	public void testGetHighCardData() {

		List<Integer> lstCards = new ArrayList<Integer>();
		lstCards.add(new Integer(2));
		lstCards.add(new Integer(3));
		lstCards.add(new Integer(4));
		lstCards.add(new Integer(5));
		lstCards.add(new Integer(6));

		List<Integer> lstCards1 = new ArrayList<Integer>();
		lstCards1.add(new Integer(7));
		lstCards1.add(new Integer(8));
		lstCards1.add(new Integer(9));
		lstCards1.add(new Integer(10));
		lstCards1.add(new Integer(11));

		Map<String, List<Integer>> mpData = new HashMap<String, List<Integer>>();
		mpData.put("Player1HighCard", lstCards);
		mpData.put("Player2HighCard", lstCards1);
		try {
			Assert.assertEquals(11, PokerUtil.getHighCardData(mpData).entrySet().iterator().next().getValue().intValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testGetMapWithHighCardData() {
		List<Integer> lstCards = new ArrayList<Integer>();
		lstCards.add(new Integer(1));
		lstCards.add(new Integer(5));
		lstCards.add(new Integer(5));
		lstCards.add(new Integer(9));
		lstCards.add(new Integer(9));

		List<Integer> lstCards1 = new ArrayList<Integer>();
		lstCards1.add(new Integer(2));
		lstCards1.add(new Integer(5));
		lstCards1.add(new Integer(5));
		lstCards1.add(new Integer(9));
		lstCards1.add(new Integer(9));

		Map<String, List<Integer>> mpData = new HashMap<String, List<Integer>>();
		mpData.put("Player1HighCard", lstCards);
		mpData.put("Player2HighCard", lstCards1);
		try {
			Assert.assertEquals(2,
					PokerUtil.getMapWithHighCardData(mpData).entrySet().iterator().next().getValue().intValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testFindCaseWithRankValue() {

		try {
			org.junit.Assert.assertEquals(PokerConstants.FULL_HOUSE_CASE, PokerUtil.findCaseWithRankValue(7));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testDetermineSuit() {
		Assert.assertTrue(PokerUtil.determineSuit("S").equals(PokerConstants.SPADES));
	}

	@Test
	public void testCreatePlayerDataStructure() {
		
		Map<String,String[]> mpPlayerData =  new HashMap<String,String[]>();
		mpPlayerData.put("Player1Input1", new String[] {"SQ"});
		mpPlayerData.put("Player1Input2", new String[] {"HK"});
		mpPlayerData.put("Player1Input3", new String[] {"D10"});
		mpPlayerData.put("Player1Input4", new String[] {"S9"});
		mpPlayerData.put("Player1Input5", new String[] {"C8"});
		
		mpPlayerData.put("Player2Input1", new String[] {"HJ"});
		mpPlayerData.put("Player2Input2", new String[] {"SJ"});
		mpPlayerData.put("Player2Input3", new String[] {"CJ"});
		mpPlayerData.put("Player2Input4", new String[] {"S2"});
		mpPlayerData.put("Player2Input5", new String[] {"C4"});
		
		
		try {
			Assert.assertNotNull(PokerUtil.createPlayerDataStructure(mpPlayerData));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testValidateInputData() {
		
		Map<String,String[]> mpPlayerData =  new HashMap<String,String[]>();
		mpPlayerData.put("Player1Input1", new String[] {"SQ"});
		mpPlayerData.put("Player1Input2", new String[] {"HK"});
		mpPlayerData.put("Player1Input3", new String[] {"D10"});
		mpPlayerData.put("Player1Input4", new String[] {"S9"});
		mpPlayerData.put("Player1Input5", new String[] {"C8"});
		
		mpPlayerData.put("Player2Input1", new String[] {"HJ"});
		mpPlayerData.put("Player2Input2", new String[] {"SJ"});
		mpPlayerData.put("Player2Input3", new String[] {"CJ"});
		mpPlayerData.put("Player2Input4", new String[] {"S2"});
		mpPlayerData.put("Player2Input5", new String[] {"C4"});
		
		
		try {
			PokerUtil.validateInputData(mpPlayerData);
		} catch (Exception e) {
			Assert.assertTrue(e.getMessage().equalsIgnoreCase(PokerConstants.INPUT_VALIDATION));
		}
		
	}


	@Test
	public void testIsThreeOfAKindCase() {
		Card card[] = new Card[] { new Card("Spades", 10), new Card("Hearts", 11), new Card("Spades", 12),
				new Card("Clubs", 12), new Card("Diamonds", 12) };

		Hand hand = new Hand();
		hand.setCards(card);
		try {
			assertTrue(PokerUtil.isThreeOfAKindCase(hand));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testIsAFullHouseCase() {
		Card card[] = new Card[] { new Card("Spades", 11), new Card("Hearts", 11), new Card("Spades", 12),
				new Card("Clubs", 12), new Card("Diamonds", 12) };

		Hand hand = new Hand();
		hand.setCards(card);
		try {
			assertTrue(PokerUtil.isAFullHouseCase(hand));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindPlayerRank() {
		
	}

	@Test
	public void testIsStraightCase() {

		Card card[] = new Card[] { new Card("Diamond", 10), new Card("Club", 12), new Card("Spades", 11),
				new Card("Club", 13), new Card("Hearts", 9) };

		Hand hand = new Hand();
		hand.setCards(card);
		try {
			assertTrue(PokerUtil.isStraightCase(hand));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testIsFlushCase() {

		Card card[] = new Card[] { new Card("Spades", 11), new Card("Spades", 8), new Card("Spades", 10),
				new Card("Spades", 7), new Card("Spades", 9) };

		Hand hand = new Hand();
		hand.setCards(card);

		try {
			assertTrue(PokerUtil.isFlushCase(hand));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testIsAFourOfAKindCase() {
		Card card[] = new Card[] { new Card("Spades", 13), new Card("Hearts", 12), new Card("Spades", 12),
				new Card("Clubs", 12), new Card("Diamonds", 12) };

		Hand hand = new Hand();
		hand.setCards(card);
		try {
			assertTrue(PokerUtil.isAFourOfAKindCase(hand));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testIsStraightFlushCase() {

		Card card[] = new Card[] { new Card("Spades", 9), new Card("Spades", 10), new Card("Spades", 11),
				new Card("Spades", 12), new Card("Spades", 13) };

		Hand hand = new Hand();
		hand.setCards(card);
		try {
			assertTrue(PokerUtil.isStraightFlushCase(hand));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testIsPairCase() {
		Card card[] = new Card[] { new Card("Spades", 11), new Card("Spades", 7), new Card("Hearts", 12),
				new Card("Spades", 9), new Card("Clubs", 11) };

		Hand hand = new Hand();
		hand.setCards(card);
		try {
			assertTrue(PokerUtil.isPairCase(hand));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testIsTwoPairCase() {
		Card card[] = new Card[] { new Card("Spades", 2), new Card("Spades", 3), new Card("Hearts", 3),
				new Card("Spades", 5), new Card("Clubs", 5) };

		Hand hand = new Hand();
		hand.setCards(card);

		try {
			assertTrue(PokerUtil.isTwoPairCase(hand));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPlay() {
		
		Map<String,String[]> mpPlayerData =  new HashMap<String,String[]>();
		mpPlayerData.put("Player1Input1", new String[] {"SQ"});
		mpPlayerData.put("Player1Input2", new String[] {"D10"});
		mpPlayerData.put("Player1Input3", new String[] {"HJ"});
		mpPlayerData.put("Player1Input4", new String[] {"S9"});
		mpPlayerData.put("Player1Input5", new String[] {"C8"});
		
		mpPlayerData.put("Player2Input1", new String[] {"HJ"});
		mpPlayerData.put("Player2Input2", new String[] {"SJ"});
		mpPlayerData.put("Player2Input3", new String[] {"CJ"});
		mpPlayerData.put("Player2Input4", new String[] {"S2"});
		mpPlayerData.put("Player2Input5", new String[] {"C4"});
		
		
		try {
			Assert.assertTrue((PokerUtil.play(mpPlayerData)).contains("Player1"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
}
