package fianl.shop.domain.member.login;

import fianl.shop.domain.member.Member;
import fianl.shop.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null : 실패
     */

    public Member login(String name, String password){
        Member member = memberRepository.findByName(name);
        if(member.getPassword().equals(password)){
            return member;
        }
        return null;
    }

}
