package com.project.finalproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.finalproject.dto.AttendanceDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateInsertion;

    @Column(name = "date_modification")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateModification;

    private Integer evaluation;

    private Boolean worth;

    private String description;

    @JoinColumn(name = "user_fk")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn(name = "professional_fk")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"attendances", "address"})
    private Professional professional;

    @JoinColumn(name = "service_type_fk")
    @ManyToOne(fetch = FetchType.EAGER)
    private AttendanceType attendanceType;

    @JoinColumn(name = "address_fk")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("user")
    private Address address;

    public Attendance() {
        this.dateInsertion = LocalDateTime.now();
    }

    public Attendance fromDTO(AttendanceDTO attendanceDTO) {
        BeanUtils.copyProperties(attendanceDTO, this);
        this.professional = new Professional().fromDTO(attendanceDTO.getProfessionalDTO());
        return this;
    }
}
