package fianl.shop.eom.domain.order;

import fianl.shop.domain.Contract;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContractJpaRespository {

    private final EntityManager em;

    public List<Contract> findAll() {
        return em.createQuery("select c from Contract c", Contract.class)
                .getResultList();
    }
}
