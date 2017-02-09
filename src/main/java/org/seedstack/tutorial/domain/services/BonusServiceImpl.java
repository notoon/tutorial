package org.seedstack.tutorial.domain.services;

import org.seedstack.business.domain.DomainRegistry;
import org.seedstack.tutorial.domain.model.order.Order;
import org.seedstack.tutorial.domain.model.seller.BonusPolicy;
import org.seedstack.tutorial.domain.model.seller.Seller;

import javax.inject.Inject;

class BonusServiceImpl implements BonusService {
    private final DomainRegistry domainRegistry;

    @Inject
    public BonusServiceImpl(DomainRegistry domainRegistry) {
        this.domainRegistry = domainRegistry;
    }

    public void updateSellerBonus(Seller seller, Order order) {
        BonusPolicy bonusPolicy = domainRegistry.getPolicy(BonusPolicy.class, seller.getBonusPolicy());
        double orderBonus = bonusPolicy.computeBonus(order);
        seller.addToMonthlyBonus(orderBonus);
    }
}