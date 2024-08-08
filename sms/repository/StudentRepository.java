package net.javaguides.sms.repository;

import net.javaguides.sms.dto.StudentsDto;
import net.javaguides.sms.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Students,Long>{

//    @Query("SELECT new net.javaguides.sms.dto.StudentsDto(s.id, s.firstName, s.lastName, s.email, s.password, s.username) " +
//            "FROM Students s WHERE s.username = :username")
//    Students findByUsername(@Param("username") String username);
//StudentsDto findByUsername(String username);


    @Query("SELECT new net.javaguides.sms.dto.StudentsDto(s.id, s.firstName, s.lastName, s.email, s.password, s.username) " +
            "FROM Students s WHERE s.username = :username")
    StudentsDto findByUsername(@Param("username") String username);

}
