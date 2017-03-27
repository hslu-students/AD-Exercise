package ch.hslu.AD.SW05.BankAccount;


public class Playground {
	public static void main(String[] args) throws InterruptedException {
		Bank bank = new Bank(100, 10000);
		
		// start transfer
		bank.startTransfers(1000, 5000);
		
		System.out.println(bank);
	}
}
