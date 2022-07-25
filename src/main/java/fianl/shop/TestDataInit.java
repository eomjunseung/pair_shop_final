package fianl.shop;


import fianl.shop.domain.Address;
import fianl.shop.domain.item.IPTv;
import fianl.shop.domain.item.Internet;
import fianl.shop.domain.item.IoT;
import fianl.shop.domain.item.ItemRepository;
import fianl.shop.domain.member.Member;
import fianl.shop.domain.member.MemberRepository;
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
            IoT ioT = new IoT();
            ioT.setName("iotname"+i);
            ioT.setIotCategory("C"+i);
            itemRepository.save(ioT);
            Internet internet = new Internet();
            internet.setName("internet_"+i);
            internet.setInternetType("type"+i);
            itemRepository.save(internet);
        }
    }



}