package fianl.shop.dto;

import fianl.shop.domain.Address;
import fianl.shop.domain.conrtact.Contract;
import fianl.shop.domain.conrtact.ContractStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ContractDTOV5 {

    private Long contractId;
    private String name;
    private LocalDateTime contractDate; //주문시간
    private ContractStatus status;
    private Address address;
    //위는 Simple과 동일
    //아래가 추가
    private List<ContractItemDtoV5> contractItems;

    public ContractDTOV5(Long contractId, String name , LocalDateTime contractDate, ContractStatus status, Address address){
        this.contractId=contractId;
        this.name=name;
        this.contractDate=contractDate;
        this.status=status;
        this.address=address;
    }

    public ContractDTOV5(Contract contract){
        contractId = contract.getId(); //계약 ID
        name = contract.getMember().getName();//계약자
        contractDate = contract.getContractDate();
        status = contract.getStatus();
        address = contract.getInstallation().getAddress();

    }



}
