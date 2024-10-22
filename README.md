# General Idea
- Every user contains its own records and display its own menu
- As such, there is a need to read / write from the database, which is managed by DBManagers
- Logging in is done through checking the database directly with the help of the DBManagers
  
# What has been implemented already?
1. Loggin in for ALL classes (future proof)
2. Patients can view and also update their own records

# What's next?
1. Doctors must be able to view and update patient information
2. Admin / Pharmacist
3. Appointment system
   - Interacted with by Patient, Doctor and Admin
4. Past Appointment Outcome Records
   - Needs a separate database
   - Interacted with by Patient, Doctor and Pharmacist
5. Inventory Manager
   - Allows Pharmacist to update actual DB, same category as other DBManagers
   - Admin approves Pharmacist request, but does not need to know about the DBManager

