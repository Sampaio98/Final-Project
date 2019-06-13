package com.project.finalproject.dto;

import com.project.finalproject.annotation.UserUpdate;
import com.project.finalproject.model.Address;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    private String phone;

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório")
    private String username;

    private String password;

    private List<Address> address;

    public UserUpdateDTO(){
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
                Objects.equals(phone, that.phone) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, email, phone, username, password);
    }
}
