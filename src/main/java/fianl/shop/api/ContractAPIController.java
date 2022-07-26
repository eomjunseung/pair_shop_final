package fianl.shop.api;

import fianl.shop.Result;
import fianl.shop.SessionConst;
import fianl.shop.domain.conrtact.Contract;
import fianl.shop.domain.conrtact.ContractJpaRespository;
import fianl.shop.domain.conrtact.ContractService;
import fianl.shop.domain.member.Member;
import fianl.shop.dto.ItemDto;
import fianl.shop.dto.SimpleContractDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ContractAPIController {

    private final ContractService contractService;
    private final ContractJpaRespository contractJpaRespository;

    //주문 생성
    @PostMapping("/contracts")
    public Result order(
                        @SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member loginMember,
                        @RequestBody List<ItemDto> items) {

        contractService.makeContract(loginMember, items);
        return new Result(items.size(),items,"주문완료");

    }

    //모든 주문 조회
    //지금은, 모두가 조회 가능 하나, 추후 MASTER만 조회 가능,
    //해당 프로젝트에서 적용 시키지 않을 예정.
    @GetMapping(value = "/s/v1/contracts")
    public Result sContractAllV1() {

        List<Contract> all = contractJpaRespository.findAll();

        for (Contract contract : all) {
            contract.getMember().getName(); //Lazy 강제 초기화
            contract.getInstallation().getAddress(); //Lazy 강제 초기화
        }
        return new Result(all.size(),all,"select simple order entity v1");
    }

    @GetMapping(value = "/s/v2/contracts")
    public Result sContractAllV2() {
        List<Contract> all = contractJpaRespository.findAll();
        List<SimpleContractDTO> result = all.stream()
                .map(SimpleContractDTO::new)
                .collect(Collectors.toList());
        return new Result(result.size(),result,"select simple order dto v2");
    }

    @GetMapping(value = "/s/v3/contracts")
    public Result sContractAllV3() {
        List<Contract> all = contractJpaRespository.findAllWithMemberInstallation();
        List<SimpleContractDTO> result = all.stream()
                .map(SimpleContractDTO::new)
                .collect(Collectors.toList());
        return new Result(result.size(),result,"select simple order dto v3");
    }
    @GetMapping(value = "/s/v4/contracts")
    public Result  sContractAllV4() {
        List<SimpleContractDTO> contracts = contractJpaRespository.findAllWithMemberInstallationDirectDTO();
        return new Result(contracts.size(), contracts, "select simple order dto v4");

    }
    //회원별 주문 조회
    @GetMapping(value = "/members/contracts")
    public Result orderList(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member loginMember) {
        List<Contract> contracts = contractService.findMemberOrderList(loginMember);
        return new Result<>(contracts.size(), contracts, "내 주문 조회");
    }

    //주문 취소
    @PostMapping(value = "/contracts/{contractId}/cancel")
    public Result cancelOrder(@PathVariable("contractId") Long orderId) {
        return contractService.cancelContract(orderId);
    }

}
