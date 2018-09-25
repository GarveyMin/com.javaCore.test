package com.javaCore.test;

public class SynchBankTest2 {
	private static final int NACCOUNTS=100;
	private static final double INITIAL_BALANCE=1000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SynchBank2 bank=new SynchBank2(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for(i=0;i<NACCOUNTS;i++) {
			SynchTransferRunnable2 r=new SynchTransferRunnable2(bank, i, INITIAL_BALANCE);
			Thread t=new Thread(r);
			t.start();
		}
	}

}
