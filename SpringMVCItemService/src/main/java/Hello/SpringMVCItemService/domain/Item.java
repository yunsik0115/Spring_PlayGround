package Hello.SpringMVCItemService.domain;

import lombok.Data;

// @Getter @Setter // @Data는 위험함, 핵심 도메인 모델에 사용하기 위험 예측하지 못하게 동작할 수 있음
// DTO(Data Transfer Object)의 경우는 써도 무방하나, 이전에 테스트를 해보고 사용할 것.
@Data // 현재는 예제이니 Data를 사용함.
public class Item {
    private Long id;
    private String name;
    private Integer price; // price가 null일때도 있다고 가정하는 것
    private Integer quantity; // quantity가 null일때도 있다고 가정하는 것


    public Item(){

    }

    public Item(String name, Integer price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
