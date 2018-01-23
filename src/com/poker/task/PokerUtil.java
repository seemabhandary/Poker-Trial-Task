package com.poker.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PokerUtil {

	public PokerUtil() {

	}

	public static Hand initializeHand(List<String> lstHand) {

		List<Integer> listToSortWithoutSuit = new ArrayList<Integer>();
		for (String cardDetails : lstHand) {
			listToSortWithoutSuit.add(new Integer(determineValue(cardDetails.substring(1))));
		}

		Collections.sort(listToSortWithoutSuit);
		List<Card> lstCards = new ArrayList<Card>();
		for (Integer valueOnly : listToSortWithoutSuit) {
			for (String valueWithSuit : lstHand) {
				String suit = null;
				String suitPrefix = valueWithSuit.substring(0, 1);
				suit = determineSuit(suitPrefix);
				if (valueWithSuit.contains(valueOnly.toString())) {					
					Card card = new Card(suit, valueOnly);
					lstCards.add(card);
				} else if (valueOnly == 11 || valueOnly == 12 || valueOnly == 13 || valueOnly == 14) {
					String originalValue = determineOriginalValue(valueOnly);
					String originalValueWithSuit = suitPrefix + originalValue;
					if (valueWithSuit.equals(originalValueWithSuit)) {						
						Card card = new Card(suit, valueOnly);
						lstCards.add(card);
					}

				}
			}
		}
		Hand hand = new Hand();
		hand.setCards(lstCards.toArray(new Card[lstCards.size()]));

		return hand;
	}

	public static String determineSuit(String sInput) {

		String suit = null;

		switch (sInput) {
		case "S":
			suit = PokerConstants.SPADES;
			break;
		case "H":
			suit = PokerConstants.HEARTS;
			break;
		case "D":
			suit = PokerConstants.DIAMONDS;
			break;
		case "C":
			suit = PokerConstants.CLUBS;
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

	public static String determineOriginalValue(int input) {

		String value = null;

		switch (input) {
		case 11:
			value = "J";
			break;
		case 12:
			value = "Q";
			break;
		case 13:
			value = "K";
			break;
		case 14:
			value = "A";
			break;
		}

		return value;
	}

	public static String play(Map<String, String[]> mpPlayerData) {

		Set<String> setKeyNames = mpPlayerData.keySet();

		Set<String> setKeyPlayerNames = new HashSet<String>();

		for (String string : setKeyNames) {
			String[] arrKeyName = string.split("Input");
			setKeyPlayerNames.add(arrKeyName[0]);
		}

		Map<String, List<String>> mpPlayerMap = new HashMap<String, List<String>>();
		List<String> listCards = null;
		for (Map.Entry<String, String[]> entry : mpPlayerData.entrySet()) {
			for (String sPlayerName : setKeyPlayerNames) {

				if (entry.getKey().contains(sPlayerName)) {
					String sValue = entry.getValue()[0];

					if (mpPlayerMap.containsKey(sPlayerName)) {
						listCards = mpPlayerMap.get(sPlayerName);
					} else {
						listCards = new ArrayList<String>();
					}

					listCards.add(sValue);
					mpPlayerMap.put(sPlayerName, listCards);
				}
			}
		}

		// initialize the data for each player hand
		List<Hand> lstPlayers = new ArrayList<Hand>();

		for (Map.Entry<String, List<String>> entry : mpPlayerMap.entrySet()) {
			Hand hand = initializeHand(entry.getValue());
			hand.setRank(findPlayerRank(hand));
			hand.setPlayerName(entry.getKey().replaceAll("Hand", "Player "));
			lstPlayers.add(hand);
		}

		List<Integer> lstRanksOfPlayers = new ArrayList<Integer>();
		for (int iPlayer = 0; iPlayer < lstPlayers.size(); iPlayer++) {
			lstRanksOfPlayers.add(new Integer(lstPlayers.get(iPlayer).getRank()));
		}

		int iMaxValue = Collections.max(lstRanksOfPlayers).intValue();
		String pokerCase = null, playerName = null;
		for (int iPlayer = 0; iPlayer < lstPlayers.size(); iPlayer++) {
			Hand player = lstPlayers.get(iPlayer);
			if (player.getRank() == iMaxValue) {
				pokerCase = player.getPokerCase();
				playerName = player.getPlayerName();
			}
		}

		return playerName + " wins with the case : " + pokerCase;

	}

	public static boolean isThreeOfAKindCase(Hand hand) {
		boolean isThreeOfAKind = false;
		Card[] cards = hand.getCards();
		if (((cards[0].getRank() == cards[1].getRank()) && (cards[0].getRank() == cards[2].getRank()))
				|| ((cards[1].getRank() == cards[2].getRank()) && (cards[1].getRank() == cards[3].getRank()))
				|| ((cards[2].getRank() == cards[3].getRank()) && (cards[2].getRank() == cards[4].getRank()))) {
			isThreeOfAKind = true;
		}

		return isThreeOfAKind;
	}

	public static boolean isTwoPairCase(Hand hand) {
		int numberOfPairs = 0;
		boolean isTwoPair = false;
		Card[] cards = hand.getCards();
		for (int cardCounter = 0; cardCounter < cards.length - 1; cardCounter++) {
			if (!isTwoPair) {
				for (int nextCardCounter = cardCounter + 1; nextCardCounter < cards.length; nextCardCounter++) {
					if (cards[cardCounter].getRank() == cards[nextCardCounter].getRank()) {
						numberOfPairs++;
						if (numberOfPairs == 2) {
							isTwoPair = true;
							break;
						} else if (numberOfPairs == 1) {
							cardCounter++;
							break;
						}
					}
				}
			}
		}

		return isTwoPair;
	}

	public static boolean isPairCase(Hand hand) {
		boolean isPair = false;
		Card[] cards = hand.getCards();
		for (int cardCounter = 0; cardCounter < cards.length - 1; cardCounter++) {
			if (!isPair) {
				for (int nextCardCounter = cardCounter + 1; nextCardCounter < cards.length; nextCardCounter++) {
					if (cards[cardCounter].getRank() == cards[nextCardCounter].getRank()) {
						isPair = true;
						break;
					}

				}

			}
		}
		return isPair;
	}

	public static boolean isAFullHouseCase(Hand hand) {
		if (isThreeOfAKindCase(hand) && isPairCase(hand)) {
			return true;
		} else
			return false;

	}

	public static boolean isAFourOfAKindCase(Hand hand) {
		boolean isFourOfAKind = false;
		Card[] cards = hand.getCards();
		if (cards.length == 5) {
			// for 4 of a kind in a sorted array, it is mandatory that the 1st is same as
			// 2nd,3rd, 4th or 2nd is same as 3rd, 4th, 5th
			if (((cards[0].getRank() == cards[1].getRank()) && (cards[0].getRank() == cards[2].getRank())
					&& (cards[0].getRank() == cards[3].getRank()))
					|| ((cards[1].getRank() == cards[2].getRank()) && (cards[1].getRank() == cards[3].getRank())
							&& (cards[1].getRank() == cards[4].getRank()))) {
				isFourOfAKind = true;
			}

		} else if (cards.length == 7) {
			if (((cards[0].getRank() == cards[1].getRank()) && (cards[0].getRank() == cards[2].getRank())
					&& (cards[0].getRank() == cards[3].getRank())
					|| ((cards[1].getRank() == cards[2].getRank()) && (cards[1].getRank() == cards[3].getRank())
							&& (cards[1].getRank() == cards[4].getRank()))
					|| ((cards[2].getRank() == cards[3].getRank()) && (cards[2].getRank() == cards[4].getRank())
							&& (cards[2].getRank() == cards[5].getRank()))
					|| ((cards[3].getRank() == cards[4].getRank()) && (cards[3].getRank() == cards[5].getRank())
							&& (cards[3].getRank() == cards[6].getRank())))) {

				isFourOfAKind = true;

			}

		}

		return isFourOfAKind;

	}

	public static Card getHighCard(Hand hand) {
		Card[] cards = hand.getCards();
		return cards[cards.length - 1];
	}

	public static Card getHandHighCard(Hand hand) {
		Card[] cards = hand.getCards();
		return cards[cards.length - 1];
	}

	public static boolean isStraightCase(Hand hand) {
		Card[] cards = hand.getCards();
		int noOfCards = 0;
		int position = 0;
		boolean isStraightCase = false;
		while (position < cards.length - 1 && !isStraightCase) {
			if (cards[position + 1].getRank() - cards[position].getRank() == 1) {
				noOfCards++;
				if (noOfCards == 4) {
					isStraightCase = true;
				} else {
					position++;
				}
			} else {
				noOfCards = 0;
				position++;
			}
		}
		return isStraightCase;
	}

	public static boolean isFlushCase(Hand hand) {
		Card[] cards = hand.getCards();
		int noOfClubs = 0;
		int noOfSpades = 0;
		int noOfHearts = 0;
		int noOfDiamonds = 0;
		for (Card c : cards) {
			switch (c.getSuit()) {
			case PokerConstants.HEARTS:
				noOfHearts++;
				break;
			case PokerConstants.SPADES:
				noOfSpades++;
				break;
			case PokerConstants.CLUBS:
				noOfClubs++;
				break;
			case PokerConstants.DIAMONDS:
				noOfDiamonds++;
				break;
			}
		}
		return (noOfClubs == 5 || noOfSpades == 5 || noOfHearts == 5 || noOfDiamonds == 5);
	}

	public static boolean isStraightFlushCase(Hand hand) {
		if (isStraightCase(hand) && isFlushCase(hand)) {
			return true;
		} else {
			return false;
		}
	}

	public static int findPlayerRank(Hand hand) {
		if (isStraightFlushCase(hand)) {
			hand.setPokerCase(PokerConstants.STRAIGHT_FLUSH_CASE);
			return PokerConstants.NINE;
		} else if (isAFourOfAKindCase(hand)) {
			hand.setPokerCase(PokerConstants.FOUR_OF_KIND_CASE);
			return PokerConstants.EIGHT;
		} else if (isAFullHouseCase(hand)) {
			hand.setPokerCase(PokerConstants.FULL_HOUSE_CASE);
			return PokerConstants.SEVEN;
		} else if (isFlushCase(hand)) {
			hand.setPokerCase(PokerConstants.FLUSH_CASE);
			return PokerConstants.SIX;
		} else if (isStraightCase(hand)) {
			hand.setPokerCase(PokerConstants.STRAIGHT_CASE);
			return PokerConstants.FIVE;
		} else if (isThreeOfAKindCase(hand)) {
			hand.setPokerCase(PokerConstants.THREE_OF_KIND_CASE);
			return PokerConstants.FOUR;
		} else if (isTwoPairCase(hand)) {
			hand.setPokerCase(PokerConstants.TWO_PAIR_CASE);
			return PokerConstants.THREE;
		} else if (isPairCase(hand)) {
			hand.setPokerCase(PokerConstants.PAIR_CASE);
			return PokerConstants.TWO;
		} else {
			hand.setPokerCase(PokerConstants.HIGH_CARD_CASE);
			return PokerConstants.ONE;
		}
	}

}
