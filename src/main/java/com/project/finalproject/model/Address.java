package com.project.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String number;

    private String observation;

    private String cep;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Attendance> attendances;

    public Address() {
        this.attendances = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(name, address.name) &&
                Objects.equals(number, address.number) &&
                Objects.equals(observation, address.observation) &&
                Objects.equals(cep, address.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number, observation, cep);
    }

    public void update(Address address) {
        BeanUtils.copyProperties(address, this, "id", "attendances", "user");
    }
}
