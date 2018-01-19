package com.poker.task;

import java.util.Arrays;
import java.util.Comparator;

public class Hand {

	private Card[] hand = new Card[5];

	public Hand(Card[] card) {
		this.hand = card;
	}

	public Card[] getHand() {
		return hand;
	}

	public void setHand(Card[] hand) {
		this.hand = hand;
	}

	
	public boolean isStraightCase() {
		Card[] cards = hand;
		Arrays.sort(cards, rankCompare);
		int noOfCards = 0;
		int pos = 0;
		boolean isStraightCase = false;
		while (pos < cards.length - 1 && !isStraightCase) {
			if (cards[pos + 1].getRank().getValue() - cards[pos].getRank().getValue() == 1) {
				noOfCards++;
				if (noOfCards == 4) {
					isStraightCase = true;
				} else {
					pos++;
				}
			} else {
				noOfCards = 0;
				pos++;
			}
		}
		return isStraightCase;
	}

	public boolean isFlushCase() {
		Card[] cards = hand;
		int noOfClubs = 0;
		int noOfSpades = 0;
		int noOfHearts = 0;
		int noOfDiamonds = 0;
		for (Card c : cards) {
			switch (c.getSuit()) {
			case "Hearts":
				noOfHearts++;
				break;
			case "Spades":
				noOfSpades++;
				break;
			case "Clubs":
				noOfClubs++;
				break;
			case "Diamonds":
				noOfDiamonds++;
				break;
			}
		}
		return (noOfClubs == 5 || noOfSpades == 5 || noOfHearts == 5 || noOfDiamonds == 5);
	}

	
	public Comparator<Card> rankCompare = (Card left, Card right) -> {
		if (left.getRank().getValue() < right.getRank().getValue()) {
			return -1;
		} else {
			return 1;
		}
	};

	public boolean isStraightFlushCase() {
		if (isStraightCase() && isFlushCase()) {
			return true;
		} else {
			return false;
		}
	}

}