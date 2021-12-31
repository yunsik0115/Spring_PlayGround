package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // JPA가 로딩될 때 JPA 관리대상임을 인식시킴.
// @Table(name = "USER") DB의 테이블 이름을 설정할 수 있다
public class Member {

    @Id // JPA에 PK가 무엇인지 알려줘야 함.
    private Long id;

    public Member(){
        // 기본생성자
    }

    public Member(Long id, String name) { // JPA는 기본적으로 내부적으로 reflection 등을 사용해서 동적으로 객체 생성을 해야 함 -> 기본생성자가 있어야 한다!
        this.id = id;
        this.name = name;
    }

    // @Column(열 이름 설정 가능)
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
