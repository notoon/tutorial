package org.seedstack.tutorial.domain.services;

import org.seedstack.business.Service;
import org.seedstack.tutorial.domain.model.order.Order;
import org.seedstack.tutorial.domain.model.product.Product;

@Service
public interface OrderingService {
    void addProductToOrder(Order order, Product product, int quantity);
}