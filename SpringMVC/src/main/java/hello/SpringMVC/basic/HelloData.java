package hello.SpringMVC.basic;

import lombok.Data;

@Data // Getter Setter ToString (Equals Hashcode) RequriedArgsConstructor 자동생성
public class HelloData {
    private String username;
    private int age;
}
