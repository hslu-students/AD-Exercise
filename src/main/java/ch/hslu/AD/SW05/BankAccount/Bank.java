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
	
	public void startTransfers(final int maxThreads, final int amount) throws InterruptedException {
		List<Thread> threads = new ArrayList<Thread>();
		
		final int transferAmount = amount / maxThreads;
		
		// setup threads
		for(int i = 0; i < sourceAccounts.size(); i++) {
			BankAccount sourceAccount = sourceAccounts.get(i);
			BankAccount targetAccount = targetAccounts.get(i);
			
			for(int j = 0; j < maxThreads; j++) {
				threads.add(new Thread(() -> sourceAccount.transfer(targetAccount, transferAmount)));
				threads.add(new Thread(() -> targetAccount.transfer(sourceAccount, transferAmount)));
			}
		}
		
		//Collections.shuffle(threads);
		
		threads.stream().forEach((t) -> t.start());
		threads.stream().forEach((t) -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for(BankAccount account : this.sourceAccounts) {
			builder.append(account + "\n");
		}
		for(BankAccount account : this.targetAccounts) {
			builder.append(account + "\n");
		}
		
		return builder.toString();
	}
}
