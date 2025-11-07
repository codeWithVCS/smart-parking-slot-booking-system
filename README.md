# Smart Parking Slot Booking System (Spring Core Capstone)

## 📘 Overview

The **Smart Parking Slot Booking System** is a fully functional, console-driven application developed entirely using **Spring Core** concepts. It simulates a real-world parking management platform where users can book slots, view existing bookings, and cancel reservations, while admins manage slots and oversee bookings.

This project serves as the **Capstone Project for the Spring Core module** of the Spring Boot Mastery Learning Plan. It demonstrates deep understanding and practical mastery of **Dependency Injection (IoC)**, **Bean Lifecycle Management**, **Spring Profiles**, and **Layered Architecture** — all without involving Spring MVC or web layers.

---

## 🎯 Project Objectives

* To design a realistic, interactive parking slot management system.
* To apply **Spring Core** features (IoC, @Component, @Autowired, @Profile, @PostConstruct, @PreDestroy).
* To build a modular, testable, and layered architecture with **service**, **repository**, and **console** interaction layers.
* To simulate production-ready behavior using **Spring Profiles** (`dev` and `prod`).

---

## 🧩 Architecture Overview

### Layers and Responsibilities

| Layer             | Package                                                | Responsibility                                                                                            |
| ----------------- | ------------------------------------------------------ | --------------------------------------------------------------------------------------------------------- |
| **Model**         | `org.chandra.smartparkingslotbookingsystem.model`      | Defines entities like `ParkingSlot`, `CarParkingSlot`, `BikeParkingSlot`, `Booking`, `Admin`, and `User`. |
| **Repository**    | `org.chandra.smartparkingslotbookingsystem.repository` | In-memory data storage and management. Different profiles (`dev`, `prod`) simulate different datasets.    |
| **Service**       | `org.chandra.smartparkingslotbookingsystem.service`    | Encapsulates core business logic for slots, bookings, and admin operations.                               |
| **Console**       | `org.chandra.smartparkingslotbookingsystem.console`    | Handles user interaction (menu-driven) and orchestrates service calls.                                    |
| **Configuration** | `src/main/resources`                                   | Profile-specific property files (`application-dev.properties`, `application-prod.properties`).            |

---

## 🧱 Key Spring Core Concepts Demonstrated

| Concept                                   | Implementation Example                                                                                                 |
| ----------------------------------------- | ---------------------------------------------------------------------------------------------------------------------- |
| **Inversion of Control (IoC)**            | All components (`Service`, `Repository`, `ConsoleHandler`) are Spring-managed beans injected via constructor-based DI. |
| **@Component and @Service**               | Used to register console handlers and services automatically in the application context.                               |
| **@Repository and @Profile**              | `InMemorySlotRepository` (dev) and `ProdSlotRepository` (prod) demonstrate profile-based bean loading.                 |
| **Bean Lifecycle Hooks**                  | `@PostConstruct` and `@PreDestroy` used in repositories for data initialization and cleanup.                           |
| **Layered Architecture**                  | Clean separation between console layer, service layer, and repository layer.                                           |
| **Profiles and Configuration Management** | Demonstrates realistic environment separation (`dev` vs. `prod`).                                                      |

---

## ⚙️ Features

### 👤 **User Features**

* View available parking slots (Car or Bike)
* Book a slot with name, email, phone number, and vehicle number
* Specify custom booking start and end time (with separate date & time prompts)
* View all personal bookings by phone number
* Cancel bookings

### 🧑‍💼 **Admin Features**

* View all parking slots
* Add new slots (Car or Bike)
* Update slot details (rate and availability)
* Delete slots
* View all bookings
* Force cancel a booking

---

## 🧰 Tech Stack

* **Java 21**
* **Spring Framework (Core Context)**
* **Maven** (for dependency management)
* **IntelliJ IDEA** (development environment)

No databases or web frameworks (Spring MVC) are used — all data is managed via **in-memory repositories**.

---

## 🧪 Profiles & Configuration

Two profiles are included:

| Profile | Repository               | Description                                                          |
| ------- | ------------------------ | -------------------------------------------------------------------- |
| `dev`   | `InMemorySlotRepository` | Contains small, sample dataset for development and testing.          |
| `prod`  | `ProdSlotRepository`     | Simulates larger dataset with realistic pricing and slot allocation. |

To switch profiles, edit `application.properties`:

```properties
spring.profiles.active=dev
```

Change `dev` to `prod` for production simulation.

---

## 🚀 How to Run the Application

### Step 1. Clone the Repository

```bash
git clone https://github.com/<your-username>/smart-parking-slot-booking-system.git
cd smart-parking-slot-booking-system
```

### Step 2. Build the Project

```bash
mvn clean install
```

### Step 3. Run the Application

```bash
mvn spring-boot:run
```

### Step 4. Interact via Console

You’ll be presented with a main menu:

```
SMART PARKING SLOT BOOKING SYSTEM

Select mode:
1. User Mode
2. Admin Mode
3. Exit
```

Follow on-screen instructions to perform actions as a User or Admin.

---

## 🧠 Example Console Flow

### 🧍‍♂️ User Booking Example

```
Select mode: 1 (User Mode)

===== USER MENU =====
1. View Available Slots
2. Book a Slot
...

Enter slot type (Car/Bike): Car
✅ Available Slots:
[C-101] Type: Car | Location: A1 | Rate: 50/hr | Available: Yes

Enter your name: Chandra
Enter your email: chandra@email.com
Enter your phone number: 9876543210
Enter your vehicle registration number: AP09AB1234
Enter slot ID to book: C-101
Enter booking start date (dd-MM-yyyy): 07-11-2025
Enter booking start time (HH:mm): 09:00
Enter booking end date (dd-MM-yyyy): 07-11-2025
Enter booking end time (HH:mm): 12:00
✅ Booking created successfully for Slot C-101
```

---

## 🧭 Learning Outcomes

* Deep understanding of **Spring Core** fundamentals.
* Practical mastery of **Dependency Injection** and **Profiles**.
* Ability to build layered, maintainable, and extensible applications.
* Confidence to transition from Spring Core to **Spring MVC / REST API** architecture next.

---

## 👨‍💻 Author

**Chandra Sekhar**
Java Backend Developer — passionate about Spring, clean architecture, and backend craftsmanship.

---
