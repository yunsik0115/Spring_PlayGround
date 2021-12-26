package jpabook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded // JPA 내장타입인 Address를 이용한다.
    private Address address;

    @OneToMany(mappedBy = "member") // 일대다 관계 (멤버 입장에서)
    private List<Order> orders = new ArrayList<>();
    // mappedby를 적는 순간 읽기 전용으로 변한다.
    // 값을 넣는다고 해서 Foriegn 키가 변하지 않는다.


}
