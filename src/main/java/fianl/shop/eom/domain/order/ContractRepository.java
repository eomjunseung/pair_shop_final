package fianl.shop.eom.domain.order;

import fianl.shop.domain.Contract;
import fianl.shop.eom.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract,Long> {
    List<Contract> findByMember(Member member);
}
