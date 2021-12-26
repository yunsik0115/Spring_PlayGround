package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name="category_id"),
            inverseJoinColumns = @JoinColumn(name="item_id")) // 중간 테이블 할당 필요
    private List<Item> items = new ArrayList<>();


    // 셀프로 양방향 연관관계 만드는 방법법
    @ManyToOne
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
