package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"user", "item"}) // 상호참조가 해제됨, 연관관계 설정에 대한 변수에는 반드시 exclude해주는 게 좋다
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;

    // N : 1
    @ManyToOne
    private User user; // 연관관계는 객체이름으로 설정해줘야 함

    // N : 1
    @ManyToOne
    private Item item;
}
