# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table allergy (
  id                        serial not null,
  name                      varchar(255),
  constraint pk_allergy primary key (id))
;

create table diagnostic_image (
  id                        serial not null,
  name                      varchar(255),
  patient_id                integer not null,
  type                      varchar(255),
  date                      timestamp,
  constraint pk_diagnostic_image primary key (id))
;

create table medical_procedure (
  id                        serial not null,
  name                      varchar(255),
  patient_id                integer not null,
  date                      timestamp,
  constraint pk_medical_procedure primary key (id))
;

create table pathology (
  id                        serial not null,
  name                      varchar(255),
  triage                    integer,
  constraint pk_pathology primary key (id))
;

create table patient (
  id                        serial not null,
  name                      varchar(255),
  login                     varchar(255),
  birth_date                timestamp,
  blood_type                varchar(255),
  height_cm                 integer,
  weight_gr                 integer,
  active                    boolean,
  constraint pk_patient primary key (id))
;


create table patient_allergy (
  patient_id                     integer not null,
  allergy_id                     integer not null,
  constraint pk_patient_allergy primary key (patient_id, allergy_id))
;

create table patient_pathology (
  patient_id                     integer not null,
  pathology_id                   integer not null,
  constraint pk_patient_pathology primary key (patient_id, pathology_id))
;
alter table diagnostic_image add constraint fk_diagnostic_image_patient_1 foreign key (patient_id) references patient (id);
create index ix_diagnostic_image_patient_1 on diagnostic_image (patient_id);
alter table medical_procedure add constraint fk_medical_procedure_patient_2 foreign key (patient_id) references patient (id);
create index ix_medical_procedure_patient_2 on medical_procedure (patient_id);



alter table patient_allergy add constraint fk_patient_allergy_allergy_01 foreign key (patient_id) references allergy (id);

alter table patient_allergy add constraint fk_patient_allergy_patient_02 foreign key (allergy_id) references patient (id);

alter table patient_pathology add constraint fk_patient_pathology_patholog_01 foreign key (patient_id) references pathology (id);

alter table patient_pathology add constraint fk_patient_pathology_patient_02 foreign key (pathology_id) references patient (id);

# --- !Downs

drop table if exists allergy cascade;

drop table if exists patient_allergy cascade;

drop table if exists diagnostic_image cascade;

drop table if exists medical_procedure cascade;

drop table if exists pathology cascade;

drop table if exists patient_pathology cascade;

drop table if exists patient cascade;

