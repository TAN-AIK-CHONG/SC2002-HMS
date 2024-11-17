# Hospital Management System 🏥

The Hospital Management System is a Command-Line-Interface application built in Java that allows patients, doctors, pharmacists, and administrators to interact with each other and perform tasks that a hospital may require, such as viewing medical records, scheduling appointments, and maintaining a billing system.

## Features

- View and manage medical records
- Schedule and reschedule appointments
- Manage inventory of medications
- Secure authentication for patients and staff
- Role-based access control for administrators, doctors, pharmacists, and patients

## Additional Features

1. **Billing System**
   - Medical records automatically keep track of the total bill for each patient
   - Each Appointment Outcome Record (AOR) includes an appointment bill
   - The total bill for the respective patient is updated dynamically to reflect the charges from all appointments
   - The Patient can pay off outstanding fees.

2. **Secure Password Storage**
   - Passwords are hashed securely with Bcrypt

3. **Patient Management**
   - Administrators given ability to view, add and remove patients
   - Administrators can also reset passwords for patients
     
## Libraries Used

### jBCrypt

We used **jBCrypt** for secure password hashing and verification in our application. jBCrypt provides an implementation of the BCrypt hashing algorithm in Java, enhancing security by incorporating a salt and a computational cost factor to protect against brute-force attacks.

#### Installation

Since we didn't use Maven or any build automation tools, jBCrypt was added manually to the project.

**Download the Library**

- Download the `jbcrypt-0.4.jar` file from the official repository:
  - [Download jBCrypt 0.4](https://repo1.maven.org/maven2/org/mindrot/jbcrypt/0.4/jbcrypt-0.4.jar)

## Set-up Instructions

### Prerequisites

- **Java Development Kit (JDK) 8 or higher**: Ensure that the JDK is installed on your system.
  - [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- **Git**: For cloning the repository.
  - [Download Git](https://git-scm.com/downloads)

### Installation

1. **Clone the Repository**

   Clone the repository from GitHub to your local machine using the following command:

   ```bash
   git clone https://github.com/TAN-AIK-CHONG/SC2002-HMS
   ```
   
2. **Navigate to Build Directory**
   
   ```bash
   cd out\production\SC2002-HMS
   ```

3. **Run the Main class**

   ```bash
   java HMSApp
   ```
   
