package bank_Management_Console;

import java.util.ArrayList;
import java.util.Scanner;

public class BankSystem {
	// Stores all bank accounts
	private ArrayList<BankAccount> accounts = new ArrayList<>();
	
	private Scanner sc = new Scanner(System.in);
	
	// Creates a new bank account
	public void createAccount() {
		System.out.println("Please enter account user name : ");
		String name = sc.nextLine();
		
		if(name.trim().isEmpty()) {
			System.out.println("Name can't be empty!");
			return; 
		}
		
		System.out.println("Please enter account number : ");
		int accNo;
		
		try {
			accNo = Integer.parseInt(sc.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Please enter numbers only!");
			return;
		}
		
		for(BankAccount acc : accounts) {
			if(acc.getAccNo() == accNo) {
				System.out.println("Account number already used!");
				return;
			}
		}
		
		System.out.println("Please enter PIN : ");
		int pin;
		try {
			pin = Integer.parseInt(sc.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Please enter numbers only!");
			return; 
		}
		
		if(pin < 1000 || pin > 9999) {
			System.out.println("Invalid PIN! Enter a 4-digit PIN.");
			return;
		}
		
		System.out.println("Please enter initial balance : ");
		double balance;
		try {
			balance = Double.parseDouble(sc.nextLine());
		}
		catch(NumberFormatException e) {
			System.out.println("Please enter numbers only!");
			return; 
		}
		
		if(balance < 0) {
			System.out.println("Invalid amount!");
			return; 
		}
		
		BankAccount account = new BankAccount(name, accNo, pin, balance);
		accounts.add(account);
		
		System.out.println("Account created successfully!");
	}
	
	// Searches account using account number
	public BankAccount findAccount(int accNo) {
		for(BankAccount acc : accounts) {
			if(acc.getAccNo() == accNo) {
				return acc;
			}
		}
		return null; 
	}
	
	//Manages Login system for already created accounts
	public BankAccount login() {
		System.out.println("Please enter account number : ");
		int accNo;
		try {
			accNo = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e) {
			System.out.println("Please enter numbers only!");
			return null; 
		}
		
		System.out.println("Please enter PIN : ");
		int pin;
		try {
			pin = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e) {
			System.out.println("Please enter numbers only!");
			return null; 
		}
		
		BankAccount account = findAccount(accNo);
		if(account == null) {
			System.out.println("Account not found!");
			return null;
		} 
		
		if(account.getPin() == pin) {
			System.out.println("Login Successful!");
			return account;
		}
		else {
			System.out.println("Invalid PIN!");
			return null;
		}
	}
	
	// Account menu shown after successful login
	public void accountMenu(BankAccount account) {
		while(true) {
			System.out.println("-------------------------------------------");
			System.out.println("Welcome, " + account.getName() + "!");
			System.out.println("-------------------------------------------");
			System.out.println("1. Deposit");
			System.out.println("2. Withdraw");
			System.out.println("3. Show Balance");
			System.out.println("4. Show History");
			System.out.println("5. Logout");
			
			System.out.println("Enter your choice : ");
			int choice;
			try {
				choice = Integer.parseInt(sc.nextLine());
			}
			catch(NumberFormatException e) {
				System.out.println("Please enter only numbers!");
				continue;
			}
			
			switch(choice) {
			case 1 :
				System.out.println("Please enter amount to deposit : ");
				double amount1;
				try {
					amount1 = Double.parseDouble(sc.nextLine());
				}
				catch(NumberFormatException e) {
					System.out.println("Please enter only numbers!");
					continue;
				}
				account.deposit(amount1);
				break;
			
			case 2 :
				System.out.println("Please enter amount to withdraw : ");
				double amount2;
				try {
					amount2 = Double.parseDouble(sc.nextLine());
				}
				catch(NumberFormatException e) {
					System.out.println("Please enter only numbers!");
					continue;
				}
				account.withdraw(amount2);
				break;
			
			case 3 :
				account.showBalance();
				break;
			
			case 4 :
				account.showHistory();
				break;
				
			case 5 :
				System.out.println("Thank You! Have a nice day.");
				return;

			default :
				System.out.println("Invalid Input!");
				continue; 
			}
		} 
	}
	
	// Main menu of the banking system
	public void start() {
		while(true) {
			System.out.println("======================================");
			System.out.println("\tBANK MANAGEMENT SYSTEM");
			System.out.println("======================================");
			
			System.out.println("1. Create Account");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			
			System.out.println("Please enter your choice : ");
			int choice;
			try {
				choice = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.out.println("Please enter numbers only!");
				continue;
			}
			
			switch(choice) {
			case 1 :
				createAccount();
				break;
				
			case 2 : 
				BankAccount loggedInAccount = login();
				if(loggedInAccount != null) {
					accountMenu(loggedInAccount);
				}
				break;
				
			case 3 : 
				System.out.println("Thanks! Have a nice day.");
				return;
				
			default :
				System.out.println("Invalid choice!");
				continue;
			}
			
		}
		
	}
	
	
}
