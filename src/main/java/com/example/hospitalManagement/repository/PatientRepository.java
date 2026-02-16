package com.example.hospitalManagement.repository;

import com.example.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

// Spring Data JPA
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByName(String name);

    List<Patient> findByNameContainingIgnoreCaseOrderByIdDesc(String name);

    List<Patient> findByBirthDateOrEmail(LocalDate birth_date, String email);

    List<Patient> findByBirthDateBetween(LocalDate birthDate, LocalDate birthDate2);

    @Query(value = "SELECT * FROM Patient", nativeQuery = true)
    List<Patient> findAllPatients();

    @Modifying
    @Transactional
    @Query("UPDATE Patient p SET p.name = :name where p.id = :id")
    int updateNameById(@Param("name") String name, @Param("id") Long id);

    @Query("SELECT p FROM Patient p where p.blood_group = ?1")
    List<Patient> findByBloodGroup(@Param("blood_group") BloodGroupType blood_group);

    @Query("SELECT new com.example.hospitalManagement.dto.BloodGroupCountResponseEntity(p.blood_group,"
    + "Count(p)) FROM Patient p group by p.blood_group")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();

}
