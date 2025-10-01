# Advantis Dental Surgeries (ADS) - Functional Requirements Specification

## Project Overview

**Company:** Advantis Dental Surgeries, LLC (ADS)  
**Project:** Web-Based Dental Surgery Management System  
**Document Version:** 1.0  
**Date:** October 2025

---

## Table of Contents

1. [Introduction](#introduction)
2. [System Purpose](#system-purpose)
3. [User Roles](#user-roles)
4. [Functional Requirements](#functional-requirements)
   - [User Management & Authentication](#user-management--authentication)
   - [Dentist Management](#dentist-management)
   - [Patient Management](#patient-management)
   - [Appointment Management](#appointment-management)
   - [Business Rules & Constraints](#business-rules--constraints)
   - [Surgery Location Management](#surgery-location-management)
   - [Billing Management](#billing-management)
   - [Notification System](#notification-system)
---

## Introduction

This document outlines the functional requirements for the Advantis Dental Surgeries web-based management system. The system is designed to streamline operations across ADS's growing network of dental surgeries located in the South West region.

The requirements have been derived from stakeholder interviews and business process analysis, focusing on the core needs of office managers, dentists, and patients.

---

## System Purpose

The ADS Management System serves to:

- Centralize dentist and patient registration
- Facilitate appointment scheduling and management
- Enable self-service portals for dentists and patients
- Enforce business rules and operational constraints
- Track billing and payment status
- Manage multiple surgery locations

---

## User Roles

The system supports three primary user roles:

| Role | Description |
|------|-------------|
| **Office Manager** | Administrative staff responsible for registering dentists, enrolling patients, and booking appointments |
| **Dentist** | Healthcare providers who view their schedules and patient information |
| **Patient** | Service recipients who request appointments and manage their bookings |

---

## Functional Requirements

### User Management & Authentication

| ID | Requirement |
|----|-------------|
| **FR-1** | The system shall allow Office Managers to register and manage user accounts |
| **FR-2** | The system shall allow Dentists to sign in to access their portal |
| **FR-3** | The system shall allow Patients to sign in to access their portal |
| **FR-4** | The system shall maintain unique identification for each Dentist |
| **FR-5** | The system shall maintain unique identification for each Patient |

---

### Dentist Management

| ID | Requirement |
|----|-------------|
| **FR-6** | The system shall allow Office Manager to register new Dentists |
| **FR-7** | The system shall record Dentist information including ID, First Name, Last Name, Contact Phone Number, Email, and Specialization |
| **FR-8** | The system shall allow Dentists to view all their scheduled appointments |
| **FR-9** | The system shall display Patient details for each appointment to the Dentist |

**Data Captured:**
- Dentist ID (unique identifier)
- First Name
- Last Name
- Contact Phone Number
- Email Address
- Specialization (e.g., Orthodontics, Periodontics, General Dentistry)

---

### Patient Management

| ID | Requirement |
|----|-------------|
| **FR-10** | The system shall allow Office Manager to enroll new Patients |
| **FR-11** | The system shall record Patient information including First Name, Last Name, Contact Phone Number, Email, Mailing Address, and Date of Birth |
| **FR-12** | The system shall allow Patients to view their appointment history |
| **FR-13** | The system shall display Dentist information for each appointment to the Patient |

**Data Captured:**
- Patient ID (unique identifier)
- First Name
- Last Name
- Contact Phone Number
- Email Address
- Mailing Address
- Date of Birth

---

### Appointment Management

| ID | Requirement |
|----|-------------|
| **FR-14** | The system shall allow Patients to request appointments via phone (recorded by Office Manager) |
| **FR-15** | The system shall allow Patients to request appointments via online form submission |
| **FR-16** | The system shall allow Office Manager to book appointments upon receiving requests |
| **FR-17** | The system shall record appointment details including date, time, Patient, Dentist, and Surgery location |
| **FR-18** | The system shall send confirmation emails to Patients when appointments are booked |
| **FR-19** | The system shall allow Patients to request appointment cancellation |
| **FR-20** | The system shall allow Patients to request appointment changes/rescheduling |

**Appointment Data:**
- Appointment ID (unique identifier)
- Appointment Date
- Appointment Time
- Associated Patient
- Assigned Dentist
- Surgery Location
- Status (Requested, Confirmed, Completed, Cancelled)

**Appointment Channels:**
1. Phone-in requests (handled by Office Manager)
2. Online web form (self-service)

---

### Business Rules & Constraints

| ID | Requirement |
|----|-------------|
| **FR-21** | The system shall enforce a maximum of 5 appointments per Dentist per week |
| **FR-22** | The system shall prevent Patients with outstanding unpaid bills from requesting new appointments |
| **FR-23** | The system shall validate appointment scheduling to prevent conflicts |

**Business Rule Details:**

**BR-1: Dentist Appointment Limit**
- Maximum: 5 appointments per dentist per calendar week
- Week defined as: Sunday through Saturday
- System must reject/warn when limit would be exceeded

**BR-2: Outstanding Bill Restriction**
- Patients with `hasOutstandingBill = true` cannot request new appointments
- System displays appropriate error message to patient
- Restriction lifted once bill is marked as paid

**BR-3: Appointment Conflict Prevention**
- No double-booking of dentists at same date/time
- No double-booking of patients at same date/time
- Surgery location capacity considerations (future enhancement)

---

### Surgery Location Management

| ID | Requirement |
|----|-------------|
| **FR-24** | The system shall maintain information about Surgery locations |
| **FR-25** | The system shall record Surgery details including name, location address, and telephone number |
| **FR-26** | The system shall associate each appointment with a specific Surgery location |

**Priority:** High  
**Rationale:** Multi-location support is essential for the expanding network of dental surgeries.

**Surgery Data:**
- Surgery ID (unique identifier)
- Name
- Location Address
- Telephone Number

---

### Billing Management

| ID | Requirement |
|----|-------------|
| **FR-27** | The system shall track billing status for dental services |
| **FR-28** | The system shall maintain records of outstanding payments for Patients |
| **FR-29** | The system shall flag Patients with unpaid bills |

**Priority:** High  
**Rationale:** Financial tracking ensures business sustainability and enforces payment policies.

**Billing Data:**
- Bill ID (unique identifier)
- Amount
- Issue Date
- Due Date
- Payment Status (Paid/Unpaid)
- Associated Patient
- Associated Appointment

---

### Notification System

| ID | Requirement |
|----|-------------|
| **FR-30** | The system shall send email notifications for appointment confirmations |
| **FR-31** | The system shall provide communication capabilities between the system and users |

**Priority:** Medium  
**Rationale:** Automated notifications reduce manual communication overhead and improve customer experience.

**Notification Types:**
- Appointment confirmation emails (to Patient)
- Appointment reminder emails (future enhancement)
- Cancellation confirmations (future enhancement)

---


## Assumptions

1. All users have access to email for notifications
2. Office Manager is available during business hours to handle phone requests
3. Internet connectivity is available at all surgery locations
4. Basic computer literacy is assumed for all user types
5. The system will initially support English language only

---

## Dependencies

1. Email service provider for notification delivery
2. Secure hosting infrastructure with SSL/TLS support
3. Database management system for data persistence
4. Web server for application hosting
5. Domain name and DNS configuration






