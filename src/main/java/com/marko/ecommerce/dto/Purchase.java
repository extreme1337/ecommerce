package com.marko.ecommerce.dto;

import com.marko.ecommerce.entity.Address;
import com.marko.ecommerce.entity.Customer;
import com.marko.ecommerce.entity.Order;
import com.marko.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
