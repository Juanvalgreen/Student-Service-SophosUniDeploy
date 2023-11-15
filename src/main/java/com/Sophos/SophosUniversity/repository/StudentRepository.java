package com.Sophos.SophosUniversity.repository;

import com.Sophos.SophosUniversity.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {

    @Query(value = "SELECT * FROM students s WHERE CAST(s.student_id AS VARCHAR) LIKE :searchId || '%'", nativeQuery = true)
    List<Student> searchAllStudentsById(@Param("searchId") Long searchId);

    @Query(value = "SELECT * FROM students s WHERE LOWER(s.student_full_name) LIKE LOWER(:searchName || '%')", nativeQuery = true)
    List<Student> searchAllStudentsByName(@Param("searchName") String searchName);


}
