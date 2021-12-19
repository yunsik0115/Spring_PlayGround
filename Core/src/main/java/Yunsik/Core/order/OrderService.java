package Yunsik.Core.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice); // 최종 order 결과 반환
}
