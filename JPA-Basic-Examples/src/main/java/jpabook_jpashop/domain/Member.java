package jpabook_jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member{

    @Id @GeneratedValue(strategy = GenerationType.AUTO) // 기본값이라 생략해도 됨
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    @Embedded
    private Period period;

    @Embedded
    private Address address;


}
