package fianl.shop.domain.like;

import fianl.shop.domain.item.Item;
import fianl.shop.domain.member.Member;
import fianl.shop.domain.member.MemberRepository;
import fianl.shop.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public String pushLike(Long memberId,Long itemId){

        //memeber 찾기
        Member member = memberRepository.findById(memberId).get();

        //Item 찾기
        Item item = itemRepository.findById(itemId).orElseThrow(()->new IllegalStateException("상품 ID를 확인해주세요"));

        //상품찾기
        Optional<Like> findLike = likeRepository.findByMemberAndItem(member, item);

        // todo : 비즈니스 로직을 엔티티로 옮기는거 어떤지 -> 완료
        if(findLike.isPresent()){
            //다운시킴
            likeRepository.delete(findLike.get());
            return "0";
        }else{
            //업 시킴
            likeRepository.save(Like.createLike(item, member));
            return "1";
        }


    }

}
