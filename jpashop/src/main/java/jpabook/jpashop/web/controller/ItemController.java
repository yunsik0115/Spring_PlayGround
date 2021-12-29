package jpabook.jpashop.web.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form){
        Book book = new Book();
        book.setName(form.getName()); // Setter를 제거하는게 더 좋은 설계다 Order에서 본것처럼 생성자 메소드 사용
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems(); //DTO 변환 권장 API에서 엔티티가 넘어가면 데이터 그대로 노출되고 엔티티 변경시 API 스펙 불안정 야기
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Book item = (Book) itemService.findOne(itemId); // casting not recommended but for better example

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());
        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form) {

       //Book book = new Book();

        //book.setId(form.getId()); // 새로운 객체이나 id 세팅되어있다. JPA에 들어갔다 나온 놈
        // JPA가 인식 가능한 ID를 보유한 친구 ==> 이런 친구를 준영속 상태의 객체라고 한다. (근데 JPA의 영속성 컨텍스트의의 관리 대상은 아니다)
        // "새로운 객체를 만들어서" 기본적으로 JPA가 관리 안함. 아무리 값을 고쳐도 DB 업데이트 쿼리 안 날아감.
        // 준영속 엔티티를 수정하는 방법? 1) 변경감지 기능의 사용 2) merge(병합)의 사용 머지를 막 쓰는게 아니다!

        // 어설프게 아래 방법처럼 엔티티를 생성하는 방식은 좋지 않다
        //book.setName(form.getName()); // Setter를 제거하는게 더 좋은 설계다 Order에서 본것처럼 생성자 메소드 사용
        //book.setPrice(form.getPrice());
        //book.setStockQuantity(form.getStockQuantity());
        //book.setAuthor(form.getAuthor());
        //book.setIsbn(form.getIsbn());
        //book.setIsbn(form.getIsbn());

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
        return "redirect:/items";
    }

}
