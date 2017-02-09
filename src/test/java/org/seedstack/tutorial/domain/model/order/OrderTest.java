/**
 * Copyright (c) 2013-2015, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.tutorial.domain.model.order;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {
    private static final long ORDER_ID = 1L;
    private static final long CUSTOMER_ID = 2L;

    private Order underTest;

    @Before
    public void setup() {
        underTest = new Order(ORDER_ID, CUSTOMER_ID);
    }

    @Test
    public void testInitialOrderState() throws Exception {
        assertThat(underTest.getItems()).isEmpty();
        assertThat(underTest.getTotal()).isZero();
    }

    @Test
    public void testAddItemIncreaseTheTotal() throws Exception {
        final int quantity = 5;
        final int productId = 1;
        final int price = 3;

        underTest.addItem(new OrderItem(productId, quantity, price));
        assertThat(underTest.getTotal()).isEqualTo(quantity * price);
        assertThat(underTest.getItems().size()).isEqualTo(1);
    }

    @Test
    public void testRemoveItemUpdateTheTotal() throws Exception {
        final int quantity = 5;
        final int productId = 1;
        final int price = 3;
        final OrderItem orderItem = new OrderItem(productId, quantity, price);

        underTest.addItem(orderItem);
        underTest.removeItem(orderItem);
        assertThat(underTest.getTotal()).isZero();
        assertThat(underTest.getItems()).isEmpty();
    }

    @Test
    public void itemQuantityShouldNotBeLowerThan1() throws Exception {
        try {
            new OrderItem(1L, 0, 50d);
        } catch (IllegalArgumentException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("The quantity cannot be lower than 1");
        }
    }

    @Test
    public void itemPriceShouldNotBeLowerThan0() throws Exception {
        try {
            new OrderItem(1L, 1, -1d);
        } catch (IllegalArgumentException e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("The amount cannot be lower than 0");
        }
    }
}
