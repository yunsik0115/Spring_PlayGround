package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){ // Might Use DTO if there is too many parameters
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
        // save, merge, persist를 이 경우는 전부 호출할 필요 없다 더티체킹으로 해결할 수 있다.
        // findOne으로 찾아온 객체는 현재 영속상태에 해당한다. 이 경우 변하면 상태변경 -> flush를 날린다.
        // 이런식으로 변경지점이 엔티티로 가게 하자
        // 의미있는 변경은 메서드로 엔티티에 집어넣자 조금만 복잡해져도 로직이 어디서 발생하는지 찾기 힘들다
    }
    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
