package com.cro.model.user;

public enum Gender {
	MAN{
		public String getName(){return "ÄÐ";}
	},WOMEN{
		public String getName(){return "Å®";}
	};
	public abstract String getName();
}