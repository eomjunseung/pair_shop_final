package fianl.shop.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import fianl.shop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ContractItem {

    @Id @GeneratedValue
    @Column(name="contract_item_id")
    private Long id;

    //양방향이 아니기에 JsonIgnore 굳이 안씀
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contract_id")
    private Contract contract;

    private int price; //1개 가격, 할인없으면 item의 가격과 완전 동일(수량고려X)
    private int count;

}
