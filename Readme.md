# Smart IV

<img src = "/images/logo_round.png" height = "200" width="200" align="left" hspace="10" vspace="10">

An IoT based automatic alerting device that consists of laser and a precise Light Dependent Resistor to detect the laser which is employed to constantly monitor the fluid level. When the fluid level is below the critical level which will be defined by laser, it will alert the patient through buzzer, nurses and doctors through mobile app and the central control room using web app.Smart-IV is a two-tier application with Android App focussing on node selection while web server handles all the records. The application allows the entry of a new patient, viewing profile facilities and deleting a node assignment to a patient.The application's objective is to form a helping hand to the healthcare workers to monitor the drip system connected to several patients.

**Hardware Components Used**

1.NodeMCU

2.Light dependent resistor(LDR)

3.Laser

4.Buzzer

5.Power supply

**Implementation:**

•	Using IoT, we can solve the problem of 'empty bottles’. We’re using LDR sensor which is connected to NodeMcu.

•	LDR will help us detect if the fluid is below a critical level.

•	The NodeMCU will then send their data to the firebase.

•	Buzzer will even notify the patient about the fluid level.

•	The data from the firebase will then be accessed by a mobile application.

•	The nurse and doctors will be notified about the fluid level using mobile and web application.

<img src = "/images/smartiv-git.PNG" height = "200" width="800" align="center" hspace="10" vspace="10"> 

The cost of the hardware product is estimated to be not more than Rs. 400 which makes it highly economical to be employed in the hospitals.

**Working of mobile and web application**

With the new entry of a patient is created using web application, a record is generated with a unique patientID which is stored in a firebase-database which will help hospitals in managing records in more manageable manner. Similarly, whenever a "drip entry" is created using mobile apllication, the corresponding data is stored in another database. The creation of drip entry assigns a node to the patient with the highest priority and we are good to go. And both the application consists of monitoring drip status and delete entry options too. Monitoring drip status will allow hospital staff to keep track of the IV bottles connected to the patients. Delete entry option will allow them to delete details of the patients from the hardware node after the treatment is over.

**Screenshots of mobile application**

**Screenshots of web application**



**Technology Stack**

 - Internet of Things
 - Embedded c
 - Firebase
 - Kotlin
 - XML
 - Firebase Authentication
 - Firebase Real Time Database
 - Kotlin Coroutines
 - Material Design
 - HTML,CSS
 - Javascript
 - AWS Server - Apache 2

**Dependencies**

1.Firebase

2.Internet connection

**Progress**

At the moment, creating an entry and check for assistance on the website have been successfully deployed while deleting an entry is in progress. In the Android application, the addition of a drip entry has been deployed while check for assistance and deleting drip entry are in progress.

As the dire need of drip system is evident in most medical facilities, the usage of Smart-IV is limitless.


