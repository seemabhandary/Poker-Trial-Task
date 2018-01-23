package com.poker.task;

public class Hand {

	public Hand() {

	}

	private int rank;
	private Card[] cards;
	private String pokerCase;
	private String playerName;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPokerCase() {
		return pokerCase;
	}

	public void setPokerCase(String pokerCase) {
		this.pokerCase = pokerCase;
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}