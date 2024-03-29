package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 테스트 간 의존성 없어야 함
    public void afterEach() {
        repository.clear();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("hhyuw");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // findById에 대한 테스트는 어디에?
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("ha");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("um");
        repository.save(member2);

        Member result = repository.findByName("ha").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("ha");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("um");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
