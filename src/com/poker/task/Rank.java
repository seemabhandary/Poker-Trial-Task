package com.poker.task;

public class Rank {

	private int evalRank;
	private String inputRank;

	public Rank() {
	}

	public Rank(int value, String rank) {
		this.evalRank = value;
		this.inputRank = rank;
	}

	public int getValue() {
		return evalRank;
	}

	public void setValue(int value) {
		this.evalRank = value;
	}

	public String getRank() {
		return inputRank;
	}

	public void setRank(String rank) {
		this.inputRank = rank;
	}

	@Override
	public String toString() {
		String stg = "";
		stg += this.inputRank + "(" + this.evalRank + ")";
		return stg;
	}

}