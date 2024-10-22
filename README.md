# General Idea
- Every user contains its own records and display its own menu
- As such, there is a need to read / write from the database, which is managed by DBManagers
- Logging in is done through checking the database directly with the help of the DBManagers
  
# What has been implemented already?
1. Loggin in for ALL classes (future proof)
2. Patients can view and also update their own records
3. Doctors can view and update patient information
4. Pharmacist can view inventory
5. Admin can view and update inventory

# What's next?
1. ~Doctors must be able to view and update patient information~
2. ~Admin / Pharmacist~
3. Appointment system
   - Interacted with by Patient, Doctor and Admin
4. Past Appointment Outcome Records
   - Needs a separate database
   - Interacted with by Patient, Doctor and Pharmacist
5. ~Inventory Manager~
   - Allows Admin to update actual DB, same category as other DBManagers
   - Admin approves Pharmacist request, only needs to call DBManager
6. Allow Pharmacist to ask for replenishment, and allow Admin to approve

# Minor fixes
1. all the different (custom) I/O exceptions to be added
2. fix the adding of new diagnosis etc, it is appending into empty list
3. nicer display for the diagnosis etc
4. hashing of passwords
5. allowing first time log in users to change password