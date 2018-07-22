package com.capgemini.core.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.core.beans.Customer;
import com.capgemini.core.beans.Wallet;
import com.capgemini.core.exception.InsufficientBalanceException;
import com.capgemini.core.exception.InvalidInputException;
import com.capgemini.core.service.WalletService;
import com.capgemini.core.service.WalletServiceImpl;

public class TestClass {

	static WalletService walletService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		walletService = new WalletServiceImpl();
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateAccount() throws InvalidInputException {
		Customer customer = new Customer();
		Wallet wallet = new Wallet();
		String name = "divya";
		String number = "9603840952";
		BigDecimal amount = new BigDecimal(5000);
		
		customer.setName(name);
		customer.setMobileNo(number);
		wallet.setBalance(amount);
		customer.setWallet(wallet);
		
		customer = walletService.createAccount(name, number, amount);
		
		assertNotEquals(null, customer);
	}
	
	@Test
	public void testshowBalance() throws InvalidInputException {
		String number = "9603840952";
		
		Customer customer = walletService.showBalance(number);
		
		assertNotEquals(null,customer);
	}
	
	@Test
	public void testWithdrawAmount() throws InvalidInputException, InsufficientBalanceException {
		String name = "divya";
		String mobileNumber = "8179990657";
		BigDecimal balance = new BigDecimal(7000);
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		Customer customer = walletService.withdrawAmount(mobileNumber, amount);
		assertEquals(new BigDecimal(4000), customer.getWallet().getBalance());
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void testWithdrawAmount1() throws InvalidInputException, InsufficientBalanceException {
		String name = "divya";
		String mobileNumber = "8765098121";
		BigDecimal balance = new BigDecimal(2000);
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		walletService.withdrawAmount(mobileNumber, amount);
	}
	
	@Test
	public void testDepositAmount() throws InvalidInputException {
		String name = "divya";
		String mobileNumber = "8765098122";
		BigDecimal balance = new BigDecimal(3000);
		
		Customer customer = walletService.createAccount(name, mobileNumber, balance);
		
		Customer customer1 = walletService.depositAmount(mobileNumber, new BigDecimal(3000));
		
		assertEquals(new BigDecimal(6000), customer1.getWallet().getBalance());
	}
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount1() throws InvalidInputException {
		String name = "divya";
		String mobileNumber = "8765098129";
		BigDecimal balance = new BigDecimal(2000);
		
		Customer customer = walletService.createAccount(name, mobileNumber, balance);
		
		Customer customer1 = walletService.depositAmount(mobileNumber, new BigDecimal(0));
		
	}
	
	@Test(expected = InvalidInputException.class)
	public void testMobileNumber() throws InvalidInputException, InsufficientBalanceException {
		String name = "divya";
		String mobileNumber = "96038409523";
		BigDecimal balance = new BigDecimal(3000);
		
		walletService.createAccount(name, mobileNumber, balance);
		
		BigDecimal amount = new BigDecimal(3000);
		
		walletService.withdrawAmount(mobileNumber, amount);
	}


}
