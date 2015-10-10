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


-- PATIENT
CREATE TABLE patient (
    id integer NOT NULL PRIMARY KEY,
    name character varying(256) NOT NULL,
    login character varying(512) NOT NULL,
    birth_date date DEFAULT ('now'::text)::date NOT NULL,
    blood_type character varying(2) NOT NULL,
    height_cm integer NOT NULL,
    weight_gr integer NOT NULL,
    active boolean DEFAULT true NOT NULL
);
ALTER TABLE patient OWNER TO sis_role;
CREATE SEQUENCE patient_id_seq
    START WITH 10
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE patient_id_seq OWNER TO sis_role;
ALTER SEQUENCE patient_id_seq OWNED BY patient.id;

-- PATHOLOGY
CREATE TABLE pathology (
    id integer NOT NULL PRIMARY KEY,
    name character varying(256) NOT NULL,
    triage integer NOT NULL,
    CONSTRAINT triage_max_val CHECK (triage < 6),
    CONSTRAINT triage_min_val CHECK (triage > 0)
);
ALTER TABLE pathology OWNER TO sis_role;
CREATE SEQUENCE pathology_id_seq
    START WITH 10
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE pathology_id_seq OWNER TO sis_role;
ALTER SEQUENCE pathology_id_seq OWNED BY pathology.id;

-- PATIENT_PATHOLOGY
CREATE TABLE patient_pathology (
    id integer NOT NULL PRIMARY KEY,
    patient_id integer NOT NULL,
    pathology_id integer NOT NULL,
    CONSTRAINT unq_patient_pathology UNIQUE(patient_id,pathology_id) 
);
ALTER TABLE patient_pathology OWNER TO sis_role;
CREATE UNIQUE INDEX patient_pathology_idx ON patient_pathology (patient_id);
CREATE SEQUENCE patient_pathology_id_seq
    START WITH 10
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE patient_pathology_id_seq OWNER TO sis_role;
ALTER SEQUENCE patient_pathology_id_seq OWNED BY patient_pathology.id;

-- PATHOLOGY
CREATE TABLE allergy (
    id integer NOT NULL PRIMARY KEY,
    name character varying(256) NOT NULL
);
ALTER TABLE allergy OWNER TO sis_role;
CREATE SEQUENCE allergy_id_seq
    START WITH 10
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE allergy_id_seq OWNER TO sis_role;
ALTER SEQUENCE allergy_id_seq OWNED BY allergy.id;

-- PATIENT_PATHOLOGY
CREATE TABLE patient_allergy (
    id integer NOT NULL PRIMARY KEY,
    patient_id integer NOT NULL,
    allergy_id integer NOT NULL,
    CONSTRAINT unq_patient_allergy UNIQUE(patient_id,allergy_id) 
);
ALTER TABLE patient_allergy OWNER TO sis_role;
CREATE UNIQUE INDEX patient_allergy_idx ON patient_allergy (patient_id);
CREATE SEQUENCE patient_allergy_id_seq
    START WITH 10
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE patient_allergy_id_seq OWNER TO sis_role;
ALTER SEQUENCE patient_allergy_id_seq OWNED BY patient_allergy.id;

-- MEDICAL_PROCEDURE
CREATE TABLE medical_procedure (
    id integer NOT NULL PRIMARY KEY,
    name character varying(256) NOT NULL,
    patient_id integer NOT NULL REFERENCES patient(id),
    date date NOT NULL
);
ALTER TABLE medical_procedure OWNER TO sis_role;
CREATE SEQUENCE medical_procedure_id_seq
    START WITH 10
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE medical_procedure_id_seq OWNER TO sis_role;
ALTER SEQUENCE medical_procedure_id_seq OWNED BY medical_procedure.id;

-- DIAGNOSTIC_IMAGE
CREATE TABLE diagnostic_image (
    id integer NOT NULL PRIMARY KEY,
    name character varying(256) NOT NULL,
    patient_id integer NOT NULL REFERENCES patient(id),
    type character varying(256) NOT NULL,
    date date NOT NULL
);
ALTER TABLE diagnostic_image OWNER TO sis_role;
CREATE SEQUENCE diagnostic_image_id_seq
    START WITH 10
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE diagnostic_image_id_seq OWNER TO sis_role;
ALTER SEQUENCE diagnostic_image_id_seq OWNED BY diagnostic_image.id;

-- FOREIGN KEYS

ALTER TABLE ONLY patient_pathology
    ADD CONSTRAINT patient_fk FOREIGN KEY (patient_id) REFERENCES patient(id);
ALTER TABLE ONLY patient_pathology
    ADD CONSTRAINT pathology_fk FOREIGN KEY (pathology_id) REFERENCES pathology(id);

ALTER TABLE ONLY patient_allergy
    ADD CONSTRAINT patient_fk FOREIGN KEY (patient_id) REFERENCES patient(id);
ALTER TABLE ONLY patient_allergy
    ADD CONSTRAINT allergy_fk FOREIGN KEY (allergy_id) REFERENCES allergy(id);


