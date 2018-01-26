package com.poker.task;

/**
 * Card bean class representing the 5 cards with each player
 * 
 * @author Seema Bhandary
 *
 */
public class Card {

	private String suit;
	private int rank;
	
	public Card() {

	}

	public Card(String suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return suit + " " + rank;
	}

}