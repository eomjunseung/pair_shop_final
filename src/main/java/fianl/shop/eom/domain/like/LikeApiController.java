package fianl.shop.eom.domain.like;

import fianl.shop.Result;
import fianl.shop.SessionConst;
import fianl.shop.eom.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

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

//        출력 테스트
//        System.out.println("checkcheck");
//        System.out.println(loginMember.getId());
//        System.out.println(loginMember.getName());
//        System.out.println(itemId);

        String s = likeService.pushLike(loginMember.getId(), itemId);

        if(s.equals("0")){
            return new Result("관심상품 등록 해제.");
        }
        return new Result("관심상품 등록 완료.");
    }


}
