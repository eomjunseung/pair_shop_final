package fianl.shop;


import fianl.shop.eom.domain.member.Member;
import fianl.shop.eom.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {

        for(int i = 0 ; i<5;i++){
            Member member = new Member();
            member.setName("asd"+i);
            member.setPassword("1234");
            memberRepository.save(member);
        }
    }



}