package com.poker.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class PokerUtil {

	/**
	 * Initializes the data in Hand class for each player
	 * 
	 * @param lstHand
	 *            - list of cards
	 * @return Hand - hand bean containing the intialized data
	 */
	public static Hand initializeHand(List<String> lstHand) {

		List<String> listOfCardsWithRanks = new ArrayList<String>();
		for (String originalCard : lstHand) {
			listOfCardsWithRanks.add(originalCard.substring(0, 1) + determineValue(originalCard.substring(1)));
		}

		Collections.sort(listOfCardsWithRanks);

		List<Card> lstCards = new ArrayList<Card>();
		for (String cardWithRank : listOfCardsWithRanks) {
			Card card = new Card(determineSuit(cardWithRank.substring(0, 1)),
					determineValue(cardWithRank.substring(1)));
			lstCards.add(card);
		}

		Hand hand = new Hand();
		hand.setCards(lstCards.toArray(new Card[lstCards.size()]));

		return hand;
	}

	/**
	 * To get the corresponding suit for every shortform (S,C,H,D) in the input
	 * format
	 * 
	 * @param sInput
	 * @return String
	 */
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

	/**
	 * To get the corresponding value for J,Q,K,A cards in any suit
	 * 
	 * @param sInput
	 * @return int
	 */
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

	/**
	 * To get the corresponding Original A,K,Q,J for value given in method
	 * determineValue()
	 * 
	 * @param sInput
	 * @return String
	 */
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

	/**
	 * Main method from which the user entered data is processed to find the winning
	 * player
	 * 
	 * @param mpPlayerData
	 *            - input request raw data from the User input
	 * @return String - Winning message with player name and poker case details
	 * @throws Exception
	 */
	public static String play(Map<String, String[]> mpPlayerData) throws Exception {

		try {
			// validate the input data from the UI first
			validateInputData(mpPlayerData);

			// create a data structure to group the input per player
			Map<String, List<String>> mpPlayerMap = createPlayerDataStructure(mpPlayerData);

			// initialize the data for each player hand and find the rank (poker case) of
			// every player
			List<Hand> lstPlayers = new ArrayList<Hand>();

			for (Map.Entry<String, List<String>> entry : mpPlayerMap.entrySet()) {
				Hand hand = initializeHand(entry.getValue());
				hand.setRank(findPlayerRank(hand));
				hand.setPlayerName(entry.getKey());
				lstPlayers.add(hand);
			}

			// return the winning player
			return getWinningPlayer(lstPlayers);
		} catch (Exception e) {

			return "Error :: " + e.getMessage();
		}

	}

	/**
	 * Validates every input from the user and informs even if one error is found
	 * 
	 * @param mpPlayerData
	 *            - raw input data from the UI
	 * @throws Exception
	 *             - thrown when the input format is not correct
	 */
	public static void validateInputData(Map<String, String[]> mpPlayerData) throws Exception {

		String mapValue = null, stringPrefix;
		char prefix;
		boolean hasNonAlphanumeric, isNumeric, isAKQJ, isDSHC;
		int valueOfSuffix;
		Pattern pAlphaNumeric = Pattern.compile("[^A-Z0-9]");
		Pattern pDHSC = Pattern.compile("[DHSC]");
		Pattern pAKQJ = Pattern.compile("[AKQJ]");
		Pattern pNumeric = Pattern.compile("[0-9]");

		for (Map.Entry<String, String[]> entry : mpPlayerData.entrySet()) {
			mapValue = entry.getValue()[0];
			hasNonAlphanumeric = pAlphaNumeric.matcher(mapValue).find();
			if (!hasNonAlphanumeric) {
				prefix = mapValue.charAt(0);
				stringPrefix = new Character(prefix).toString();
				isDSHC = pDHSC.matcher(stringPrefix).find();
				if (!isDSHC) {
					throw new Exception(PokerConstants.INPUT_VALIDATION);
				} else {
					String sValue = mapValue.split(stringPrefix)[1];
					isNumeric = pNumeric.matcher(sValue).find();
					isAKQJ = pAKQJ.matcher(sValue).find();

					if (isNumeric) {
						valueOfSuffix = new Integer(sValue).intValue();
						if (valueOfSuffix > 10 || valueOfSuffix == 1) {
							throw new Exception(PokerConstants.INPUT_VALIDATION);
						}
					} else if (!isAKQJ) {
						throw new Exception(PokerConstants.INPUT_VALIDATION);
					}
				}
			} else {
				throw new Exception(PokerConstants.INPUT_VALIDATION);
			}
		}

	}

	/**
	 * Create data structure by grouping the data of every player together
	 * 
	 * @param mpPlayerData
	 *            - input request raw data from the User input
	 * @return Map - contains Player Name as key and list of cards as values
	 */
	public static Map<String, List<String>> createPlayerDataStructure(Map<String, String[]> mpPlayerData)
			throws Exception {

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
		return mpPlayerMap;
	}

	/**
	 * Gets the winning player and the pokercase with which player wins
	 * 
	 * @param lstPlayers
	 *            - list containing handbean objects
	 * @return String - message with winning player name and poker case
	 */
	public static String getWinningPlayer(List<Hand> lstPlayers) throws Exception {

		String pokerCase;
		String playerName;
		List<Hand> listOfPlayersWithSameRank = getListOfPlayersWithSameRank(lstPlayers);

		if (listOfPlayersWithSameRank.size() > 1) {
			// Since all players have same rank, we can take the first player for finding
			// the rank
			Hand hand = listOfPlayersWithSameRank.get(0);
			Map<String, Integer> mpHighCardData = null;

			int rankOfPlayer = hand.getRank();
			Map<String, List<Integer>> mpMasterData = getMapWithPlayerRanksWithoutSuit(listOfPlayersWithSameRank);

			// if rank =1 (High card) , rank = 9 (Straight Flush) or rank=5 (Straight) or
			// rank=6 (Flush) then determine the highest card
			if (rankOfPlayer == PokerConstants.ONE || rankOfPlayer == PokerConstants.NINE
					|| rankOfPlayer == PokerConstants.FIVE || rankOfPlayer == PokerConstants.SIX) {
				mpHighCardData = getHighCardData(mpMasterData);
			}
			// For pair case , 3 of a kind case, 4 of a kind case , full house , 2 pairs
			else if (rankOfPlayer == PokerConstants.TWO || rankOfPlayer == PokerConstants.FOUR
					|| rankOfPlayer == PokerConstants.SEVEN || rankOfPlayer == PokerConstants.EIGHT
					|| rankOfPlayer == PokerConstants.THREE) {
				mpHighCardData = getMapWithHighCardData(mpMasterData);
			}

			// Map will always have only 1 entry because only 1 player can be the winner
			playerName = mpHighCardData.keySet().iterator().next().toString();
			pokerCase = findCaseWithRankValue(rankOfPlayer);

		} else {
			Hand player = listOfPlayersWithSameRank.get(0);
			pokerCase = player.getPokerCase();
			playerName = player.getPlayerName();
		}
		return playerName + " wins with the case : " + pokerCase;
	}

	/**
	 * To get the list of players with same ranks or having same poker case
	 * 
	 * @param lstPlayers
	 *            - List containing handbean objects
	 * @return List - list containing handbean objects
	 */
	private static List<Hand> getListOfPlayersWithSameRank(List<Hand> lstPlayers) throws Exception {
		List<Integer> lstRanksOfPlayers = new ArrayList<Integer>();
		for (int iPlayer = 0; iPlayer < lstPlayers.size(); iPlayer++) {
			lstRanksOfPlayers.add(new Integer(lstPlayers.get(iPlayer).getRank()));
		}

		int iMaxValue = Collections.max(lstRanksOfPlayers).intValue();
		List<Hand> listOfPlayersWithSameRank = new ArrayList<Hand>();
		for (int iPlayer = 0; iPlayer < lstPlayers.size(); iPlayer++) {
			if (lstPlayers.get(iPlayer).getRank() == iMaxValue) {
				listOfPlayersWithSameRank.add(lstPlayers.get(iPlayer));
			}
		}
		return listOfPlayersWithSameRank;
	}

	/**
	 * To get a map with player name and highest value of the card with the player
	 * 
	 * @param mpMasterData
	 *            - map with player name and list of cards with the player
	 * @return Map - map with player name and highest value of the card with the
	 *         player
	 */
	public static Map<String, Integer> getMapWithHighCardData(Map<String, List<Integer>> mpMasterData)
			throws Exception {
		int tempRank = 0;
		Map<String, Integer> mpHighCardData = new HashMap<String, Integer>();
		for (Map.Entry<String, List<Integer>> entry : mpMasterData.entrySet()) {
			List<Integer> listInt = entry.getValue();
			// Get unique elements in the list
			Set<Integer> set = new HashSet<>(listInt);
			int playerMaxRank = Collections.max(set);
			listInt.clear();
			listInt.addAll(set);

			if (playerMaxRank > tempRank) {
				tempRank = playerMaxRank;
				mpHighCardData.clear();
				mpHighCardData.put(entry.getKey(), tempRank);
			} else if (playerMaxRank == tempRank) {
				mpHighCardData = getHighCardData(mpMasterData);
			}
		}
		return mpHighCardData;
	}

	/**
	 * To find the pokercase based on the player rank
	 * 
	 * @param rankOfPlayer
	 *            - integer value
	 * @return String - poker case
	 */
	public static String findCaseWithRankValue(int rankOfPlayer) throws Exception {
		String pokerCase = null;
		switch (rankOfPlayer) {
		case PokerConstants.NINE:
			pokerCase = PokerConstants.STRAIGHT_FLUSH_CASE;
			break;
		case PokerConstants.EIGHT:
			pokerCase = PokerConstants.FOUR_OF_KIND_CASE;
			break;
		case PokerConstants.SEVEN:
			pokerCase = PokerConstants.FULL_HOUSE_CASE;
			break;
		case PokerConstants.SIX:
			pokerCase = PokerConstants.FLUSH_CASE;
			break;
		case PokerConstants.FIVE:
			pokerCase = PokerConstants.STRAIGHT_CASE;
			break;
		case PokerConstants.FOUR:
			pokerCase = PokerConstants.THREE_OF_KIND_CASE;
			break;
		case PokerConstants.THREE:
			pokerCase = PokerConstants.TWO_PAIR_CASE;
			break;
		case PokerConstants.TWO:
			pokerCase = PokerConstants.PAIR_CASE;
			break;
		case PokerConstants.ONE:
			pokerCase = PokerConstants.HIGH_CARD_CASE;
			break;
		}

		return pokerCase;
	}

	/**
	 * Remove the same ranks in the list of the cards
	 * 
	 * @param mpMasterData
	 *            - map with player name and list of cards
	 * @param integer
	 *            - rank to be removed from the list of cards
	 * @return Map - Map with playername and list of cards without same ranks
	 */
	private static Map<String, List<Integer>> getMapPostRemovalOfSameRanksInList(
			Map<String, List<Integer>> mpMasterData, Integer integer) throws Exception {
		Map<String, List<Integer>> mpCloneMasterData = new HashMap<String, List<Integer>>();
		mpCloneMasterData.putAll(mpMasterData);

		// remove integer value from all the lists in the map
		for (Map.Entry<String, List<Integer>> entryValue : mpCloneMasterData.entrySet()) {
			List<Integer> listIntValue = entryValue.getValue();
			listIntValue.removeIf(integer::equals);
		}

		return mpCloneMasterData;
	}

	/**
	 * To get a data structure with playername and list of card values only without
	 * suit details
	 * 
	 * @param listOfPlayersWithSameRank
	 *            - list containing hand bean objects
	 * @return - map containing player data and list without suit details
	 */
	private static Map<String, List<Integer>> getMapWithPlayerRanksWithoutSuit(List<Hand> listOfPlayersWithSameRank)
			throws Exception {
		Map<String, List<Integer>> mpMasterData = new HashMap<String, List<Integer>>();
		for (Hand player : listOfPlayersWithSameRank) {
			mpMasterData.put(player.getPlayerName(), getListWithoutSuit(Arrays.asList(player.getCards())));
		}
		return mpMasterData;
	}

	/**
	 * To get the player with the highest card details when ranks of the players are
	 * same
	 * 
	 * @param mpMasterData
	 *            - map containing player name and list of card values without suit
	 *            details
	 * @return Map - map containing the name and highest card with the player among
	 *         many players
	 */
	public static Map<String, Integer> getHighCardData(Map<String, List<Integer>> mpMasterData) throws Exception {
		int tempMaxRankInPlayer = 0;
		Map<String, Integer> highCardDataMap = new HashMap<String, Integer>();
		String playerName;
		for (Map.Entry<String, List<Integer>> entry : mpMasterData.entrySet()) {
			List<Integer> listInt = entry.getValue();

			int playerMaxRank = Collections.max(listInt).intValue();

			if (tempMaxRankInPlayer < playerMaxRank) {
				tempMaxRankInPlayer = playerMaxRank;
				playerName = entry.getKey();
				highCardDataMap.clear();
				highCardDataMap.put(playerName, new Integer(tempMaxRankInPlayer));
			} else if (tempMaxRankInPlayer == playerMaxRank) {
				Map<String, List<Integer>> mpCloneMasterData = getMapPostRemovalOfSameRanksInList(mpMasterData,
						new Integer(tempMaxRankInPlayer));

				// Again find the highest card in the remaining list iteratively
				highCardDataMap = getHighCardData(mpCloneMasterData);
			}

		}
		return highCardDataMap;

	}

	/**
	 * To get a plain simple list of integers for easier comparison , sorting and
	 * finding the maximum number
	 * 
	 * @param listOfCards
	 *            - list of card beans with suit details
	 * @return List - list of values without suit details
	 */
	public static List<Integer> getListWithoutSuit(List<Card> listOfCards) throws Exception {
		List<Integer> listToSortWithoutSuit = new ArrayList<Integer>();
		for (Card cardDetails : listOfCards) {
			listToSortWithoutSuit.add(new Integer(cardDetails.getRank()));
		}
		return listToSortWithoutSuit;
	}

	/**
	 * Checks if the player's case is a Three of a kind Case
	 * 
	 * @param hand
	 * @return boolean - true or false
	 */
	public static boolean isThreeOfAKindCase(Hand hand) throws Exception {
		List<Integer> listWithOnlyRanks = createListWithOnlyRanks(hand);

		return findIfFrequencyMatch(listWithOnlyRanks, 3);
	}

	/**
	 * Checks if the player's case is a Two pair case
	 * 
	 * @param hand
	 * @return boolean - true or false
	 */

	public static boolean isTwoPairCase(Hand hand) throws Exception {
		List<Integer> listWithOnlyRanks = createListWithOnlyRanks(hand);
		int numberOfPairs = 0;
		boolean isTwoPair = false;
		int checkedIntValuePrev = 0;
		for (Integer integer : listWithOnlyRanks) {

			int frequency = Collections.frequency(listWithOnlyRanks, integer);

			if (frequency == 2 && checkedIntValuePrev != integer.intValue()) {
				checkedIntValuePrev = integer.intValue();
				numberOfPairs++;
				if (numberOfPairs == 2) {
					isTwoPair = true;
					break;
				}
			}

		}

		return isTwoPair;
	}

	/**
	 * Checks if the player's case is a Pair Case
	 * 
	 * @param hand
	 * @return boolean - true or false
	 */

	public static boolean isPairCase(Hand hand) throws Exception {
		List<Integer> listWithOnlyRanks = createListWithOnlyRanks(hand);
		return findIfFrequencyMatch(listWithOnlyRanks, 2);
	}

	/**
	 * Checks if the player's case is a Full House Case
	 * 
	 * @param hand
	 * @return boolean - true or false
	 */

	public static boolean isAFullHouseCase(Hand hand) throws Exception {
		if (isThreeOfAKindCase(hand) && isPairCase(hand)) {
			return true;
		} else
			return false;

	}

	/**
	 * Checks if the player's case is a Four of a kind Case
	 * 
	 * @param hand
	 * @return boolean - true or false
	 */
	public static boolean isAFourOfAKindCase(Hand hand) throws Exception {
		// boolean isFourOfAKind = false;
		List<Integer> listWithOnlyRanks = createListWithOnlyRanks(hand);
		// Card[] cards = hand.getCards();
		// boolean isFourOfAKind =

		/*
		 * // for 4 of a kind in a sorted array, it is mandatory that the 1st is same as
		 * // 2nd,3rd, 4th or 2nd is same as 3rd, 4th, 5th if
		 * (((listWithOnlyRanks.get(0) ==listWithOnlyRanks.get(1)) &&
		 * (listWithOnlyRanks.get(0) == listWithOnlyRanks.get(2)) && (cards[0].getRank()
		 * == cards[3].getRank())) || ((cards[1].getRank() == cards[2].getRank()) &&
		 * (cards[1].getRank() == cards[3].getRank()) && (cards[1].getRank() ==
		 * cards[4].getRank()))) { isFourOfAKind = true;
		 * 
		 * 
		 * }
		 */

		return findIfFrequencyMatch(listWithOnlyRanks, 4);

	}

	/**
	 * @param isFourOfAKind
	 * @param listWithOnlyRanks
	 * @return
	 */
	public static boolean findIfFrequencyMatch(List<Integer> sortedListWithOnlyRanks, int freqToMatch)
			throws Exception {
		int frequency = 0;
		boolean frequencyMatched = false;
		for (Integer integer : sortedListWithOnlyRanks) {

			frequency = Collections.frequency(sortedListWithOnlyRanks, integer);

			if (frequency == freqToMatch) {
				frequencyMatched = true;
				break;
			}

		}
		return frequencyMatched;
	}

	/**
	 * Checks if the player's case is a Straight Case
	 * 
	 * @param hand
	 * @return boolean - true or false
	 */
	public static boolean isStraightCase(Hand hand) throws Exception {

		List<Integer> listWithOnlyRanks = createListWithOnlyRanks(hand);

		// Card[] cards = hand.getCards();
		int noOfCards = 0;
		int position = 0;
		boolean isStraightCase = false;
		while (position < listWithOnlyRanks.size() - 1 && !isStraightCase) {
			if (listWithOnlyRanks.get(position + 1) - listWithOnlyRanks.get(position) == 1) {
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

	public static List<Integer> createListWithOnlyRanks(Hand hand) throws Exception {
		Card[] cards = hand.getCards();

		List<Integer> listWithOnlyRanks = new ArrayList<Integer>();

		for (Card stringCard : cards) {
			listWithOnlyRanks.add(new Integer(determineValue((new Integer(stringCard.getRank())).toString())));
		}

		Collections.sort(listWithOnlyRanks);

		// TODO Auto-generated method stub
		return listWithOnlyRanks;
	}

	/**
	 * Checks if the player's case is a Flush Case
	 * 
	 * @param hand
	 * @return boolean - true or false
	 */
	public static boolean isFlushCase(Hand hand) throws Exception {
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

	/**
	 * Checks if the player's case is a Straight Flush Case
	 * 
	 * @param hand
	 * @return boolean - true or false
	 */
	public static boolean isStraightFlushCase(Hand hand) throws Exception {
		if (isStraightCase(hand) && isFlushCase(hand)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * To find the poker case and rank of each player
	 * 
	 * @param hand
	 *            - Player data bean
	 * @return int - Player Rank
	 */
	public static int findPlayerRank(Hand hand) throws Exception {
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
