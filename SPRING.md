# Entity -> database tables

    - Patient
    - Doctor
    - Appointment
    - Department

    - DoctorDepartment
    - type/BloodGroupType

Entity define the structure of the data which our application manages

# Repository -> data access layer

    - PatientRepository

This extends JpaRepository. it acts as the bridge between Java and the database providing built-in methods like save(),
findById(), and delete()

# Service -> business logic layer

    - PatientService

This is where heavy lifting happens, instead of putting complex logic in a controller - we put it here.
For eg:- calculating patient age or checking insurance validity before saving an appointment would happen in this layer.

# DTO -> data transfer layer

    - BloodGroupCountResponsibility

This class is specifically designed to send a structured response to the user (via)
