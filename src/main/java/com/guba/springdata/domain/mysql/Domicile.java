package com.guba.springdata.domain.mysql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "domicile")
@Setter
@Getter
@ToString
public class Domicile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "street_num")
    private Integer streetNum;

    @Column(name = "department_num_floor")
    private Integer departNumFloor;

    @Column(name = "department_num_room")
    private Integer departNumRoom;
}
