package com.project.finalproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.finalproject.annotation.UserUpdate;
import com.project.finalproject.model.Address;
import com.project.finalproject.model.Attendance;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@UserUpdate
public class UserUpdateDTO {

    private Long id;

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    private String name;

    @CPF
    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    private String cpf;

    @Email(message = "Email inválido")
    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateInsertion;

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    private String phone;

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    private String username;

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    private String password;

    private List<Attendance> attendances;

    private List<Address> address;

    public UserUpdateDTO(){
        this.attendances = new ArrayList<>();
        this.address = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdateDTO that = (UserUpdateDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(email, that.email) &&
                Objects.equals(dateInsertion, that.dateInsertion) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, email, dateInsertion, phone, username, password);
    }
}
