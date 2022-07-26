package fianl.shop.api;

import fianl.shop.Result;
import fianl.shop.domain.conrtact.Contract;
import fianl.shop.domain.conrtact.ContractItem;
import fianl.shop.dto.ContractDTO;
import fianl.shop.dto.ContractDTOV5;
import fianl.shop.domain.conrtact.ContractJpaRespository;
import fianl.shop.domain.conrtact.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ContractDeatailAPIController {
    private final ContractService contractService; //아마 여기서 안쓸듯합니다. (to종훈님)
    private final ContractJpaRespository contractJpaRespository;

    @GetMapping("/d/v1/contracts")
    public Result dContractAllV1() {
        List<Contract> all = contractJpaRespository.findAll();
        for (Contract contract : all) {
            contract.getMember().getName(); //Lazy 강제 초기화
            contract.getInstallation().getAddress(); //Lazy 강제 초기환
            List<ContractItem> contractOrderItems = contract.getContractItems();
            contractOrderItems.stream().forEach(o -> o.getItem().getName()); //Lazy 강제
        }
        return new Result<>(all.size(),all,"select detail contracts entity v1");
    }

    @GetMapping("/d/v2/contracts")
    public Result dContractAllV2() {
        List<Contract> all = contractJpaRespository.findAll();
        List<ContractDTO> result = all.stream().map(c -> new ContractDTO(c)).collect(Collectors.toList());
        return new Result(result.size(),result,"select detail contracts dto v2");
    }

    //페이징 불가능, 대신 한방 쿼리
    @GetMapping("/d/v3/contracts")
    public Result dContractAllV3() {
        List<Contract> all = contractJpaRespository.findAllWithItem();
        List<ContractDTO> result = all.stream().map(c -> new ContractDTO(c)).collect(Collectors.toList());
        return new Result(result.size(),result,"select detail contracts dto v3");
    }

    //xxxToOne - fetch join
    //xxxToMany - default_batch_fetch_size
    @GetMapping("/d/v4/contracts")
    public Result dContractAllV4(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                            @RequestParam(value = "limit", defaultValue = "10") int limit) {
        List<Contract> all = contractJpaRespository.findDetailAllWithMemberInstallation(offset, limit);
        List<ContractDTO> result = all.stream().map(c -> new ContractDTO(c)).collect(Collectors.toList());
        return new Result(result.size(),result,"select detail contracts dto v4");
    }
    @GetMapping("/d/v5/contracts")
    public Result dContractAllV5(){
        List<ContractDTOV5> result = contractJpaRespository.findContractQueryDto();
        return new Result(result.size(),result,"select detail contracts dto v5");
    }
    @GetMapping("/d/v6/contracts")
    public Result dContractAllV6(){
        List<ContractDTOV5> result = contractJpaRespository.findContractQueryDto_optimization();
        return new Result(result.size(),result,"select detail contracts dto v6");
    }


}