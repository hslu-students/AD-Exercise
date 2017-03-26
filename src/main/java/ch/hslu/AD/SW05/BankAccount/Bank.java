package ch.hslu.AD.SW05.BankAccount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bank {
	private List<BankAccount> sourceAccounts = new ArrayList<>();
	private List<BankAccount> targetAccounts = new ArrayList<>();
	
	public Bank(final int amountAccounts, final int initialBalance) {
		this.createAccounts(amountAccounts, initialBalance);
	}
	
	private void createAccounts(final int amountAccounts, final int initialBalance) {
		for(int i = 0; i < amountAccounts; i++) {
			sourceAccounts.add(new BankAccount(initialBalance));
			targetAccounts.add(new BankAccount(initialBalance));
		}
	}
	
	public void startTransfers(final int maxThreads, final int amount) {
		Thread[] forthTransferThreads = new Thread[maxThreads * sourceAccounts.size()];
		Thread[] backTransferThreads = new Thread[maxThreads * targetAccounts.size()];
		
		final int transferAmount = amount / maxThreads;
		
		// setup threads
		for(int i = 0; i < sourceAccounts.size(); i++) {
			BankAccount sourceAccount = sourceAccounts.get(i);
			BankAccount targetAccount = targetAccounts.get(i);
			
			for(int j = 0; j < maxThreads; j++) {
				forthTransferThreads[i * sourceAccounts.size() + j] = new Thread(() -> sourceAccount.transfer(targetAccount, transferAmount));
				backTransferThreads[i * sourceAccounts.size() + j] = new Thread(() -> targetAccount.transfer(sourceAccount, transferAmount));
			}
		}
		
		// one list of threads
		for(Thread thread : forthTransferThreads) {
			thread.start();
		}
		
		// join all threads
		for(Thread thread : forthTransferThreads) {
			thread.join();
		}
	}
}
