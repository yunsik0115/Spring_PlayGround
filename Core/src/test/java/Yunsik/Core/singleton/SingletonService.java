package Yunsik.Core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    // 의존관계상 클라이언트가 구체 클래스에 의존해야 함.\
    // 다른 애들이 구체 클래스의 .getinstance에 의존하게 됨 => OCP 위반 가능성
    // TEST에 어려움 내부 속성 추가 어려움 private 생성자로 자식 클래스 만들기 어려움 유연성이 떨어짐, 안티패턴이라고 불리기도 한다.
    // static 영역에 "하나"만 객체가 만들어져 올라감

    public static SingletonService getInstance(){
        return instance; // 조회할 때는 이 메서드를 사용함.
    }

    private SingletonService(){} // 생성자 호출을 private으로 막아서 new 객체로 생성을 막는다.

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
