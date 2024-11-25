package com.student.repositorry;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.student.model.Students;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {
}
