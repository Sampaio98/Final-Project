package com.project.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.project.finalproject.dto.UserInsertDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@JsonTypeName("Professional")
public class Professional extends User{

    private String code;

    @OneToMany(mappedBy = "professional", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "professional")
    private List<Attendance> attendances;

    public Professional() {
        super();
        this.attendances = new ArrayList<>();
    }

    public Professional fromDTO(UserInsertDTO userInsertDTO) {
        BeanUtils.copyProperties(userInsertDTO, this);
        return this;
    }

    public void generateCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getUsername(), 0, 2);
        sb.append(this.getDateInsertion().getDayOfMonth());
        sb.append(this.getId());
        this.code = sb.toString();
    }
}
