package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity // 다른 패키지에 같은 이름에 클래스가 있는 경우 JPA 내에서는 name 속성을 사용해서 구분한다
// JPA가 로딩될 때 JPA 관리대상임을 인식시킴. (DATABASE 테이블 맵핑 대상이다!)
// @Table(name = "USER") DB의 테이블 이름을 설정할 수 있다
public class Member {
    @Id
    private Long id;

    @Column// (name = "name") // DB Column 명 지정
    private String username;

    /*
    updatable (변경이 가능한가?), Insertable(등록이 가능한가?)
    nullable - false로 설정하면 notnull 제약조건이 생성된다.
    unique - @table의 uniqueconstraints와 같으나 한 컬럼에만 간단히 유니크 제약조건을 걸때 사용한다
    근데 알 수 없는 문자열이 이름으로 생성되기 때문에 Table에 uniqueconstraints를 사용하는 걸 선호
    column definition - 제약조건 SQL로 내가 커스터마이징 할 수 있다. (특정 DB 종속적 옵션들 추가 가능)
    Precision, Scale -> Big Decimal 타입이나 Integer에서 사용가능, precision은 소수점을 포함한 전체 자리수를
    scale은 소수의 자리수다. float, double은 적용되지 않는다. 아주 큰 숫자나 정밀한 소수 다룰때 사용용
    */

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    /*
    ordinal 사용 금지, enum에 새로운 내용 추가할 경우 기존 데이터들 전부 문제 생김
    0 - 학생 1 - 선생님
    0 - 학생 1 - 교직원 2 - 선생님 으로 되는 경우 모든 1번이었던 선생님들이 새로운 DB에서는 전부 교직원으로 인식됨(굉장히 위험함)
     */

    @Temporal(TemporalType.TIMESTAMP) // Temporal - 날짜 타입
    private Date createdDate;
    // 자바는 기본적으로 타입이 하나(날짜 + 시간)인데, DB는 날짜, 시간, 날짜 + 시간을 구분하기 때문에 매핑 정보를 주어야 한다.

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // private LocalDate testLocalDate; -- > 알아서 올려줌 최신버전에서는
    // private LocalDateTime testLocalDateTime; -- > 알아서 올려줌 최신버전에서는

    @Lob // varchar를 초과하는 큰 컨텐츠를 넣고자 하는 경우, 문자열 타입의 경우 자동으로 CLOB으로 생성
    private String description;

    // DB에 연관 없는 변수 생성하고 싶은 경우 Transient로 생성(메모리에서만 사용할 경우)

    public Member(){

    }

//Getter, Setter…
}
