package com.project.finalproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.finalproject.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String username;

    public UserDTO(){
    }

    public UserDTO convertToDto(User user){
        BeanUtils.copyProperties(user, this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) &&
                Objects.equals(name, userDTO.name) &&
                Objects.equals(cpf, userDTO.cpf) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(phone, userDTO.phone) &&
                Objects.equals(username, userDTO.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, email, phone, username);
    }
}
