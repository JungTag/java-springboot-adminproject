package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String updatedBt;
}
