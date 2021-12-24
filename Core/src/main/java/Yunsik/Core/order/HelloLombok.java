package Yunsik.Core.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter // Getter Setter ToString 자동생성 via Lombok
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfas");
        helloLombok.setAge(20);

        String name = helloLombok.getName();
        System.out.println("name = " + name);
        System.out.println("HelloLombok = " + helloLombok);
    }
}
