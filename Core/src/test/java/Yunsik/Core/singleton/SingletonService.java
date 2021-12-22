package Yunsik.Core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    // static 영역에 "하나"만 객체가 만들어져 올라감

    public static SingletonService getInstance(){
        return instance; // 조회할 때는 이 메서드를 사용함.
    }

    private SingletonService(){} // 생성자 호출을 private으로 막아서 new 객체로 생성을 막는다.

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
