package org.seedstack.tutorial.application;

import org.seedstack.business.Service;
import org.seedstack.tutorial.domain.model.order.Order;

@Service
public interface CheckoutService {
    void checkout(Order order) throws CheckoutException;
}