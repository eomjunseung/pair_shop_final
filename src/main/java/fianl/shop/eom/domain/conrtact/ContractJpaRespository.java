package fianl.shop.eom.domain.conrtact;

import fianl.shop.domain.Contract;
import fianl.shop.dto.ContractDTO;
import fianl.shop.dto.ContractDTOV5;
import fianl.shop.dto.ContractItemDtoV5;
import fianl.shop.dto.SimpleContractDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ContractJpaRespository {

    private final EntityManager em;

    //simple-v1
    //simpel-v2
    //detail-v1
    //detail-v2
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

    //simple-v4
    public List<SimpleContractDTO> findAllWithMemberInstallationDirectDTO() {
        return em.createQuery(
                        "select new fianl.shop.dto.SimpleContractDTO(c.id, m.name, c.contractDate, c.status, i.address)" +
                                " from Contract c" +
                                " join c.member m" +
                                " join c.installation i ", SimpleContractDTO.class)
                .getResultList();
    }


    public List<Contract> findAllWithItem() { //페이징 불가능
        return em.createQuery(
                "select distinct c from Contract c "+
                        " join fetch c.member m "+
                        " join fetch c.installation i "+
                        " join fetch c.contractItems ci "+
                        " join fetch ci.item i",Contract.class
                ).getResultList();
    }

    public List<Contract> findDetailAllWithMemberInstallation(int offset, int limit) {
        return em.createQuery(
                "select c from Contract c "+
                " join fetch c.member m "+
                " join fetch c.installation i ",Contract.class
                ).setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<ContractDTOV5> findContractQueryDto() {
        List<ContractDTOV5> all = em.createQuery(
                        "select new fianl.shop.dto.ContractDTOV5(c.id, m.name, c.contractDate, c.status, i.address)" +
                                " from Contract c" +
                                " join c.member m" +
                                " join c.installation i ", ContractDTOV5.class)
                .getResultList();

        all.forEach(
                c-> {
                    List<ContractItemDtoV5> contractItemDtoV5s = findContractItems(c.getContractId());
                    c.setContractItems(contractItemDtoV5s);
                 }
            );
        return all;
    }
    private List<ContractItemDtoV5> findContractItems(Long contractId){
        return em.createQuery(
                        "select new fianl.shop.dto.ContractItemDtoV5(i.name,ci.price)" +
                                " from ContractItem ci" +
                                " join ci.item i " +
                                " where ci.contract.id = :contractId", ContractItemDtoV5.class)
                .setParameter("contractId",contractId)
                .getResultList();
    }

    public List<ContractDTOV5> findContractQueryDto_optimization() {
        List<ContractDTOV5> all = em.createQuery(
                        "select new fianl.shop.dto.ContractDTOV5(c.id, m.name, c.contractDate, c.status, i.address)" +
                                " from Contract c" +
                                " join c.member m" +
                                " join c.installation i ", ContractDTOV5.class)
                .getResultList();

        List<Long> toContractIds = all.stream()
                                        .map(c-> c.getContractId())
                                        .collect(Collectors.toList());


        List<ContractItemDtoV5> contractItemDtoV5s = em.createQuery(
                        "select new fianl.shop.dto.ContractItemDtoV5(ci.contract.id,i.name,ci.price)" +
                                " from ContractItem ci" +
                                " join ci.item i " +
                                " where ci.contract.id in :contractIds", ContractItemDtoV5.class)
                .setParameter("contractIds", toContractIds)
                .getResultList();

        Map<Long, List<ContractItemDtoV5>> ContractItemMap = contractItemDtoV5s.stream().collect(Collectors.groupingBy(ContractItemDtoV5::getContractId));

        all.forEach(c->c.setContractItems(ContractItemMap.get(c.getContractId())));

        return all;

    }
}
