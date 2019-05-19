package com.project.finalproject.controller;

import com.project.finalproject.model.AttendanceType;
import com.project.finalproject.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/services")
public class AttendanceTypeController {

    @Autowired
    private AttendanceService service;

    @PostMapping(value = "/attendaceType")
    public ResponseEntity<Void> insert(@Valid @RequestBody AttendanceType attendanceType){
        AttendanceType typeSaved = service.insert(attendanceType);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(typeSaved.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/attendaceType/{id}")
    public ResponseEntity<AttendanceType> findById(@PathVariable("id") Long id){
        AttendanceType type = service.findById(id);
        return ResponseEntity.ok().body(type);
    }

    @GetMapping(value = "/attendaceTypes")
    public ResponseEntity<List<AttendanceType>> findAll(){
        List<AttendanceType> attendanceTypes = service.findAll();
        return ResponseEntity.ok().body(attendanceTypes);
    }

    @DeleteMapping(value = "/attendaceType/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
