create table appointment (
appointment_id            varchar(255) not null,
patient                   varchar(255),
doctor_id                 varchar(255),
speciality_id             varchar(255),
address                   varchar(255),
time                      timestamp,
constraint uq_appointment_speciality_id unique (speciality_id),
constraint pk_appointment primary key (appointment_id))
;

create table doctor (
doctor_id                 varchar(255) not null,
name                      varchar(255),
phone                     integer,
default_address           varchar(255),
email                     varchar(255),
constraint pk_doctor primary key (doctor_id))
;

create table insurance (
insurance_id              varchar(255) not null,
name                      varchar(255),
constraint pk_insurance primary key (insurance_id))
;

create table speciality (
speciality_id             varchar(255) not null,
name                      varchar(255),
constraint pk_speciality primary key (speciality_id))
;


create table insurance_doctor (
insurance_id                   varchar(255) not null,
doctor_id                      varchar(255) not null,
constraint pk_insurance_doctor primary key (insurance_id, doctor_id))
;

create table doctor_speciality (
speciality_id                  varchar(255) not null,
doctor_id                      varchar(255) not null,
constraint pk_doctor_speciality primary key (speciality_id, doctor_id))
;
alter table appointment add constraint fk_appointment_doctor_1 foreign key (doctor_id) references doctor (doctor_id);
create index ix_appointment_doctor_1 on appointment (doctor_id);
alter table appointment add constraint fk_appointment_speciality_2 foreign key (speciality_id) references speciality (speciality_id);
create index ix_appointment_speciality_2 on appointment (speciality_id);
alter table insurance_doctor add constraint fk_insurance_doctor_insurance_01 foreign key (insurance_id) references insurance (insurance_id);
alter table insurance_doctor add constraint fk_insurance_doctor_doctor_02 foreign key (doctor_id) references doctor (doctor_id);
alter table doctor_speciality add constraint fk_doctor_speciality_speciali_01 foreign key (speciality_id) references speciality (speciality_id);
alter table doctor_speciality add constraint fk_doctor_speciality_doctor_02 foreign key (doctor_id) references doctor (doctor_id);