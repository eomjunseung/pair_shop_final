package fianl.shop.eom.domain.conrtact;

import fianl.shop.domain.Contract;
import fianl.shop.domain.ContractItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContractDeatailAPIController {
    private final ContractService contractService; //아마 여기서 안쓸듯합니다. (to종훈님)
    private final ContractJpaRespository contractJpaRespository;

    @GetMapping("/d/v1/contracts")
    public List<Contract> dContractAllV1() {
        List<Contract> all = contractJpaRespository.findAll();
        for (Contract contract : all) {
            contract.getMember().getName(); //Lazy 강제 초기화
            contract.getInstallation().getAddress(); //Lazy 강제 초기환
            List<ContractItem> contractOrderItems = contract.getContractItems();
            contractOrderItems.stream().forEach(o -> o.getItem().getName()); //Lazy 강제
        }
        return all;
    }
}
