package fianl.shop.eom.domain.order;

import fianl.shop.Result;
import fianl.shop.SessionConst;
import fianl.shop.domain.Contract;
import fianl.shop.domain.ContractItem;
import fianl.shop.eom.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContractApiController {
    private final ContractService contractService;
    private final ContractJpaRespository contractJpaRespository;

    //주문 생성
    @PostMapping("/order")
    public String order(
                        @SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member loginMember,
                        @RequestBody List<ItemDto> items) {
        contractService.makeContract(loginMember, items);
        return "완료";
    }

    //모든 주문 조회
    //지금은, 모두가 조회 가능 하나, 추후 MASTER만 조회 가능,
    //해당 프로젝트에서 적용 시키지 않을 예정.
    @GetMapping(value = "/orders")
    public List<Contract> allOrderList() {

        List<Contract> all = contractJpaRespository.findAll();
        for (Contract contract : all) {

            contract.getMember().getName(); //Lazy 강제 초기화 --> .getMember() 까지 프록시, getName();에서 쿼리
            contract.getInstallation().getAddress(); //Lazy 강제 초기화 --> 이하 동일 ( 이런게 강제 초기화~)
            List<ContractItem> contractItems = contract.getContractItems();//주문에서 --> orderItem(디폴트 LAZY여서 직접 초기화 필요)

            //OrdetItem에 Item이 LAZY임 --> 직접 초기화 필요
            contractItems.stream().forEach(a -> a.getItem().getName()); //getItem()이 아니라, getName()에서 초기화됨 ㅎㅎ
        }

        System.out.println("testtest");

        System.out.println(all.size());

        return all;
    }

    //회원별 주문 조회
    @GetMapping(value = "/members/orders")
    public Result orderList(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member loginMember) {
        List<Contract> contracts = contractService.findMemberOrderList(loginMember);
        return new Result<>(contracts.size(), contracts, "내 주문 조회");
    }

    //주문 취소
    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        contractService.cancelOrder(orderId);
        return "redirect:/orders";
    }

}
