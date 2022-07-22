package fianl.shop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("iot")
@Getter
@Setter
public class IoT extends Item{
    private String iotCategory;
}
