package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.exception.NotEnoughStockException;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품_주문() {
        // G
        Member member = new Member();
        member.setName("um");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);


        int stockQuantity = 10;
        int price = 10000;
        int orderCount = 2;

        Book book = new Book();
        book.setName("수학의 정석");
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);

        // W
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // T
        Order order = orderRepository.findOne(orderId);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.ORDERED);
        assertThat(order.getOrderItems().size()).isEqualTo(1);
        assertThat(order.getTotalPrice()).isEqualTo(price * orderCount);
        assertThat(book.getStockQuantity()).isEqualTo(stockQuantity - orderCount);
    }

    @Test
    public void 주문_취소() {// G
        Member member = new Member();
        member.setName("um");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);

        int stockQuantity = 10;
        int price = 10000;
        int orderCount = 3;

        Book book = new Book();
        book.setName("수학의 정석");
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        // W
        orderService.cancelOrder(orderId);

        // T
        Order order = orderRepository.findOne(orderId);

        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCELED);
        assertThat(book.getStockQuantity()).isEqualTo(stockQuantity);
    }

    @Test
    public void 재고_부족() {
        // G
        Member member = new Member();
        member.setName("um");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);

        int stockQuantity = 10;
        int price = 10000;
        int orderCount = 11;

        Book book = new Book();
        book.setName("수학의 정석");
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);

        // W
        NotEnoughStockException e = Assertions.assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), book.getId(), orderCount));

        // T
        assertThat(e.getMessage()).isEqualTo("재고 부족");

    }
}