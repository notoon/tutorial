/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 * <p>
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.tutorial.it;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.domain.Repository;
import org.seedstack.seed.it.SeedITRunner;
import org.seedstack.tutorial.domain.model.order.Order;
import org.seedstack.tutorial.domain.model.order.OrderItem;
import org.seedstack.tutorial.domain.model.seller.Seller;
import org.seedstack.tutorial.domain.services.BonusService;

import javax.inject.Inject;

@RunWith(SeedITRunner.class)
public class SaleServiceIT {
    private static final long SELLER_ID = 1L;

    private static final long CUSTOMER_ID = 1L;

    private static final long ORDER_ID = 1L;

    @Inject
    private BonusService bonusService;

    @Inject
    private Repository<Seller, Long> sellerRepository;

    @Test
    public void testCalculateBonus() {
        Order order = new Order(ORDER_ID, CUSTOMER_ID);
        order.addItem(new OrderItem(444L, 2, 50d));

        Seller seller = sellerRepository.load(SELLER_ID);
        bonusService.updateSellerBonus(seller, order);
        Assertions.assertThat(seller.getMonthlyBonus()).isEqualTo(20);
    }
}
