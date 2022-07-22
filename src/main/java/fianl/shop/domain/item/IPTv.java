package fianl.shop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("iptv")
@Getter
@Setter
public class IPTv extends Item{
    private String ipTvGrade;
    private String etc;
}
