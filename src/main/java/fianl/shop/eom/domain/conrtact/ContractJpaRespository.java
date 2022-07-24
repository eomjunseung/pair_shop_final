package fianl.shop.eom.domain.conrtact;

import fianl.shop.domain.Contract;
import fianl.shop.dto.SimpleContractDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContractJpaRespository {

    private final EntityManager em;

    //simple-v1
    //simpel-v2
    public List<Contract> findAll() {
        return em.createQuery("select c from Contract c", Contract.class)
                .getResultList();
    }


    //simple-v3
    public List<Contract> findAllWithMemberInstallation() {
        return em.createQuery(
                        "select c from Contract c" +
                                " join fetch c.member m" +
                                " join fetch c.installation i", Contract.class)
                .getResultList();
    }

    public List<SimpleContractDTO> findAllWithMemberInstallationDirectDTO() {
        return em.createQuery(
                        "select new fianl.shop.dto.SimpleContractDTO(c.id, m.name, c.contractDate, c.status, i.address)" +
                                " from Contract c" +
                                " join c.member m" +
                                " join c.installation i ", SimpleContractDTO.class)
                .getResultList();
    }



}
