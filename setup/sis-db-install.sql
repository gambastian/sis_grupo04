-- Role: stampidia clave: 1234567890

-- DROP ROLE stampidia;

CREATE ROLE sis_role LOGIN ENCRYPTED PASSWORD '37fb11bf3ee72586152c895c1a63f82f' 
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


-- SMTP-PLAN
CREATE TABLE patient (
    id integer NOT NULL,
    name character varying(256) NOT NULL,
    description character varying(512) NOT NULL,
    status boolean DEFAULT true NOT NULL
);
ALTER TABLE patient OWNER TO sis_role;
CREATE SEQUENCE patient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE patient_id_seq OWNER TO sis_role;
ALTER SEQUENCE patient_id_seq OWNED BY patient.id;


