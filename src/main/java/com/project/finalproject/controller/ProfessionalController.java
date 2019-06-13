package com.project.finalproject.controller;

import com.project.finalproject.dto.ProfessionalDTO;
import com.project.finalproject.service.ProfessionalService;
import com.project.finalproject.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/services")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @GetMapping("/professionals/page")
    public ResponseEntity<Page<ProfessionalDTO>> findAllDTOByPage(Pagination pagination) {
        return ResponseEntity.ok().body(professionalService.findAllDTOByPage(pagination));
    }

    @GetMapping("/professionals")
    public ResponseEntity<List<ProfessionalDTO>> findAll(){
        return ResponseEntity.ok().body(professionalService.findAll());
    }

}
