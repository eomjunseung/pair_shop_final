package fianl.shop.domain.conrtact;

import fianl.shop.domain.Installation;
import fianl.shop.domain.member.Member;
import lombok.Getter;
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

    public void setMember(Member member){
        this.member=member;
        member.getContracts().add(this);
    }

    public static Contract createContract(Member member, Installation installation) {
        //계약 생성 
        Contract contract = new Contract();
        
        //계약 주소 설정 쌍방셋팅
        contract.setInstallation(installation);
        installation.setContract(contract);
        
        // 멤버  - 계약 쌍방 셋팅 
        contract.setMember(member);
        
        contract.setStatus(ContractStatus.Ready);
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