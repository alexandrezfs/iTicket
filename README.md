iTicket
=======

A ticket manager written in J2EE with JSP Framework and Hibernate ORM.
Users can register as DEVELOPER or PRODUCT_OWNER, and create tickets / manage them.

+ A DEVELOPER can mark as ticket as "In progress".
+ A PRODUCT_OWNER can CRUD on tickets.

### Environment :

+ Glassfish 4
+ Hibernate
+ JSP framework
+ Bower
+ Foundation CSS Framework

### Installation :

+ Import the module in IntellijIDEA
+ Create a new MySQL database and import iTicket.sql into this database
+ Confugure yout database credentials in hibernate.cfg.xml
+ Link the JDBC Driver to the module
+ Make sure that the module is running on Glassfish 4. It can work on another server that support J2EE, but since the project has been build on Glassfish 4, I can't guarantee that it will work on another platform.
+ Run the module
