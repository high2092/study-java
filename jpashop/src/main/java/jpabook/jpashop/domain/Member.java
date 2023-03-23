package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String name;

    @Embedded // 클래스 정의부에 @Embeddable 만 있어도 되지만, 명시적 프로그래밍
    private Address address;

    @OneToMany(mappedBy = "member") // 클래스에 정의된 멤버 변수명. 읽기 전용 같은 느낌
    private List<Order> orders = new ArrayList<>(); // 컬렉션은 필드에서 초기화 후, 변경하지 말 것 (hibernate Entity 영속화 관련 문제)
}
