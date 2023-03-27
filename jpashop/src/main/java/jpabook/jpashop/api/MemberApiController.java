package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMember(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);

        return new CreateMemberResponse(id);

    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMember(@PathVariable Long id, @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getName());

        Member member = memberService.findOne(id); // <- 수정 후 조회. 해야 하나?
        return new UpdateMemberResponse(member.getId(), member.getName());
    }

    @Getter
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Getter
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Getter
    static class UpdateMemberRequest {
        private String name;
    }

    @Getter
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }
}
