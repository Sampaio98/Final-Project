package com.project.finalproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "SERVICE_TYPE")
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name")
    private String name;

    @Column(name = "date_insertion")
    private LocalDateTime dateInsertion;

    @OneToMany(mappedBy = "serviceType", fetch = FetchType.LAZY)
    private Set<Service> services;

    public ServiceType() {
        this.dateInsertion = LocalDateTime.now();
        this.services = new HashSet<>();
    }
}
