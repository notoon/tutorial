package org.seedstack.tutorial.application.handler;

import org.seedstack.business.EventHandler;
import org.seedstack.business.domain.Repository;
import org.seedstack.mongodb.morphia.Morphia;
import org.seedstack.tutorial.domain.model.order.CheckoutCompleteEvent;
import org.seedstack.tutorial.domain.model.seller.Seller;
import org.seedstack.tutorial.domain.services.BonusService;

import javax.inject.Inject;

public class UpdateSellerBonusHandler implements EventHandler<CheckoutCompleteEvent> {

    @Inject
    @Morphia
    private Repository<Seller, Long> sellerRepository;

    @Inject
    private BonusService bonusService;

    @Override
    public void handle(CheckoutCompleteEvent event) {
        bonusService.updateSellerBonus(event.getSeller(), event.getOrder());
        sellerRepository.save(event.getSeller());
    }
}
