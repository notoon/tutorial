package org.seedstack.tutorial.it;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.domain.Factory;
import org.seedstack.business.test.events.EventFixture;
import org.seedstack.seed.it.SeedITRunner;
import org.seedstack.tutorial.application.handler.UpdateSellerBonusHandler;
import org.seedstack.tutorial.domain.model.order.CheckoutCompleteEvent;
import org.seedstack.tutorial.domain.model.order.Order;
import org.seedstack.tutorial.domain.model.seller.Seller;

import javax.inject.Inject;
import java.util.Date;

@RunWith(SeedITRunner.class)
public class CheckoutCompleteEventIT {

    private static final long SELLER_ID = 1L;

    private static final long CUSTOMER_ID = 1L;

    private static final long ORDER_ID = 1L;

    @Inject
    private EventFixture fixture;

    @Inject
    private Factory<CheckoutCompleteEvent> checkoutCompleteEventFactory;

    @Test
    public void testUpdateSellerBonusWhenCheckoutComplete() throws Exception {
        Seller seller = new Seller(SELLER_ID, new Date());
        Order order = new Order(ORDER_ID, CUSTOMER_ID);
        fixture.given(checkoutCompleteEventFactory.create(seller, order))
                .whenFired()
                .wasHandledBy(UpdateSellerBonusHandler.class);
    }

}