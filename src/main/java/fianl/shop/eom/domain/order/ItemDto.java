package fianl.shop.eom.domain.order;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDto {
    public Long id;
    public int price;
}
