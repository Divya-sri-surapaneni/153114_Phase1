package com.capgemini.core.repo;

import com.capgemini.core.beans.Customer;

public interface WalletRepo {

	public boolean save(Customer customer);

	public Customer findOne(String mobileNo);

	public void remove(String mobileNo);
	
}
