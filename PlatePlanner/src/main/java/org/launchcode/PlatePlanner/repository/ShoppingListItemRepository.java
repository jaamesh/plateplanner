package org.launchcode.PlatePlanner.repository;

import org.launchcode.PlatePlanner.model.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Long> {
}
