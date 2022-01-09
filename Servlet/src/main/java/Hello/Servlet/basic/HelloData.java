package Hello.Servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
// JSON 형식의 데이터를 객체로 바꿀 수 있도록(보통 그대로 안씀 객체상태로 씀) 객체 생성
public class HelloData {
    private String username;
    private int age;
}
