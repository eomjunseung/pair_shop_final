package fianl.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fianl.shop.eom.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Contract {

    @Id @GeneratedValue
    @Column(name = "contract_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //ToMany  : LAZY default
    @OneToMany(fetch=LAZY,  mappedBy = "contract", cascade = CascadeType.ALL) //Order가 저장되면 --> 얘도 같이 저장해라~
    private List<ContractItem> contractItems = new ArrayList<>(); //관례상 빈 ArrayList넣어줌

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "installation_id")
    private Installation installation;

    private LocalDateTime contractDate; //주문시간

    @Enumerated(EnumType.STRING)
    private ContractStatus status; //주문상태 [ORDER, CANCEL]


    public static Contract createContract(Member member, Installation installation, ContractItem contractItem) {
        Contract contract = new Contract();
        contract.setInstallation(installation);
        contract.getContractItems().add(contractItem);
//        contract.setContractDate();
        return contract;
    }


    public String cancel() {

        if(status==ContractStatus.Ready){
            status = ContractStatus.CANCEL;
            return "1";
        }
        return "0";
    }

}