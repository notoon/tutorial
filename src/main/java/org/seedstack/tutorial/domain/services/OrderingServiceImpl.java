package org.seedstack.tutorial.domain.services;

import org.seedstack.tutorial.domain.model.order.Order;
import org.seedstack.tutorial.domain.model.order.OrderItem;
import org.seedstack.tutorial.domain.model.product.Product;

import java.util.List;
import java.util.stream.Collectors;

class OrderingServiceImpl implements OrderingService {
    @Override
    public void addProductToOrder(Order order, Product product, int quantity) {
        // Find items with the same product identifier if any
        List<OrderItem> itemsToRemove = order.getItems().stream()
                .filter(orderItem -> orderItem.getProductId() == product.getProductId())
                .collect(Collectors.toList());

        // Remove the items from the order and update the quantity
        quantity += itemsToRemove.stream().map(order::removeItem).mapToInt(OrderItem::getQuantity).sum();

        // Add a new item with the total quantity
        order.addItem(new OrderItem(
                product.getProductId(),
                quantity,
                product.getPrice() * quantity)
        );
    }
}