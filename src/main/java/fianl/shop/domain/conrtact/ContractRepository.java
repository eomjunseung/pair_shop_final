package fianl.shop.domain.conrtact;

import fianl.shop.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract,Long> {
    List<Contract> findByMember(Member member);
}
