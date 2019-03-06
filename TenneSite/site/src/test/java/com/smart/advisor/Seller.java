package com.smart.advisor;

import com.smart.aspectj.example.Merchant;

public class Seller implements Merchant {
	public void greetTo(String name) {
		System.out.println("seller greet to " + name + " ...");
	}

	@Override
	public void sell(String customer, String goods) {
		System.out.println("Seller: sell " + goods + " to " + customer);
	}
}
