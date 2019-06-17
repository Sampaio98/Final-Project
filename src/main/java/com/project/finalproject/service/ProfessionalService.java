package com.project.finalproject.service;

import com.project.finalproject.dto.ProfessionalDTO;
import com.project.finalproject.exception.handler.ObjectNotFoundException;
import com.project.finalproject.model.Professional;
import com.project.finalproject.repository.ProfessionalRepository;
import com.project.finalproject.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfessionalService {

    private static final String CLASS_NAME = "Profissional não encontrado.";

    @Autowired
    private ProfessionalRepository repository;


    public Page<ProfessionalDTO> findAllDTOByPage(Pagination pagination) {
        Pageable pageRequest = new PageRequest(pagination.getPage(),
                pagination.getLinesPerPage(),
                Sort.Direction.valueOf(pagination.getDirection()),
                pagination.getOrderBy());
        Page<Professional> professionals = repository.findAll(pageRequest);
        return professionals.map(professional -> new ProfessionalDTO().fromEntity(professional));
    }

    public List<ProfessionalDTO> findAll() {
        List<Professional> professionals = repository.findAll();
        return professionals
                .stream()
                .map(professional -> new ProfessionalDTO().fromEntity(professional))
                .collect(Collectors.toList());
    }

    public Professional findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(CLASS_NAME));
    }
}
