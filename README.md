# Spring_Security
Spring security practice looking at user authentication and authorisation with roles and permission

Looking at adding some security to the application. The golf application will have user roles for ADMIN, USER, HANDICAPADMIN and EVENTADMIN. ADMIN 
will have access to view and edit all areas, EVENTADMIN can only view and edit courses and events. HANDICAPADMIN can view member details but only edit their handicaps
USER role will only be able to view the main user area. They can view and read all areas in the main site. ADMIN duties are behind the /management path and this path is restricted 
to those with ADMIN, HADNICAPADMIN and EVENTADMIN roles. Within these roles they have either READ, WRITE or both permissions.
