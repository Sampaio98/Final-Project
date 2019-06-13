package com.project.finalproject.dto;

import com.project.finalproject.model.Professional;
import org.springframework.beans.BeanUtils;

public class ProfessionalDTO extends UserDTO {

    public ProfessionalDTO(){
        super();
    }

    public ProfessionalDTO fromEntity(Professional professional){
        BeanUtils.copyProperties(professional, this);
        return this;
    }
}
