package com.project.finalproject.model;

import com.fasterxml.jackson.annotation.*;
import com.project.finalproject.dto.UserInsertDTO;
import com.project.finalproject.dto.UserUpdateDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Getter
@Setter
@Entity
@Where(clause = "deleted = 'false'")
@Table(name = "USER_ACCOUNT")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
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

    @JsonIgnore
    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<Address> address;

    public User() {
        this.deleted = false;
        this.dateInsertion = LocalDateTime.now();
        this.attendances = new ArrayList<>();
        this.address = new ArrayList<>();
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
        if (nonNull(name)) {
            this.name = name.toUpperCase();
        }
    }

    public void setUsername(String username) {
        if (nonNull(username)) {
            this.username = username.toUpperCase();
        }
    }

    public void softDelete() {
        this.deleted = true;
    }

    public User fromDTO(UserInsertDTO userInsertDTO) {
        BeanUtils.copyProperties(userInsertDTO, this);
        return this;
    }

    public User fromDTO(UserUpdateDTO userUpdateDTO) {
        BeanUtils.copyProperties(userUpdateDTO, this);
        return this;
    }

    public void update(User userFromFront) {
        if (isNull(userFromFront.getPassword())) {
            BeanUtils.copyProperties(userFromFront, this, "id", "attendances", "address", "dateInsertion", "password");
        } else {
            BeanUtils.copyProperties(userFromFront, this, "id", "attendances", "address", "dateInsertion");
        }
        addOrUpdateAddress(userFromFront);
    }

    private void addOrUpdateAddress(User userFromFront) {
        if (userFromFront.getAddress() != null) {

            userFromFront.getAddress().forEach(addressFromFront -> {
                if (isNull(addressFromFront.getId())) {
                    addressFromFront.setUser(this);
                    this.address.add(addressFromFront);
                } else {
                    updateAddressFromUser(addressFromFront);
                }
            });

            this.address.forEach(addressFromDb -> {
                if (!userFromFront.getAddress().contains(addressFromDb)) {
                    addressFromDb.setUser(null);
                }
            });
        }
    }

    private void updateAddressFromUser(Address addressFromFront) {
        this.address.forEach(addressFromDb -> {
            if (addressFromDb.getId().equals(addressFromFront.getId())) {
                addressFromDb.update(addressFromFront);
            }
        });
    }
}
