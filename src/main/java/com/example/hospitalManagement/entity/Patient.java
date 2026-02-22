package com.example.hospitalManagement.entity;

import com.example.hospitalManagement.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Table(
        name = "patient",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_patient_email", columnNames = {
                        "email"
                }),
                @UniqueConstraint(name = "unique_patient_name_birth_date", columnNames = {
                        "name", "birthDate"
                })
        },
        indexes = {
                @Index(name = "idx_patient_birthdate", columnList = "birthDate")
        }

)
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    //    @ToString.Exclude
    private LocalDate birthDate;

    @Column(unique = true, nullable = false)
    private String email;

    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroupType blood_group;

    @OneToOne
    @JoinColumn(name = "insurance_id") // inverse side
    private Insurance insurance;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointmentList;


}
