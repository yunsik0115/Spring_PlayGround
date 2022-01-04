package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id @GeneratedValue
    private Long id;
    private String name;


    // Parent 중심으로 개발중인데 Child 알아서 persist 해주면 안될까...? 이 때 쓰는게 바로 cascade이다!
    // Parent를 perist할때 Collection안에 있는 친구들을 모두 Persist 날려주겠다! CASCADE.ALL
    // 영속성 전이는 연관관계를 매핑하는 것과는 아무 관련이 없다
    // 엔티티를 영속화 할 때 연관된 엔티티도 함께 영속화하는 편리함만을 제공한다, 두 엔티티가 생명주기를 같이할 때 사용하라
    // 1:N에 모두 걸어야하나? No -> 하나의 부모가 자식들을 관리할 때만 사용한다. (게시물 - 첨부파일의 관계의 경우), (파일을 여러군데서 사용하는 경우 사용은 적절하지 않다)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();
    // parent가 관리하는 컬렉션에서 빠지는 경우 remove 된다.

    public void addChild(Child child){
        childList.add(child);
        child.setParent(this);
    }

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
