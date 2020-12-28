package com.marko.ecommerce.service;


import com.marko.ecommerce.dto.Purchase;
import com.marko.ecommerce.dto.PurchaseResponse;


public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);

}
