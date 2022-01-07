package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){
            em.persist(item); // JPA 저장 전까지 ID값이 없음, 새로 생성한 객체
        } else{
            em.merge(item); //이미 DB에 등록되어 어디서 가져옴, Update와 비슷함.
            // 왠만하면 쓰지 말자, 일부 필드에 변경이 없거나 변경하지 않아 변수 할당이 아예 되지 않는 경우 NULL이 되는것이고
            // 변수 할당이 안된 필드가 NULL로 바뀌게 되어 복잡한 상황이 발생할 수 있다, 쓰지 말고 최선을 다해 더티체킹을 사용하는것을 권장!
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
