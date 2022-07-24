package fianl.shop.eom.domain.order;

import fianl.shop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item,Long> {

}
