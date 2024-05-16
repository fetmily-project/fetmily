//package org.zerock.petmilyproject.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.util.List;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import org.zerock.petmilyproject.domain.Orders;
//import org.zerock.petmilyproject.repository.OrdersRepository;
//
//@SpringBootTest
//@Log4j2
//public class OrderServiceTest {
//
//    @Autowired
//    OrdersService ordersService;
//    @Autowired
//    OrdersRepository ordersRepository;
////    @Test
////    public void testOrder() {
////        log.info(ordersService.order(2L,1L,1));
////    }
//
//
//
//    @Test
//    public void testFindOrdersByMemberId() {
//        // given
//        Long memberId = 1L; // 테스트할 memberId를 지정합니다.
//
//        // when
//        List<Orders> orders = ordersRepository.findOrdersByMemberId(memberId);
//
//        // then
//        assertNotNull(orders, "주문 목록은 null이 아니어야 합니다.");
//        assertFalse(orders.isEmpty(), "주문 목록은 비어있으면 안됩니다.");
//
//        // 테스트할 memberId에 해당하는 주문 목록을 확인합니다.
//        for (Orders order : orders) {
//            assertEquals(memberId, order.getMember().getMemberId(), "주문의 회원 ID가 올바른지 확인합니다.");
//        }
//    }
//
//    @Test
//    @Transactional
//    public void testPrintOrderDetails() {
//        // given
//        Long memberId = 1L; // 테스트할 주문 ID를 지정합니다.
//
//        // when
//        List<Orders> orders = ordersRepository.findOrdersByMemberId(memberId);// 주문 ID로 주문을 조회합니다.
//
//        for (Orders order : orders) {
//            // then
//            assertNotNull(order, "주문이 null이 아니어야 합니다.");
//
//            // 주문의 상세 정보를 출력합니다.
//            System.out.println("주문 ID: " + order.getOrderId());
//            System.out.println("회원 ID: " + order.getMember().getMemberId());
//            System.out.println("전화번호: " + order.getPhone());
//            System.out.println("주소: " + order.getAddr());
//            System.out.println("총 주문 가격: " + order.getTotalPrice());
//            System.out.println("주문 상태: " + order.getStatus());
//            System.out.println("주문 상세 정보:");
//            List<OrderItem> orderItems = order.getOrderItems();
//            for (OrderItem item : orderItems) {
//                System.out.println("상품 ID: " + item.getItem().getItemId());
//                System.out.println("상품 이름: " + item.getItem().getItemName());
//                System.out.println("주문 가격: " + item.getOrderPrice());
//                System.out.println("주문 수량: " + item.getCount());
//            }
//        }
//        // 주문의 상품 목록을 출력합니다.
//
//    }
//    }
//
//
//
