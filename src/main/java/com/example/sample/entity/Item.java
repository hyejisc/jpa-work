package com.example.sample.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 상속 매핑
@DiscriminatorColumn // dtype
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

}
