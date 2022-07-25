package fianl.shop.dto;

import fianl.shop.domain.Address;
import fianl.shop.domain.Contract;
import fianl.shop.domain.ContractItem;
import fianl.shop.domain.ContractStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ContractDTO {

    private Long contractId;
    private String name;
    private LocalDateTime contractDate; //주문시간
    private ContractStatus status;
    private Address address;
    //위는 Simple과 동일
    //아래가 추가
    private List<ContractItemDto> contractItems;

    public ContractDTO(Contract contract){
        contractId = contract.getId(); //계약 ID
        name = contract.getMember().getName();//계약자
        contractDate = contract.getContractDate();
        status = contract.getStatus();
        address = contract.getInstallation().getAddress();
        contractItems = contract.getContractItems().stream()
                .map(contractItem -> new ContractItemDto(contractItem))
                .collect(Collectors.toList());
    }

    @Data
    static class ContractItemDto{
        private String itemName;//상품 명 -(item)에 존재
        private int contractPrice; //계약 가격-(contract_item)에 존재

        public ContractItemDto(ContractItem contractItem){
            itemName=contractItem.getItem().getName();
            contractPrice = contractItem.getPrice();
        }
    }

}
