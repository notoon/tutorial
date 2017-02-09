package org.seedstack.tutorial.domain.model.order;

import org.seedstack.business.domain.DomainEvent;
import org.seedstack.tutorial.domain.model.seller.Seller;

public class CheckoutCompleteEvent implements DomainEvent {

    private Seller seller;

    private Order order;

    public CheckoutCompleteEvent(Seller seller, Order order) {
        this.seller = seller;
        this.order = order;
    }

    public Seller getSeller() {
        return seller;
    }

    public Order getOrder() {
        return order;
    }
}
