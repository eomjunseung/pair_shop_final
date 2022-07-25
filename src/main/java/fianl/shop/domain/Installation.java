package fianl.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fianl.shop.domain.conrtact.Contract;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Installation {

    @Id @GeneratedValue
    @Column(name="installation_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "installation",fetch = FetchType.LAZY)
    private Contract contract;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private InstallationStatus status;
}
