package Hello.SpringMVCItemService.web.basic;

import Hello.SpringMVCItemService.domain.Item;
import Hello.SpringMVCItemService.domain.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    /*@Autowired
    public BasicItemController(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }*/

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String saveItemV1(@RequestParam String itemName, @RequestParam int price, @RequestParam Integer quantity, Model model){

        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }

    //@PostMapping("/add")
    public String saveItemV2(@ModelAttribute("item") Item item, Model model){
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String saveItemV3(@ModelAttribute Item item, Model model){
        // Item -> item, HelloData -> helloData가 modelAttribute에 담긴다.
        itemRepository.save(item);
        // model.addAttribute("item", item); 생략 가능
        return "basic/item";
    }

    //@PostMapping("/add")
    public String saveItemV4(Item item){
        // Item -> item, HelloData -> helloData가 modelAttribute에 담긴다.
        itemRepository.save(item);
        // model.addAttribute("item", item); 생략 가능
        return "basic/item";
    }

    //@PostMapping("/add")
    public String saveItemV5(Item item){
        // Item -> item, HelloData -> helloData가 modelAttribute에 담긴다.
        itemRepository.save(item);
        // model.addAttribute("item", item); 생략 가능
        return "redirect:/basic/items/" + item.getId();
        // 한글이나 띄어쓰기 있는 경우 인코딩이 되지 않아 위험함 -> 이 문제는 redirect Attributes를 이용해 해결 가능
    }

    @PostMapping("/add")
    public String saveItemV6(Item item, RedirectAttributes redirectAttributes){
        // Item -> item, HelloData -> helloData가 modelAttribute에 담긴다.
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        // model.addAttribute("item", item); 생략 가능
        return "redirect:/basic/items/{itemId}";
        // 한글이나 띄어쓰기 있는 경우 인코딩이 되지 않아 위험함 -> 이 문제는 redirect Attributes를 이용해 해결 가능
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * Dataset for Test added
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
