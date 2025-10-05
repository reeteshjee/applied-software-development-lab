# Advantis Dental Surgeries (ADS) - Database Design & Implementation
## Lab Assignment - Software Engineering

---

## Table of Contents
1. [Overview](#overview)
2. [Entity-Relationship Model](#entity-relationship-model)
3. [Database Schema](#database-schema)
4. [Installation Instructions](#installation-instructions)
5. [SQL Queries & Results](#sql-queries--results)
6. [Business Rules Implementation](#business-rules-implementation)
7. [Testing Instructions](#testing-instructions)

---

## Overview

This document contains the complete database design and implementation for the Advantis Dental Surgeries (ADS) web application system. The system manages dental surgery operations including:

- Dentist registration and management
- Patient enrollment and records
- Appointment scheduling and tracking
- Surgery location management
- Billing system

---

## Entity-Relationship Model

### Entities

1. **dentists**
   - Stores information about dentists in the ADS network
   - Primary Key: `dentist_id`

2. **patients**
   - Stores patient information and demographics
   - Primary Key: `patient_id`

3. **surgeries**
   - Stores dental surgery location information
   - Primary Key: `surgery_id`

4. **appointments**
   - Links dentists, patients, and surgery locations
   - Primary Key: `appointment_id`
   - Foreign Keys: `dentist_id`, `patient_id`, `surgery_id`

5. **bills**
   - Tracks patient billing and payment status
   - Primary Key: `bill_id`
   - Foreign Key: `patient_id`

### Relationships

- **dentists → appointments**: One-to-Many (One dentist has many appointments)
- **patients → appointments**: One-to-Many (One patient has many appointments)
- **surgeries → appointments**: One-to-Many (One surgery hosts many appointments)
- **patients → bills**: One-to-Many (One patient can have many bills)

### ER Diagram Description

<img src="ER Diagram.png" alt="Diagram" width="500"/>


---

## Database Schema

### Table: dentists
```sql
CREATE TABLE dentists (
    dentist_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    specialization VARCHAR(100) NOT NULL
);
```

### Table: patients
```sql
CREATE TABLE patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    mailing_address VARCHAR(200) NOT NULL,
    date_of_birth DATE NOT NULL
);
```

### Table: surgeries
```sql
CREATE TABLE surgeries (
    surgery_id INT PRIMARY KEY AUTO_INCREMENT,
    surgery_name VARCHAR(100) NOT NULL,
    location_address VARCHAR(200) NOT NULL,
    telephone_number VARCHAR(15) NOT NULL
);
```

### Table: bills
```sql
CREATE TABLE bills (
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_status ENUM('PAID', 'UNPAID') DEFAULT 'UNPAID',
    bill_date DATE NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE
);
```

### Table: appointments
```sql
CREATE TABLE appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    dentist_id INT NOT NULL,
    patient_id INT NOT NULL,
    surgery_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    status ENUM('SCHEDULED', 'COMPLETED', 'CANCELLED') DEFAULT 'SCHEDULED',
    FOREIGN KEY (dentist_id) REFERENCES dentists(dentist_id) ON DELETE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (surgery_id) REFERENCES surgeries(surgery_id) ON DELETE CASCADE
);
```

---

## Installation Instructions

### Prerequisites
- MySQL Server (version 5.7 or higher) or MariaDB
- MySQL Workbench, phpMyAdmin, or command-line access

### Setup Steps

1. **Download the SQL Script**
   - File: `myADSDentalSurgeryDBScript.sql`

2. **Option A: Command Line Installation**
   ```bash
   mysql -u root -p < myADSDentalSurgeryDBScript.sql
   ```

3. **Option B: MySQL Workbench**
   - Open MySQL Workbench
   - File → Open SQL Script
   - Select `myADSDentalSurgeryDBScript.sql`
   - Execute the script (Lightning bolt icon)

4. **Option C: phpMyAdmin**
   - Login to phpMyAdmin
   - Click "Import" tab
   - Choose file: `myADSDentalSurgeryDBScript.sql`
   - Click "Go"

5. **Verify Installation**
   ```sql
   USE ads_dental_surgery;
   SHOW TABLES;
   ```

---

## SQL Queries & Results

### Query 1: Display All Dentists (Sorted by Last Name)

**Purpose**: List all registered dentists in ascending order of their last names.

**SQL Code**:
```sql
SELECT 
    dentist_id,
    first_name,
    last_name,
    phone_number,
    email,
    specialization
FROM dentists
ORDER BY last_name ASC;
```

**Expected Results**:
| dentist_id | first_name | last_name | phone_number  | email                | specialization        |
|------------|-----------|-----------|---------------|----------------------|-----------------------|
| 1          | John      | Anderson  | 641-555-0101  | j.anderson@ads.com   | General Dentistry     |
| 2          | Sarah     | Brooks    | 641-555-0102  | s.brooks@ads.com     | Orthodontics          |
| 3          | Michael   | Chen      | 641-555-0103  | m.chen@ads.com       | Pediatric Dentistry   |
| 4          | Emily     | Davis     | 641-555-0104  | e.davis@ads.com      | Endodontics           |
| 5          | Robert    | Williams  | 641-555-0105  | r.williams@ads.com   | Periodontics          |

**Screenshot Instruction**: Execute this query and capture the output showing all 5 dentists sorted alphabetically.

---

### Query 2: Appointments for a Given Dentist with Patient Information

**Purpose**: Display all appointments for a specific dentist including patient details.

**SQL Code** (Example: dentist_id = 1):
```sql
SELECT 
    a.appointment_id,
    a.appointment_date,
    a.appointment_time,
    a.status,
    d.dentist_id,
    d.first_name AS dentist_first_name,
    d.last_name AS dentist_last_name,
    d.specialization,
    p.patient_id,
    p.first_name AS patient_first_name,
    p.last_name AS patient_last_name,
    p.phone_number AS patient_phone,
    p.email AS patient_email,
    s.surgery_name,
    s.location_address
FROM appointments a
INNER JOIN dentists d ON a.dentist_id = d.dentist_id
INNER JOIN patients p ON a.patient_id = p.patient_id
INNER JOIN surgeries s ON a.surgery_id = s.surgery_id
WHERE a.dentist_id = 1
ORDER BY a.appointment_date, a.appointment_time;
```

**Expected Results** (for Dr. Anderson - dentist_id = 1):
| appointment_id | appointment_date | appointment_time | status     | dentist_name    | patient_name    | patient_phone | surgery_name          |
|----------------|------------------|------------------|------------|-----------------|-----------------|---------------|-----------------------|
| 16             | 2025-09-15       | 09:00:00         | COMPLETED  | John Anderson   | Emma Garcia     | 641-555-1005  | ADS Fairfield Clinic  |
| 1              | 2025-10-10       | 09:00:00         | SCHEDULED  | John Anderson   | Alice Johnson   | 641-555-1001  | ADS Fairfield Clinic  |
| 2              | 2025-10-10       | 10:30:00         | SCHEDULED  | John Anderson   | Bob Smith       | 641-555-1002  | ADS Fairfield Clinic  |
| 3              | 2025-10-11       | 14:00:00         | SCHEDULED  | John Anderson   | Carol Martinez  | 641-555-1003  | ADS Ottumwa Center    |
| 4              | 2025-10-12       | 11:00:00         | SCHEDULED  | John Anderson   | David Brown     | 641-555-1004  | ADS Fairfield Clinic  |

**Screenshot Instruction**: Execute with dentist_id = 1 and capture results showing all appointments with patient information.

**Note**: You can test with different dentist_id values (1-5) to see other dentists' appointments.

---

### Query 3: All Appointments at a Specific Surgery Location

**Purpose**: Display all appointments scheduled at a particular surgery location.

**SQL Code** (Example: surgery_id = 1 - ADS Fairfield Clinic):
```sql
SELECT 
    a.appointment_id,
    a.appointment_date,
    a.appointment_time,
    a.status,
    s.surgery_id,
    s.surgery_name,
    s.location_address,
    s.telephone_number,
    d.first_name AS dentist_first_name,
    d.last_name AS dentist_last_name,
    d.specialization,
    p.first_name AS patient_first_name,
    p.last_name AS patient_last_name,
    p.phone_number AS patient_phone
FROM appointments a
INNER JOIN surgeries s ON a.surgery_id = s.surgery_id
INNER JOIN dentists d ON a.dentist_id = d.dentist_id
INNER JOIN patients p ON a.patient_id = p.patient_id
WHERE a.surgery_id = 1
ORDER BY a.appointment_date, a.appointment_time;
```

**Expected Results** (for ADS Fairfield Clinic - surgery_id = 1):
| appointment_id | date       | time     | surgery_name         | dentist_name    | patient_name  | status     |
|----------------|------------|----------|----------------------|-----------------|---------------|------------|
| 18             | 2025-09-22 | 10:00:00 | ADS Fairfield Clinic | Michael Chen    | Bob Smith     | COMPLETED  |
| 1              | 2025-10-10 | 09:00:00 | ADS Fairfield Clinic | John Anderson   | Alice Johnson | SCHEDULED  |
| 2              | 2025-10-10 | 10:30:00 | ADS Fairfield Clinic | John Anderson   | Bob Smith     | SCHEDULED  |
| 5              | 2025-10-10 | 13:00:00 | ADS Fairfield Clinic | Sarah Brooks    | Emma Garcia   | SCHEDULED  |
| 4              | 2025-10-12 | 11:00:00 | ADS Fairfield Clinic | John Anderson   | David Brown   | SCHEDULED  |

**Screenshot Instruction**: Execute with surgery_id = 1 and capture the complete result set.

**Note**: Test with surgery_id values 1, 2, or 3 to see appointments at different locations.

---

### Query 4: Appointments for a Given Patient on a Given Date

**Purpose**: Display all appointments booked for a specific patient on a specific date.

**SQL Code** (Example: patient_id = 1, date = '2025-10-10'):
```sql
SELECT 
    a.appointment_id,
    a.appointment_date,
    a.appointment_time,
    a.status,
    p.patient_id,
    p.first_name AS patient_first_name,
    p.last_name AS patient_last_name,
    p.email AS patient_email,
    d.dentist_id,
    d.first_name AS dentist_first_name,
    d.last_name AS dentist_last_name,
    d.specialization,
    d.phone_number AS dentist_phone,
    s.surgery_name,
    s.location_address,
    s.telephone_number AS surgery_phone
FROM appointments a
INNER JOIN patients p ON a.patient_id = p.patient_id
INNER JOIN dentists d ON a.dentist_id = d.dentist_id
INNER JOIN surgeries s ON a.surgery_id = s.surgery_id
WHERE a.patient_id = 1 
  AND a.appointment_date = '2025-10-10'
ORDER BY a.appointment_time;
```

**Expected Results** (for Alice Johnson on 2025-10-10):
| appointment_id | date       | time     | status    | patient_name  | dentist_name  | specialization    | surgery_name         | location                        |
|----------------|------------|----------|-----------|---------------|---------------|-------------------|----------------------|---------------------------------|
| 1              | 2025-10-10 | 09:00:00 | SCHEDULED | Alice Johnson | John Anderson | General Dentistry | ADS Fairfield Clinic | 1000 N 4th St, Fairfield, IA    |

**Screenshot Instruction**: Execute with patient_id = 1 and date = '2025-10-10', capture the result showing patient and dentist details.

**Note**: Test with different combinations:
- patient_id = 2, date = '2025-10-13'
- patient_id = 3, date = '2025-10-11'

---

## Business Rules Implementation

### 1. Maximum 5 Appointments Per Week per Dentist

**Validation Query**:
```sql
SELECT 
    d.dentist_id,
    d.first_name,
    d.last_name,
    YEARWEEK(a.appointment_date) AS week_number,
    COUNT(*) AS appointments_in_week
FROM dentists d
INNER JOIN appointments a ON d.dentist_id = a.dentist_id
WHERE a.status = 'SCHEDULED'
GROUP BY d.dentist_id, d.first_name, d.last_name, YEARWEEK(a.appointment_date)
HAVING COUNT(*) > 5;
```

**Implementation**: The system should check this before booking new appointments.

---

### 2. Prevent Appointments for Patients with Unpaid Bills

**Validation Query**:
```sql
SELECT 
    p.patient_id,
    p.first_name,
    p.last_name,
    p.email,
    COUNT(b.bill_id) AS unpaid_bills_count,
    SUM(b.amount) AS total_unpaid_amount
FROM patients p
INNER JOIN bills b ON p.patient_id = b.patient_id
WHERE b.payment_status = 'UNPAID'
GROUP BY p.patient_id, p.first_name, p.last_name, p.email;
```

**Expected Results**:
| patient_id | first_name | last_name | email              | unpaid_bills_count | total_unpaid_amount |
|------------|------------|-----------|--------------------|--------------------|---------------------|
| 2          | Bob        | Smith     | bob.smith@email.com| 1                  | 450.00              |
| 5          | Emma       | Garcia    | emma.g@email.com   | 1                  | 500.00              |

**Implementation**: Check this before allowing new appointment bookings.

---

## Testing Instructions

### 1. Verify Data Population
```sql
-- Check record counts
SELECT 'dentists' AS table_name, COUNT(*) AS record_count FROM dentists
UNION ALL
SELECT 'patients', COUNT(*) FROM patients
UNION ALL
SELECT 'surgeries', COUNT(*) FROM surgeries
UNION ALL
SELECT 'appointments', COUNT(*) FROM appointments
UNION ALL
SELECT 'bills', COUNT(*) FROM bills;
```

**Expected Output**:
| table_name   | record_count |
|--------------|--------------|
| dentists     | 5            |
| patients     | 7            |
| surgeries    | 3            |
| appointments | 18           |
| bills        | 5            |

---

### 2. View All Appointments with Complete Details
```sql
SELECT 
    a.appointment_id,
    a.appointment_date,
    a.appointment_time,
    a.status,
    CONCAT(d.first_name, ' ', d.last_name) AS dentist_name,
    d.specialization,
    CONCAT(p.first_name, ' ', p.last_name) AS patient_name,
    p.phone_number AS patient_phone,
    s.surgery_name,
    s.location_address
FROM appointments a
INNER JOIN dentists d ON a.dentist_id = d.dentist_id
INNER JOIN patients p ON a.patient_id = p.patient_id
INNER JOIN surgeries s ON a.surgery_id = s.surgery_id
ORDER BY a.appointment_date, a.appointment_time;
```

---

### 3. Check Referential Integrity
```sql
-- Verify all foreign keys are valid
SELECT 'Invalid dentist_id in appointments' AS issue, COUNT(*) AS count
FROM appointments a
LEFT JOIN dentists d ON a.dentist_id = d.dentist_id
WHERE d.dentist_id IS NULL

UNION ALL

SELECT 'Invalid patient_id in appointments', COUNT(*)
FROM appointments a
LEFT JOIN patients p ON a.patient_id = p.patient_id
WHERE p.patient_id IS NULL

UNION ALL

SELECT 'Invalid surgery_id in appointments', COUNT(*)
FROM appointments a
LEFT JOIN surgeries s ON a.surgery_id = s.surgery_id
WHERE s.surgery_id IS NULL;
```

**Expected**: All counts should be 0 (no integrity violations).

---

## Additional Features

### Useful Queries for System Functionality

**1. Today's Appointments**:
```sql
SELECT 
    a.appointment_time,
    CONCAT(d.first_name, ' ', d.last_name) AS dentist,
    CONCAT(p.first_name, ' ', p.last_name) AS patient,
    s.surgery_name
FROM appointments a
JOIN dentists d ON a.dentist_id = d.dentist_id
JOIN patients p ON a.patient_id = p.patient_id
JOIN surgeries s ON a.surgery_id = s.surgery_id
WHERE a.appointment_date = CURDATE()
  AND a.status = 'SCHEDULED'
ORDER BY a.appointment_time;
```

**2. Upcoming Appointments for a Patient**:
```sql
SELECT 
    a.appointment_date,
    a.appointment_time,
    CONCAT(d.first_name, ' ', d.last_name) AS dentist,
    d.specialization,
    s.surgery_name,
    s.location_address
FROM appointments a
JOIN dentists d ON a.dentist_id = d.dentist_id
JOIN surgeries s ON a.surgery_id = s.surgery_id
WHERE a.patient_id = 1
  AND a.appointment_date >= CURDATE()
  AND a.status = 'SCHEDULED'
ORDER BY a.appointment_date, a.appointment_time;
```

**3. Dentist Availability Check**:
```sql
SELECT 
    d.dentist_id,
    CONCAT(d.first_name, ' ', d.last_name) AS dentist,
    d.specialization,
    COUNT(a.appointment_id) AS scheduled_appointments
FROM dentists d
LEFT JOIN appointments a ON d.dentist_id = a.dentist_id 
    AND a.appointment_date = '2025-10-10'
    AND a.status = 'SCHEDULED'
GROUP BY d.dentist_id, d.first_name, d.last_name, d.specialization
HAVING scheduled_appointments < 5;
```

---

## File Deliverables

1. **myADSDentalSurgeryDBScript.sql** - Complete SQL script with:
   - Database creation
   - Table definitions
   - Dummy data insertion
   - All required queries

2. **README.md** - This documentation file

3. **Screenshots** (to be taken by student):
   - Query1_AllDentists.png
   - Query2_DentistAppointments.png
   - Query3_SurgeryAppointments.png
   - Query4_PatientDateAppointments.png

4. **ER Diagram** (image file):
   - ER Diagram.png or .jpg

---

## Database Naming Conventions Used

- **Table names**: lowercase, plural (dentists, patients, appointments)
- **Column names**: lowercase with underscores (first_name, appointment_date)
- **Primary keys**: table_name_singular + _id (dentist_id, patient_id)
- **Foreign keys**: referenced_table_singular + _id
- **Indexes**: Automatically created on primary keys and foreign keys

These conventions follow industry best practices for database design and make the code more maintainable.

---

## Notes

- All dates in the dummy data are set for October 2025
- The system uses ENUM types for status fields to ensure data integrity
- Foreign key constraints ensure referential integrity
- ON DELETE CASCADE is used to maintain data consistency
- Email addresses must be unique for both dentists and patients

---

## Contact & Support

For questions about this implementation, please contact:
- Course: CS489 - Applied Software Development
- Institution: MIU - Department of Computer Science

---

**End of Documentation**