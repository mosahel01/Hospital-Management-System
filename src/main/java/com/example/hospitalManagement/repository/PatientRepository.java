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

    // Spring Data JPA is smart.
    // If you name a method following a specific pattern,
    // it writes the SQL for you automatically.

    // find the patient whose name matches exactly what I give you.
    Patient findByName(String name);

    List<Patient> findByBirthDateBetween(LocalDate birthDate, LocalDate birthDate2);

    List<Patient> findByBirthDateOrEmail(LocalDate birth_date, String email);

    List<Patient> findByNameContainingIgnoreCaseOrderByIdDesc(String name);

    // Sometimes the method names get too long or the logic is complex.
    // That’s when we use the @Query annotation to write the logic ourselves.
    @Query("SELECT p FROM Patient p where p.blood_group = ?1")
    List<Patient> findByBloodGroup(@Param("blood_group") BloodGroupType blood_group);

    @Query(
            "SELECT new"
                + " com.example.hospitalManagement.dto.BloodGroupCountResponseEntity(p.blood_group,Count(p))"
                + " FROM Patient p group by p.blood_group")
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();

    // Notice the nativeQuery = true. This tells Spring: "Don't try to be smart;
    // just run this exact SQL command directly against the database
    // Pure "SQL"
    @Query(value = "SELECT * FROM Patient", nativeQuery = true)
    List<Patient> findAllPatients();

    @Modifying // tells spring that this query will change data, not just read.
    @Transactional // safety net. It ensures that if the update fails halfway through, it "rolls
                   // back" so your database doesn't get corrupted.
    @Query("UPDATE Patient p SET p.name = :name where p.id = :id")
    int updateNameById(@Param("name") String name, @Param("id") Long id);
}
