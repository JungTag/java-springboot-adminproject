package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String content;

    // 1 : N
    // LAZY = 지연로딩, EAGER = 즉시로딩
    // LAZY: 연관관계 설정된 모든 테이블에 대해 JOIN X, 성능이 더 우수
    // EAGER: 연관관계 설정된 모든 테이블에 대해 JOIN O, 1:1 등 연관관계가 하나일 때 추천

    // LAZY = SELECT * FROM item where id = ?

    // EAGER =
    // item_id = order_detail.item_id
    // user_id = order_detail.user_id
    // where item.id?
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item") // orderDetailList라는 변수의 item멤버에 매핑
    private List<OrderDetail> orderDetailList;
}
