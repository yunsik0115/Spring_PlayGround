package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    @Column(name = "TEAM_NAME")
    private String name;

    @OneToMany(mappedBy = "team") // Team 입장에서 나는 1개, 멤버는 여러명, 나의 반대편에는 team으로 mapping이 되어있다.
    // 연관관계의 주인이 아닌 쪽은 읽기만 가능하다. (mappedby는 연관관계의 주인에는 사용하지 않는다, 종속되는 쪽에 주인을 지정한다.)
    // 여기에는 값을 넣어봐야 아무런 소용이 없다.
    private List<Member> members = new ArrayList<>(); // ADD할때 NullPointerException이 뜨지 않는다.

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
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
