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
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");

        User newUser = userRepository.save(user);
        System.out.println("newUser : " + newUser);
    }

    @Test
    @Transactional
    public void read() {
        Optional<User> user = userRepository.findByAccount("TestUser03"); // type == Long
        user.ifPresent(selectedUser -> {
            selectedUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });
        });
    }

    @Test
    @Transactional
    public void update() {
        Optional<User> user = userRepository.findById(2L);
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
