package fianl.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fianl.shop.domain.conrtact.ContractItem;
import lombok.Data;

@Data
public class ContractItemDtoV5 {
    @JsonIgnore
    private Long contractId;
    private String itemName;//상품 명 -(item)에 존재
    private int contractPrice; //계약 가격-(contract_item)에 존재

    public ContractItemDtoV5(String itemName,int contractPrice){
        this.itemName=itemName;
        this.contractPrice=contractPrice;
    }
    public ContractItemDtoV5(Long contractId,String itemName,int contractPrice){
        this.contractId = contractId;
        this.itemName=itemName;
        this.contractPrice=contractPrice;
    }


    public ContractItemDtoV5(ContractItem contractItem){
        itemName=contractItem.getItem().getName();
        contractPrice = contractItem.getPrice();
    }
}
