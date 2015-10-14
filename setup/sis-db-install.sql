-- DROP ROLE stampidia;

CREATE ROLE sis_role LOGIN ENCRYPTED PASSWORD 'md5db8f512bbcdbcb322492d62501b4cb5a' 
VALID UNTIL 'infinity';
   
--Database: stampidia

CREATE DATABASE sis
  WITH OWNER = sis_role
       ENCODING = 'UTF8'
       CONNECTION LIMIT = -1;
       
REVOKE ALL ON DATABASE sis FROM public;
GRANT ALL ON DATABASE sis to sis_role; 

-- CONEXION CON LA NUEVA BASE DE DATOS

\connect sis;



