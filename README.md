# PHTRS
Pothole Tracking and Repair System for Curriculum Design using Jave Servlet etc.

Part 1.  Requirement
Exercise 1.1 Indentify requirements from different viewpoints such as citizens, repair crew, PHTRS system’s administrator. List the requirements in the form of items.
Citizen 
In the viewpoint of the citizen, the PHTRS system should provide an easily understanding and simple report interface. And to reduce the citizen’s time and standardize the reported attributes, the PHTRS system should provide optional choices for those attribute with limited situations such as size, street address, location, and district. If the system needs to collect the reporter’s information, it must not leak out the information to any third party. The system should provide the brief status of pothole reported by the citizen when he/she login the system again and display some feedback information (like a thanks from the government department).
Repair crew
When a pothole is assigned to a repair crew, the work order must contain exact location of the pothole and what would the pothole’s condition be. Maybe the work order should give an advice for what equipments should be taken and how much. Or maybe the equipments should be packed for different types of potholes for their conditions. The work order should only need to specify which package to bring and what the extra equipments are, such as, standard package with two more filler material units. When the repair crew finishes repairing the pothole assigned, there should be a simplified reporting form along with the work order for the repair crew to fill in. The form should include hours applied to repair, pothole status, and amount of filler material used.
PHTRS system’s administrator
The administrator should be able to find all the potholes with a certain pothole status. When the administrator assigns a pothole for the repair crew, he/she can print a work order easily from the pothole record for the repair crew and the data needed to be updated for that pothole record should be at the front of the page. And when the repair crew hands in the reporting form, the administrator should be able to find the pothole record by the pothole record ID in the work order and update it.

 
PHTRS System
The system must have some security strategy to protect the publics’ private information from vicious hacking and do have backup plans for system corruption and error. And the service should be provided 24 hours a day.

Exercise 1.2 Draw a use case diagram for the PHTRS system to show the PHTRS system’s functions and how it interacts with external users 

















Part 2.  Design
Exercise 2.1 Draw a class diagram for the PHTRS system 






















Exercise 2.2 Draw a sequence diagram for the PHTRS system 





















 
Part 3.  Development
Exercise 3.1 Implement the PHTRS system
MVC model:

View contains 4 jsp pages and 2 jspf pages.

Control contains 4 classes. But only 3 of handling the request from users while the dispatcher only maps the “/phtrs” url to the login page.

Model contains 7 classes. 4 of them are generated from the database table. The model.manager package contains the 2 classes which provide access interfaces for the control and view. The CL class initializes the AccountManager instance in the servlet context scope.

The following diagram shows how the data flow among model, view, and control. Arrow means one way direction, while no arrow means two way direction.









Login Page:
There will be a hint message when you input an invalid account in the login page like.








Report Page:
When you report successfully in the report page, the status message will change to the following content.










Admin Page:
In the admin page, you can find the record list sorted by the record status from the url link (                        , UR: not repaired, WIP: work in progress, RE: repaired, TR: temporary repaired) in the page easily. And you can also search the record you want by the timestamp of that record in the search form (                                      ). Besides the search form also can support fuzzy searching. In the list found, you can click the timestamp of that record to look into the detail of that record and update that record.

























Search 2013 in the search form and the result list is shown below.


















Record 
detail













Part 4.  Inspection
Exercise 4.1 Inspect your program to find faults.
Fault type	Fault description
Data Fault	Mapping the CAccountPK into the long class CAccount in reporthandler.
Data Fault	Session scope variable set in adminhandler but never used.
Data Fault	The loginhandler, reporthandler and adminhandler may encounter null pointer exception when they interact with JavaDB.
Variables might be assigned to null pointers and used before initialization.
Data Fault	Null pointer exception encountered in reporthandler and adminhandler.
Missing @Resource before the declaration of the utx in UserTransaction type.
Control Fault	Uncovered conditions in reporthandler and adminhandler which lead to web server error page.
Control Fault	The user can reach report page and admin page by url without a successful login.
Interface Fault	Parameter values mismatch.
Including url value, variable and request parameter value
Interface Fault	Parameter values mismatch in reporthandler and adminhandler


Part 5.  Test
Exercise 5.1 Draw a graph to show the structure (execution) of a selected procedure in your program. And compute the complexity of this procedure according to the structure graph.

Take the reporting pothole procedure as an example.


































The complexity of this procedure according to the structure graph above is 6 by the formula V(G)=E-N+2.

Exercise 5.2 Test case designed for the selected procedure.

Inputs	Execution Path
Invalid username and password 
(such as username = ”” and  
password = ””)	1-2-1
Valid username and password, valid form data
(such as username = “a”, 
password = “111111”, 
streetAddr = “5th avenue 67th”, 
location = “curb”, 
district = “Brooklyn”, 
size = “5”, 
CName = “a”, 
CPhone = “13421212212”, 
CAddr = “Brooklyn 5th avenue 65th”)	1-2-3-4-3-5-6-4
Valid username and password, invalid form data
(such as username = “a”, 
password = “111111”, 
streetAddr = “”, 
location = “curb”, 
district = “Brooklyn”, 
size = “5”, 
CName = “a”, 
CPhone = “”, 
CAddr = “Brooklyn 5th avenue 65th”)	1-2-3-4-3-5-4
Valid username and password, logout
(such as username = “a”, 
password = “111111”, 
logout)	1-2-3-4-1

