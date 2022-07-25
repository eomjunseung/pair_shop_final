package fianl.shop.dto;

import fianl.shop.domain.Address;
import fianl.shop.domain.conrtact.Contract;
import fianl.shop.domain.conrtact.ContractStatus;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class SimpleContractDTO {

    private Long contractId;
    private String name;
    private LocalDateTime contractDate; //주문시간
    private ContractStatus status;
    private Address address;

    public SimpleContractDTO(Contract contract) {
        contractId = contract.getId();
        name = contract.getMember().getName();
        contractDate = contract.getContractDate();
        status = contract.getStatus();
        address = contract.getInstallation().getAddress();
    }
    public SimpleContractDTO(Long contractId,String name, LocalDateTime contractDate, ContractStatus contractStatus, Address address){
        this.contractId = contractId;
        this.name = name;
        this.contractDate = contractDate;
        this.status = contractStatus;
        this.address = address;
    }
}
