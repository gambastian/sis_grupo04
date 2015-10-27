CREATE ROLE sis_role_fast LOGIN ENCRYPTED PASSWORD 'md5c787360a93d37012398f47d8a8cc870f'
VALID UNTIL 'infinity';
   
CREATE DATABASE sis_fast
  WITH OWNER = sis_role_fast
       ENCODING = 'UTF8'
       CONNECTION LIMIT = -1;
       
REVOKE ALL ON DATABASE sis_fast FROM public;
GRANT ALL ON DATABASE sis_fast to sis_role_fast;

-- CONEXION CON LA NUEVA BASE DE DATOS

\connect sis_fast;



