package com.zipportal.zipportal.Repository;


import com.zipportal.zipportal.Model.Assignment;
import com.zipportal.zipportal.Model.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @Query(value = "SELECT a FROM Assignment a WHERE a.assignment_Url = :assignment_Url")
    Assignment findAssignmentsByAssignment_Url(@Param("assignment_Url") String assignment_Url);

    @Query(value = "SELECT COUNT(a) FROM Assignment a WHERE a.assignment_Url = :assignment_Url")
    Long countAssignmentByAssignment_UrlEquals(@Param("assignment_Url") String assignment_url);
}
