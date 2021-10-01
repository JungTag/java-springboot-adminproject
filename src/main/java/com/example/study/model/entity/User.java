package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "user") 테이블명 == 클래스명인 경우 생략 가능
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "account") 컬럼명 == 멤버변수명인 경우 생략 가능
    private String account;

    private String email;

    private String phoneNumber; // JPA가 Camel Case와 Snake Case를 자동적으로 매핑해줌

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") // mappedBy에 해당하는 값은 OrderDetail클래스의 user와 같아야 한다
    private List<OrderDetail> orderDetailList;
}
