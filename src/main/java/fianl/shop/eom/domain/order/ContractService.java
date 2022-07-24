package fianl.shop.eom.domain.order;


import fianl.shop.Result;
import fianl.shop.SessionConst;
import fianl.shop.domain.*;
import fianl.shop.domain.item.Item;
import fianl.shop.eom.domain.member.Member;
import fianl.shop.eom.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContractService {
    private final MemberRepository memberRepository;
    private final ContractRepository contractRepository;
    private final ItemRepository itemRepository;

    /** 주문 생성 */
    public Long makeContract(Member loginMember,
                      List<ItemDto> items){

        //멤버 갖고옴
        Member member = memberRepository.findById(loginMember.getId()).get(); //영컨등록

        Contract contract = new Contract();
        contract.setStatus(ContractStatus.Ready);

        contract.setMember(member);
        member.getContracts().add(contract);

        //설치 주소
        Installation installation = new Installation();
        installation.setAddress(member.getAddress());
        installation.setStatus(InstallationStatus.READY);

        contract.setInstallation(installation);
        installation.setContract(contract);

        //아이템 갖고옴
        for (ItemDto item : items) {
            Item newItem = itemRepository.findById(item.getId()).get(); //영컨등록

            //주문상품 생성
            ContractItem contractItem = ContractItem.createContractItem(contract,newItem, item.getPrice());
        }

        contractRepository.save(contract);

        return contract.getId();
    }
    /** 주문 취소 */
    public Result cancelOrder(Long contractId) {

        //주문 엔티티 조회
        Contract contract = contractRepository.findById(contractId).get(); //영컨에 슉

        //주문 취소
        String cancel = contract.cancel();
        if(cancel.equals("0")){
            return   new Result<>("이미 취소 처리된 상품입니다.");
        }
        return new Result("취소 완료.");
    }

    //   전체 주문
    public List<Contract> findContracts() {
        return contractRepository.findAll();
    }

    public List<Contract> findMemberOrderList(Member member) {
        List<Contract> byMember = contractRepository.findByMember(member);
        return byMember;
    }

//본인 주문
}
