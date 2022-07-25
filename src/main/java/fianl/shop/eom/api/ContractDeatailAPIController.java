package fianl.shop.eom.api;

import fianl.shop.domain.Contract;
import fianl.shop.domain.ContractItem;
import fianl.shop.dto.ContractDTO;
import fianl.shop.dto.ContractDTOV5;
import fianl.shop.eom.domain.conrtact.ContractJpaRespository;
import fianl.shop.eom.domain.conrtact.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @GetMapping("/d/v2/contracts")
    public List<ContractDTO> dContractAllV2() {
        List<Contract> all = contractJpaRespository.findAll();
        return all.stream().map(c -> new ContractDTO(c)).collect(Collectors.toList());
    }

    //페이징 불가능, 대신 한방 쿼리
    @GetMapping("/d/v3/contracts")
    public List<ContractDTO> dContractAllV3() {
        List<Contract> all = contractJpaRespository.findAllWithItem();
        return all.stream().map(c -> new ContractDTO(c)).collect(Collectors.toList());
    }

    //xxxToOne - fetch join
    //xxxToMany - default_batch_fetch_size
    @GetMapping("/d/v4/contracts")
    public List<ContractDTO> dContractAllV4(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                            @RequestParam(value = "limit", defaultValue = "10") int limit) {
        List<Contract> all = contractJpaRespository.findDetailAllWithMemberInstallation(offset, limit);
        return all.stream().map(c -> new ContractDTO(c)).collect(Collectors.toList());
    }
    @GetMapping("/d/v5/contracts")
    public List<ContractDTOV5> dContractAllV5(){
        return contractJpaRespository.findContractQueryDto();
    }
    @GetMapping("/d/v6/contracts")
    public List<ContractDTOV5> dContractAllV6(){
        return contractJpaRespository.findContractQueryDto_optimization();
    }


}