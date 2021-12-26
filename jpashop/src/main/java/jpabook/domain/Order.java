package jpabook.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
//테이블명이 ORDER 가 아니라 ORDERS 인 것은 데이터베이스가 order by 때문에 예약어로 잡고 있는 경우가 많다. 그래서 관례상 ORDERS 를 많이 사용한다.
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne // Order와 Member는 다대일 관계
    @JoinColumn(name = "member_id") // 맵핑을 무엇으로 할 것인가 Foriegn Key?
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne // Foreign key를 어디에 둘 것인가? -> Access를 많이 하는 곳에 둔다.
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // Hibernate가 알아서 지원해 줌

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Order CANCEL 두개  상황

}
