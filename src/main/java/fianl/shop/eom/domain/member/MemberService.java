package fianl.shop.eom.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    public Long join(Member member){
        //중복 검중
        validateDuplicateMember(member);

        memberRepository.save(member);

        return member.getId();
    }

    //이름 중복 검사
    public void validateDuplicateMember(Member member) {
        Member byName = memberRepository.findByName(member.getName());
        if(byName!=null){
            throw new IllegalStateException("이미 존재하는 회원");
        }
    }

    //ID로 회원 찾기
    public Member findOne(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        if(member==null){
            throw new IllegalStateException("확인되지 않는 회원");
        }
        return member;
    }

    //회원 정보 수정(이름만 열어둠)
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).orElse(null);
        if(member==null){
            throw new IllegalStateException("확인되지 않는 회원");
        }
        member.setName(name);
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

}
