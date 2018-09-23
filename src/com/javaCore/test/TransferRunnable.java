package com.javaCore.test;

public class TransferRunnable implements Runnable{
	private Bank bank;
	private int fromAccount;0
	private double maxAmount;
	private int DELAY=10;
	
	public TransferRunnable(Bank b,int from,double max) {
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
				int toAccount=(int) (bank.size()*Math.random());
				double amount=maxAmount*Math.random();
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep((int)(DELAY*Math.random()));
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

}
