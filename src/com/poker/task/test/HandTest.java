package com.poker.task.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.poker.task.Card;
import com.poker.task.Hand;
import com.poker.task.Rank;

public class HandTest {

	@Test
	public void testHand() {
		fail("Not yet implemented");
	}

	@Test
	public void testHandCardArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHand() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHand() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintHand() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testisStraightCase() {

		Card card[] = new Card[] { new Card("Diamond", new Rank(11, "J")), new Card("Club", new Rank(8, "8")),
				new Card("Spades", new Rank(10, "10")), new Card("Club", new Rank(7, "7")),
				new Card("Hearts", new Rank(9, "9")) };

		Hand hand = new Hand(card);

		assertTrue(hand.isStraightCase());
	}

	@Test
	public void testIsFlushCase() {

		Card card[] = new Card[] { new Card("Spades", new Rank(11, "J")), new Card("Spades", new Rank(8, "8")),
				new Card("Spades", new Rank(10, "10")), new Card("Spades", new Rank(7, "7")),
				new Card("Spades", new Rank(9, "9")) };

		Hand hand = new Hand(card);

		assertTrue(hand.isFlushCase());

	}

	@Test
	public void testIsStraightFlushCase() {

		Card card[] = new Card[] { new Card("Spades", new Rank(10, "10")), new Card("Spades", new Rank(8, "8")),
				new Card("Spades", new Rank(6, "6")), new Card("Spades", new Rank(7, "7")),
				new Card("Spades", new Rank(9, "9")) };

		Hand hand = new Hand(card);

		assertTrue(hand.isStraightFlushCase());
	}

}
