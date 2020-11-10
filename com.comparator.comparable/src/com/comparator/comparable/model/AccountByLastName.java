package com.comparator.comparable.model;

import java.util.Comparator;

public class AccountByLastName implements Comparator<Account>{

	@Override
	public int compare(Account o1, Account o2) {
		return o1.getLastName().compareTo(o2.getLastName());
	}
}


