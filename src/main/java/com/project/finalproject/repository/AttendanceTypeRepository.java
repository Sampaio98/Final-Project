package com.project.finalproject.repository;

import com.project.finalproject.model.AttendanceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceTypeRepository extends JpaRepository<AttendanceType, Long> {

    AttendanceType findByName(String name);
}
