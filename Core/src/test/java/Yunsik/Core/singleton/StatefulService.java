package Yunsik.Core.singleton;

import org.junit.jupiter.api.Test;

public class StatefulService {
    // private int price; // 상태를 유지하는 필드 10000원이 들어있던게 -> 20000원으로 바뀐다
    // 공유되지 않는 지역변수 파라미터 사용이 해결책
    public int order(String name, int price){
        System.out.println("name = " + name + " price" + price);
        //this.price = price; //여기가 문제다
        return price;
    }

    /*public int getPrice(){
        return price;
    }*/
}
