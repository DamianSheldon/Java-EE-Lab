package com.smart.aspectj.example;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

import com.smart.advisor.Seller;

@Aspect
public class EnableMerchantAspect {
	
	@DeclareParents(value="com.smart.advice.NaiveWaiter", defaultImpl=Seller.class)
	public Merchant merchant;
}
