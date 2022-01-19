package Hello.SpringMVCItemService.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>();
    // 실제는 해시맵(멀티스레드) 사용 안되고 ConcurrentHashmap 사용 static 사용했다는 점에 주의
    private static long sequence = 0L;
    // atomiclong 등등 다른 타입 사용을 권장.

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values()); // new로 반환시 store 값에는 지장이 안감.
    }

    // 별도의 업데이트 파라미터 값 3개 용 객체를 만드는게 맞다, ID가 사용이 되지 않고 있음
    // 정석은 ItemParameterDTO를 객체로 만들고 파라미터를 3개 넣어놓는게 맞음.
    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }
    
    public void clearStore(){
        store.clear();
        // hashmap 초기화
    }

}
