package spring_introduction.spring_introduction_project.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Identity -> DB가 알아서 생성해주는 것
    private Long id;
    //@Column(name = "username") DB 기준 Username column
    private String name;

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
