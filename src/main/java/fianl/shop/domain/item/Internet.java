package fianl.shop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("internet")
@Getter
@Setter
public class Internet extends Item{

    //ID가 굳이 없음
    private String internetType; // 10G 3G
}
