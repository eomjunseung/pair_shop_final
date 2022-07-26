package fianl.shop.domain.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fianl.shop.domain.Address;
import fianl.shop.domain.conrtact.Contract;
import fianl.shop.domain.like.Like;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    @NotEmpty //-->@valid로 체크
    private String name;

    @Embedded //@Embadable....
    private Address address;

    @NotEmpty
    private String password;


    @JsonIgnore // 양방향 참조시에 한쪽에 해줘야함 그래서 연쇄 호출안함
    @OneToMany(mappedBy = "member") //읽기만 가능
    private List<Contract> contracts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Like> likes = new ArrayList<>();
}
