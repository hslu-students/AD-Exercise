package ch.hslu.AD.SW05.BankAccount;

public class BankAccount {
	private int balance;
	
	public BankAccount(final int balance) {
		this.balance = balance; 
	}
	
	public BankAccount() {
		this(0);
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public synchronized void deposite(final int amount) {
		this.balance += amount;
	}
	
	public void transfer(final BankAccount target, final int amount) {
		//System.out.println(String.format("Transfer %d from %s to %s", amount, this, target));
		synchronized (this) {
			this.balance -= amount;
		}
		target.deposite(amount);
	}
	
	@Override
	public String toString() {
		return String.format("BankAccount [balance: %d]", this.balance);
	}
}
