package org.seedstack.tutorial.application;

import org.seedstack.business.EventService;
import org.seedstack.business.domain.Factory;
import org.seedstack.business.domain.Repository;
import org.seedstack.mongodb.morphia.Morphia;
import org.seedstack.tutorial.domain.model.order.CheckoutCompleteEvent;
import org.seedstack.tutorial.domain.model.order.Order;
import org.seedstack.tutorial.domain.model.seller.Seller;

import javax.inject.Inject;
import java.util.Optional;

class CheckoutServiceImpl implements CheckoutService {

    @Inject
    @Morphia
    private Repository<Seller, Long> sellerRepository;

    @Inject
    @Morphia
    private Repository<Order, Long> orderRepository;

    @Inject
    private Factory<CheckoutCompleteEvent> checkoutCompleteEventFactory;

    @Inject
    private EventService eventService;

    @Override
    public void checkout(Order order) throws CheckoutException {
        Optional<Seller> authenticatedSeller = getAuthenticatedSeller();
        if (authenticatedSeller.isPresent()) {
            // Do the checkout
            order.checkout();
            orderRepository.save(order);

            // Update seller info
            Seller seller = authenticatedSeller.get();
            eventService.fire(checkoutCompleteEventFactory.create(seller, order));
        } else {
            throw new CheckoutException("No user authenticated or current user is not a seller");
        }
    }

    private Optional<Seller> getAuthenticatedSeller() {
        return Optional.of(sellerRepository.load(1L));
    }
}