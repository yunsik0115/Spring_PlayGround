package hellojpa;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // Join 전략의 사용, 기본값 = 싱글테이블
@Inheritance(strategy = InheritanceType.JOINED)
// TABLE PER CLASS - ITEM 테이블이 만들어지지 않고 테이블마다 중복 필드 생성한다, 명확하지 않은 조회시 Union을 통한 모든 테이블 조회함. (비효율적)
// Table per class는 ORM DBA 둘다 싫어함 나중에 큰 대가를 치르게 된다. -> 새로 추가할 때마다 시스템 변경 필요
@DiscriminatorColumn //DTYPE 생성
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
