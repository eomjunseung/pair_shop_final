package fianl.shop.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import fianl.shop.domain.item.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="item_like")
public class Like {
    @Id @GeneratedValue
    @Column(name="like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

//    //생성
//    public static Like createUpLike(Item item, Member member){
//        Like like = new Like();
//        like.setItem(item);
//        like.setMember(member);
//        return like;
//    }
}