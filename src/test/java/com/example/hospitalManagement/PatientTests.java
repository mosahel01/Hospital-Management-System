package com.example.hospitalManagement;


import com.example.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.entity.type.BloodGroupType;
import com.example.hospitalManagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Sql(scripts = "classpath:data.sql") // Path to a SQL Script that inserts data
public class PatientTests {

    @Autowired
    PatientRepository patientRepository;

    @Test
    public void testTransactionalMethods() {
        Patient patientName = patientRepository.findByName("James McNulty ");
        List<Patient> patientContaining = patientRepository.findByNameContainingIgnoreCaseOrderByIdDesc("Frestor Leamon");
        List<Patient> patientBirth = patientRepository.findByBirthDateOrEmail(LocalDate.of(1984, 6, 5), "kima@greggs.com");
        List<Patient> patientBetween = patientRepository.findByBirthDateBetween(LocalDate.of(2000, 3, 3), LocalDate.of(2005, 3, 10));
        List<Patient> patientBloodGroup = patientRepository.findByBloodGroup(BloodGroupType.O_POSITIVE);
        List<Patient> patientFindAll = patientRepository.findAllPatients();

        // findBy.. methods for reference https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
        System.out.println("patientName = " + patientName);
        System.out.println("patientBirth = " + patientBirth);
        System.out.println("patientBetween = " + patientBetween);
        System.out.println("patientContaining = " + patientContaining);
        System.out.println("patientBloodGroup = " + patientBloodGroup);

        List<BloodGroupCountResponseEntity> patientBloodGroupList = patientRepository.countEachBloodGroupType();
        for (BloodGroupCountResponseEntity bloodGroupResponse : patientBloodGroupList) {
            System.out.println(bloodGroupResponse);
        }

        // patientFindAll.forEach(System.out::println);
        for (Patient patients : patientFindAll) {
            System.out.println(patients.getName());
        }

        int rowsUpdated = patientRepository.updateNameById("Jimmy McNulty", 1L);
        System.out.println(rowsUpdated);


    }
}
