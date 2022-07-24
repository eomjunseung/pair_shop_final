package fianl.shop.eom.domain.like;

import fianl.shop.domain.item.Item;
import fianl.shop.eom.domain.like.Like;
import fianl.shop.eom.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {

    Optional<Like> findByMemberAndItem(Member member, Item item);
}
