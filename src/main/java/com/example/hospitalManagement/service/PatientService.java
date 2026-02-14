package com.example.hospitalManagement.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public Patient getPatient(Long id) {

        Patient p1 = patientRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Patient ID not found"));
        p1.setName("Bob");
        return p1;
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

}
