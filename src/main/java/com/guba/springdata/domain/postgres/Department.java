package com.guba.springdata.domain.postgres;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "department")
@Setter
@Getter
@ToString(exclude = "careers")
public class
Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Career> careers;
}
