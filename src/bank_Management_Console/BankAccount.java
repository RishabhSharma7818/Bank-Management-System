package bank_Management_Console;

import java.util.ArrayList;

public class BankAccount {
	
	// Declare instance variables
	private String name;
	private int accNo;
	private int pin;
	private double balance;
	
	public BankAccount(String name, int accNo, int pin, double balance) {
		this.name = name;
		this.accNo = accNo;
		this.pin = pin;
		this.balance = balance;
	}
	
	public String getName() {
		return name; 
	}
	
	public int getAccNo() {
		return accNo; 
	}
	
	public int getPin() {
		return pin; 
	}
	
	public double getBalance() {
		return balance;
	}
	
	//Use ArrayList for store transaction history
	private ArrayList<String> transactionHistory = new ArrayList<>();
	
	//Handles deposit functionality
    public void deposit(double amount) {
    	if(amount <= 0) {
    		System.out.println("Invalid amount!");
    		return;
    	}
    	balance += amount;
    	System.out.println("₹" + amount + " deposited successfully!");
    	
    	transactionHistory.add("Deposited ₹" + amount);
		
	}
	
    //Handles withdraw functionality
	public void withdraw(double amount) { 
		if(amount <= 0) {
    		System.out.println("Invalid amount!");
    		return;
    	}
		if(amount>balance) {
			System.out.println("Transaction failed! Insufficient balance.");
			transactionHistory.add("Failed withdrawal attempt ₹" + amount);
			
		}else {
			balance -= amount;
			System.out.println("₹" + amount + " withdrawn successfully!");
			
			transactionHistory.add("Withdrawn ₹" + amount);
		}
	}
	
	// Displays current account balance
	public void showBalance() {
		System.out.println("Current Balance : ₹" + balance);
	}
	
	// Displays all transaction history
	public void showHistory() {
		System.out.println("Transaction History : ");
		if(transactionHistory.isEmpty()) {
			System.out.println("No transactions found!");
		}
		else {
			for(String transaction : transactionHistory) {
				System.out.println(transaction);
			}
		}
	}
	
	
	
}

