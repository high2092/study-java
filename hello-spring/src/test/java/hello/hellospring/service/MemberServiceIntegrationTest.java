package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("ha");

        // when
        Long id = memberService.join(member);

        // then
        Member result = memberService.findMember(id).orElseThrow(RuntimeException::new);
        assertThat(result.getName()).isEqualTo(member.getName());
    }

    @Test
    public void joinDuplicate() {
        // given
        Member member1 = new Member();
        member1.setName("ha");

        Member member2 = new Member();
        member2.setName("ha");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        assertThat(e.getMessage()).isEqualTo("회원명 중복");
    }


}
