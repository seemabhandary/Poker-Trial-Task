package com.poker.task;

import java.util.ArrayList;
import java.util.List;

public class PokerUtil {

	public static Card[] initializeHand(List<String> lstHand) {

		List<Card> lstCards = new ArrayList<Card>();

		for (String cardDetails : lstHand) {
			String rank = cardDetails.substring(1);
			String suit = determineSuit(cardDetails.substring(0, 1));
			int value = determineValue(rank);

			Rank rankObj = new Rank(value, rank);
			Card card = new Card(suit, rankObj);
			lstCards.add(card);
		}

		return lstCards.toArray(new Card[lstCards.size()]);
	}

	public static String determineSuit(String sInput) {

		String suit = null;

		switch (sInput) {
		case "S":
			suit = "Spades";
			break;
		case "H":
			suit = "Hearts";
			break;
		case "D":
			suit = "Diamonds";
			break;
		case "C":
			suit = "Clubs";
			break;
		}
		return suit;
	}

	public static int determineValue(String sInput) {

		int value = 0;

		switch (sInput) {
		case "J":
			value = 11;
			break;
		case "Q":
			value = 12;
			break;
		case "K":
			value = 13;
			break;
		case "A":
			value = 14;
			break;
		default:
			value = new Integer(sInput).intValue();
			break;
		}

		return value;
	}

	public static String play(List lstHand1, List lstHand2) {

		Card[] hand1Cards = initializeHand(lstHand1);

	//	Card[] hand2 = initializeHand(lstHand2);

		Hand hand = new Hand(hand1Cards);
		
	
		
	//	boolean b = hand.isAStraight();
		
	//	boolean c = hand.isAFlush();
		
		boolean b = hand.isStraightFlushCase();
		

		return "Winning hand is ---- ";

	}

}
