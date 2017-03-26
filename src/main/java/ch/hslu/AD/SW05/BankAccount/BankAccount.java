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
	
	public void deposite(final int amount) {
		this.balance += amount;
	}
	
	public void transfer(final BankAccount target, final int amount) {
		this.balance -= amount;
		target.deposite(amount);
	}
}
