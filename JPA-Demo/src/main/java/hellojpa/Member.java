package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //@Column(name="TEAM_ID")
    //private Long teamId;

    @ManyToOne(fetch = FetchType.LAZY) // Proxy 객체로 조회하여, 직접 호출하기 전까지는 가짜 객체로 대체
    @JoinColumn
    private Team team;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<MemberProduct> getProducts() {
        return products;
    }

    public void setProducts(List<MemberProduct> products) {
        this.products = products;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
