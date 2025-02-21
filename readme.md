# Insurance Claims Management System (Portfolio Project)

## Overview
This repository is a **fork of the original COSC2440 Group Assignment 2**, now repurposed as a **portfolio project** to showcase our technical skills in **Spring Boot, Hibernate, JPA, Lombok, and Angular**. It retains the same contributors and aims to **demonstrate expertise in full-stack development** while refining and expanding upon the original system.

The **Insurance Claims Management System** provides a robust and user-friendly platform for managing, tracking, and processing insurance claims. With a structured **role-based access control system**, it ensures efficient claim handling for different user roles, including **policyholders, insurance managers, and surveyors**.

---

## 🔧 Tech Stack
- **Backend:** Spring Boot, Hibernate, JPA, Lombok
- **Frontend:** Angular
- **Database:** PostgreSQL
- **Testing:** JUnit 5
- **Build Tools:** Maven
- **Version Control:** Git, GitHub

---

## 📌 Features

### **Authentication & Authorization**
- Secure login system with role-based access control.
- Password encryption and user authentication.

### **Claim Management**
- File, update, and retrieve insurance claims.
- Upload claim documents (images) with proper validation and renaming.
- Approval/rejection process for claims.

### **User Management**
- CRUD operations for users (System Admin only).
- Policy Owner functionalities: managing beneficiaries, calculating yearly insurance costs.
- Insurance Surveyor functionalities: requesting additional claim details, filtering claims.
- Insurance Manager functionalities: approving/rejecting claims, managing surveyors.

### **System Administration**
- Manage users, roles, and permissions.
- Generate reports on successfully claimed amounts with filtering options.

---

## 🛠 Setup & Installation

### **Prerequisites**
- Java 17+
- Node.js & Angular CLI (for frontend)
- PostgreSQL database
- Maven

### **Backend Setup**
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/insurance-claims-portfolio.git
   cd insurance-claims-portfolio/backend
   ```
2. Configure PostgreSQL database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/insurance_db
   spring.datasource.username=your_db_user
   spring.datasource.password=your_db_password
   ```
3. Build and run the Spring Boot application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### **Frontend Setup**
1. Navigate to the frontend directory:
   ```bash
   cd ../frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the Angular app:
   ```bash
   ng serve
   ```

---

## 👥 Contributors
- **Giang Trong Duong**
- **Min Chi Gia Khiem**
- **Nguyen A Luy**
- **Nguyen Huynh Quang**

This project builds upon our previous work in **Group Assignment 2** and enhances it for personal portfolio use.

---

## 📝 License
This project is for **educational and portfolio purposes**. It is not intended for commercial use.

---

## 📩 Contact
For any inquiries or collaboration opportunities, feel free to reach out via GitHub or LinkedIn.

