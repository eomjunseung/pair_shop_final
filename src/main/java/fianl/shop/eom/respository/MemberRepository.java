package fianl.shop.eom.respository;

import fianl.shop.eom.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Member findByName(String name);

}
