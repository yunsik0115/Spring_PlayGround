package hellojpa;

import javax.persistence.*;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //@Column(name="TEAM_ID")
    //private Long teamId;

    // DB 관점으로 Annotation 작성
    @ManyToOne // Member 입장에서는 Many, Team 입장에서는 One
    @JoinColumn(name = "TEAM_ID") // TEAM_ID와 JOIN MAPPING이 끝남.
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getId() {
        return id;
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
