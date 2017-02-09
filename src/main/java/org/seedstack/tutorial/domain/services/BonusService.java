package org.seedstack.tutorial.domain.services;

import org.seedstack.business.Service;
import org.seedstack.tutorial.domain.model.order.Order;
import org.seedstack.tutorial.domain.model.seller.Seller;

@Service
public interface BonusService {
    void updateSellerBonus(Seller seller, Order order);
}