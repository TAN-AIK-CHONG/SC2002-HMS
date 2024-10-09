# What's implemented already?

1. Log in system has been implemented. It will work even as we add future staff classes (currently admin and pharmacist not implemented).
2. Patient has been implemented partially. The patient is able to view their medical records and also update their info. The csv file will be appropriately updated.
3. Doctors currently can only view patient records, and I have no implemented updating their records. Will do that soon.

# What's immediately next?
1. I will finish implementing writing of records for Doctors. Afterward all read/write to patient database would be completed. Admins can also view in the future.
2. I will write exception classes too because currently I have not been checking for user input error.
3. Implement admin partially and allow them to view records.

# Big Picture
1. Need to implement the appointment system. quite complicated, explore java.time for dates and time slots logic.
2. Need to implement inventory manager as well, which Pharmacist and Admins interact with.
3. Appointment Outcome Records should also be implemented. I suggest a separate csv file for this? Accessed by Patients, Doctors and Pharmacists.

## Contributing
Remember to create a separate branch and commit to that branch instead of committing to main branch directly. Just discuss with the team what parts we are all working on etc so we don't overlap!
