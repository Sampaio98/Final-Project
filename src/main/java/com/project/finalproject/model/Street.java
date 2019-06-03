package com.project.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String number;

    private String observation;

    private Boolean deleted;

    @Column(unique = true)
    private String cep;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "user_fk")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "street", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Attendance> attendances;

    public Street(){
        this.deleted = false;
        this.attendances = new ArrayList<>();
    }

}
