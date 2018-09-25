package com.javaCore.test;

public class SynchTransferRunnable implements Runnable{
	private SynchBank bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY=10;
	/**
	 * Constructs a Synch transfer runnable.
	 * @param b the bank between whose account money is transferred
	 * @param from the account to transfer money from
	 * @param max the maximum amount of money in each transfer
	 */
	public SynchTransferRunnable(SynchBank b,int from,double max) {
		// TODO Auto-generated constructor stub
		bank=b;
		fromAccount=from;
		maxAmount=max;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				int toAccount=(int)(bank.size()*Math.random());
				double amount=maxAmount*Math.random();
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep((int)(DELAY*Math.random()));
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
}
