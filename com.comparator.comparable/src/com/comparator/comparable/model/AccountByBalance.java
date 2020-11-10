package com.comparator.comparable.model;

import java.util.Comparator;

public class AccountByBalance implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		if (o1.getBalance() < o2.getBalance()) return -1;
		if (o1.getBalance() > o2.getBalance()) return 1;
		else return 0;
	}
}
