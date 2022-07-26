package fianl.shop.domain.like;

import fianl.shop.Result;
import fianl.shop.SessionConst;
import fianl.shop.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LikeApiController {

    private final LikeService likeService;

    //관심상품 등록&해제
    @PostMapping("/item/like/{itemId}")
    public Result pushLike(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            @PathVariable Long itemId ) {

        //세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return new Result("로그인정보를 확인해 주세요.");
        }

        String s = likeService.pushLike(loginMember.getId(), itemId);

        if(s.equals("0")){
            return new Result("관심상품 등록 해제.");
        }
        return new Result("관심상품 등록 완료.");
    }


    //전체 관심 상품 조회
    @GetMapping("/item/like")
    public Result findAllLikes(){
        List<Like> all = likeService.findAllLikes();
        List<LikeDto> collect = all.stream()
                .map(like -> new LikeDto(like.getItem().getName(), like.getMember().getName())).collect(Collectors.toList());
        return new Result(collect.size(), collect, "전체 관심 상품 조회");
    }

    //개별 관심 상품 조회
    @GetMapping("/item/like/personal")
    public Result findAllPersonalLike(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member loginMember){
        List<Like> all = likeService.findByMember(loginMember);
        return new Result(all.size(), all, "내 관심 상품 조회");

    }

}
