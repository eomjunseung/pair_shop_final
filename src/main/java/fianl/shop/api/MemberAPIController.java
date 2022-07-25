package fianl.shop.api;


import fianl.shop.Result;
import fianl.shop.domain.member.Member;
import fianl.shop.domain.member.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberAPIController {

    private final MemberService memberService;

//가입

    //v1
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }



    //v2
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {

        Member member = new Member();
        member.setName(request.getName());
        member.setPassword(request.getPassword());
        Long id = memberService.join(member); //저장 시키고

        return new CreateMemberResponse(id); //아이디반환
    }



//수정
    //v2 (v1없음)
    //DTO
    @PatchMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                                @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getName());// 굳이 반환을 안했음 -> 내부에서 반환 시켜도 준영속 인 애를 반환해서
        Member findMember = memberService.findOne(id);// 다시 조회 해서 찾기
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

//조회

    //v1 - entity
    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    }


    //v2 - DTO -  {} 껍대기 씌워서 반환                   {}:객체 []:배열
    @GetMapping("/api/v2/members")
    public Result membersV2() {
        List<Member> findMembers = memberService.findMembers();
        //엔티티 -> DTO 변환
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());
        return new Result(collect.size(),collect,"반환성공");
    }



    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }


    @Data @AllArgsConstructor
    @NoArgsConstructor // 이거 필수네...
    static class CreateMemberRequest {
        private String name;
        private String password;
    }
    @Data @AllArgsConstructor
    static class CreateMemberResponse {
        private Long id;
    }


    @Data @AllArgsConstructor
    @NoArgsConstructor
    static class UpdateMemberRequest{
        private String name;
    }
    @Data @AllArgsConstructor
    static class UpdateMemberResponse{
        private Long id;
        private String name;
    }
}