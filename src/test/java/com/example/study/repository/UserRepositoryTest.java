package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@WebAppConfiguration
public class UserRepositoryTest extends StudyApplicationTests {
    // Dependency Injection (DI)
    // 객체를 스프링이 직접 관리하고, 이러한 의존성들을 주입시키겠다는 의미
    // DI는 싱글톤, userRepository는 하나만 만들고 이를 @Autowired 어노테이션을 이용해 관리하는 것
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test03@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        // Builder 패턴
        User u = User.builder()
                .account(account).
                password(password).
                status(status).
                email(email).
                build();

        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
    }

    @Test
    @Transactional
    public void read() {
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        user.getOrderGroupList().stream().forEach(orderGroup -> {
            System.out.println("---------------주문묶음---------------");
            System.out.println("수령인 : " + orderGroup.getRevName());
            System.out.println("수령지 : " + orderGroup.getTotalPrice());
            System.out.println("총금액 : " + orderGroup.getRevAddress());
            System.out.println("총수량 : " + orderGroup.getTotalQuantity());
            System.out.println("---------------주문상세---------------");
            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문 상품: " + orderDetail.getItem().getName());
                System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태 : " + orderDetail.getStatus());
                System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());
            });
        });
        Assert.assertNotNull(user);
    }

    @Test
    @Transactional
    public void update() {
        Optional<User> user = userRepository.findById(2L);
        // Chain 패턴
        // user.setEmail(email).setPhoneNumber(phoneNumber).setStatus(status);
        // User u = new User().setEmail(email).setPhoneNumber(phoneNumber).setStatus(status);
        user.ifPresent(selectedUser -> {
            selectedUser.setAccount("PPPP");
            selectedUser.setUpdatedAt(LocalDateTime.now());
            selectedUser.setUpdatedBy("method update()");

            userRepository.save(selectedUser);
        });
    }

    @Test
    @Transactional // 마지막에 rollback
    public void delete() {
        Optional<User> user = userRepository.findById(3L);

        Assert.assertTrue(user.isPresent()); // true
        user.ifPresent(selectedUser -> {
            userRepository.delete(selectedUser);
        });
        Optional<User> deletedUser = userRepository.findById(3L);
        Assert.assertFalse(deletedUser.isPresent()); // false
    }
}
