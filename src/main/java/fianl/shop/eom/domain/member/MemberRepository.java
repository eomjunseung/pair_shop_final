package fianl.shop.eom.domain.member;

import fianl.shop.eom.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByName(String name);

}
