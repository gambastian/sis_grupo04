-- DROP ROLE scheduler;

CREATE ROLE scheduler LOGIN ENCRYPTED PASSWORD 'md528a44df13c85f2a25a17fbbfe3074145'
VALID UNTIL 'infinity';

--Database: stampidia

CREATE DATABASE sis_schedule
  WITH OWNER = scheduler
       ENCODING = 'UTF8'
       CONNECTION LIMIT = -1;

REVOKE ALL ON DATABASE sis_schedule FROM public;
GRANT ALL ON DATABASE sis_schedule to scheduler;

-- CONEXION CON LA NUEVA BASE DE DATOS

\connect sis_schedule;