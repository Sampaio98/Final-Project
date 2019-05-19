package com.project.finalproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.finalproject.model.Attendance;
import com.project.finalproject.model.Street;
import com.project.finalproject.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String name;
    private String cpf;
    private String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dateInsertion;

    private String phone;
    private String username;
    private String password;
    private List<Attendance> attendances;
    private List<Street> streets;

    public UserDTO(){
        this.attendances = new ArrayList<>();
        this.streets = new ArrayList<>();
    }

    public UserDTO convertToDto(User user){
        BeanUtils.copyProperties(user, this, "attendances", "streets", "dateInsertion");
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
                Objects.equals(dateInsertion, userDTO.dateInsertion) &&
                Objects.equals(phone, userDTO.phone) &&
                Objects.equals(username, userDTO.username) &&
                Objects.equals(password, userDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, email, dateInsertion, phone, username, password);
    }
}
