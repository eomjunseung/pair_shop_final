package fianl.shop.domain.item;

import fianl.shop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter @Setter
//추상클래스 임 -> 상속관계로 쓸거라
public abstract class Item {

    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    //공통속성
    private String name;
    private int price;
}
