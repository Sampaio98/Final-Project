package com.project.finalproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer situation;

    @Column(name = "date_insertion")
    private LocalDateTime dateInsertion;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    private Integer evaluation;

    private Boolean worth;

    private String description;

    @JoinColumn(name = "user_fk")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name = "professional_fk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Professional professional;

    @JoinColumn(name = "service_type_fk")
    @ManyToOne(fetch = FetchType.EAGER)
    private AttendanceType attendanceType;

    @JoinColumn(name = "street_fk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Street street;

    public Attendance() {
        this.dateInsertion = LocalDateTime.now();
    }
}
