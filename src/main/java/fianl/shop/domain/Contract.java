package fianl.shop.domain;

import fianl.shop.eom.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본 생성자를 만듬 --> 생성을 막는다
public class Contract {

    @Id @GeneratedValue
    @Column(name = "contract_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //ToMany  : LAZY default
    @OneToMany(fetch=LAZY,  mappedBy = "contract", cascade = CascadeType.ALL) //Order가 저장되면 --> 얘도 같이 저장해라~
    private List<ContractItem> contractItems = new ArrayList<>(); //관례상 빈 ArrayList넣어줌

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "installation_id")
    private Installation installation;

    private LocalDateTime contractDate; //주문시간

    @Enumerated(EnumType.STRING)
    private ContractStatus status; //주문상태 [ORDER, CANCEL]


}