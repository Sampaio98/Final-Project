package com.project.finalproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.finalproject.annotation.UserUpdate;
import com.project.finalproject.dto.UserInsertDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Where(clause = "deleted = 'false'")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    private String name;

    @CPF
    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    @Column(unique = true)
    private String cpf;

    @Email(message = "Email inválido")
    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    @Column(unique = true)
    private String email;

    @Column(name = "date_insertion")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateInsertion;

    @JsonIgnore
    private Boolean deleted;

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    @Column(unique = true)
    private String phone;

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    @Column(unique = true)
    private String username;

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "user")
    private List<Street> streets;

    public User() {
        this.deleted = false;
        this.dateInsertion = LocalDateTime.now();
        this.attendances = new ArrayList<>();
        this.streets = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(cpf, user.cpf) &&
                Objects.equals(email, user.email) &&
                Objects.equals(dateInsertion, user.dateInsertion) &&
                Objects.equals(deleted, user.deleted) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, email, dateInsertion, deleted, phone, username, password);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", cpf='").append(cpf).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", dateInsertion=").append(dateInsertion);
        sb.append(", deleted=").append(deleted);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public void setUsername(String username) {
        this.username = username.toUpperCase();
    }

    public void softDelete() {
        this.deleted = true;
    }

    public void update(User userFromFront) {
        BeanUtils.copyProperties(userFromFront, this, "id", "attendances", "streets", "dateInsertion");
    }

    public User fromDTO(UserInsertDTO userInsertDTO) {
        BeanUtils.copyProperties(userInsertDTO, this);
        return this;
    }
}
