package com.poker.task;

public class Card {

	private String suit;
	private Rank rank;

	public Card() {

	}

	public Card(String suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return suit + " " + rank;
	}

}