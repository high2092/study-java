package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clear();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("ha");

        // when
        Long id = memberService.join(member);

        // then
        Member result = memberService.findMember(id).get();
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

    @Test
    void viewAllMembers() {
    }

    @Test
    void findMember() {
    }
}