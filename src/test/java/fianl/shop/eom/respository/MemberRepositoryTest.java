package fianl.shop.eom.respository;

import fianl.shop.eom.domain.member.Member;
import fianl.shop.eom.domain.member.MemberJpaRespositoy;
import fianl.shop.eom.domain.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberJpaRespositoy memberJpaRespositoy;


    @Test
    public void 테스트1()throws Exception {
        Member member = new Member();
        member.setName("eom");
        Member save = memberRepository.save(member);
        Optional<Member> byId = memberRepository.findById(save.getId());
        Member member1 = memberRepository.findById(save.getId()).get();
        Member member2 = memberRepository.findById(save.getId()).orElse(null);
    }
}