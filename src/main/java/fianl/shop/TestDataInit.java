package fianl.shop;


import fianl.shop.domain.Address;
import fianl.shop.domain.item.IPTv;
import fianl.shop.eom.domain.order.ItemRepository;
import fianl.shop.eom.domain.member.Member;
import fianl.shop.eom.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        for(int i = 0 ; i<5;i++){
            Member member = new Member();
            member.setName("asd"+i);
            member.setPassword("1234");
            member.setAddress(new Address("서울",i+"_번길"));
            memberRepository.save(member);
        }
        for(int i = 0 ; i <5; i++){
            IPTv ipTv = new IPTv();
            ipTv.setName("iptvname"+i);
            ipTv.setIpTvGrade("VIP");
            itemRepository.save(ipTv);
        }
    }



}