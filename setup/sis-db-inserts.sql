-- PATIENTS
INSERT INTO patient VALUES (1,'Anthony Stark','ironman',to_date('1985-01-10','YYYY-MM-DD'),'BN','177','82000',true);
INSERT INTO patient VALUES (2,'Bruce Wayne','notbatman',to_date('1980-01-10','YYYY-MM-DD'),'AN','187','80000',true);
INSERT INTO patient VALUES (3,'Thor Odinson','mjolnir',to_date('1900-12-10','YYYY-MM-DD'),'ON','210','90000',true);
INSERT INTO patient VALUES (4,'Wade Wilson','deadpool',to_date('1987-02-10','YYYY-MM-DD'),'OP','189','82000',true);
INSERT INTO patient VALUES (5,'Barry Allen','flash',to_date('1990-01-10','YYYY-MM-DD'),'BP','181','70000',true);
INSERT INTO patient VALUES (6,'Clark Kent','kalel',to_date('1970-01-10','YYYY-MM-DD'),'AP','191','85000',true);

-- PATHOLOGIES
INSERT INTO pathology VALUES (1,'Flu', 3);
INSERT INTO pathology VALUES (2,'Gastritis', 4);
INSERT INTO pathology VALUES (3,'Athlete foot', 1);
INSERT INTO pathology VALUES (4,'Tetano', 5);

-- ALLERGIES
INSERT INTO allergy VALUES (1,'Dust');
INSERT INTO allergy VALUES (2,'Penicillin');
INSERT INTO allergy VALUES (3,'Cortisone');
INSERT INTO allergy VALUES (4,'Kriptonyte');
INSERT INTO allergy VALUES (5,'Theseract');

-- PATHOLOGIES AND PATIENTS
INSERT INTO patient_pathology VALUES (1,1,2);
INSERT INTO patient_pathology VALUES (2,5,3);
INSERT INTO patient_pathology VALUES (3,4,1);

-- ALLERGIES AND PATIENTS
INSERT INTO patient_allergy VALUES (1,6,4);
INSERT INTO patient_allergy VALUES (2,3,5);
INSERT INTO patient_allergy VALUES (3,1,1);
INSERT INTO patient_allergy VALUES (4,2,1);

-- MEDICAL PROCEDURE
INSERT INTO medical_procedure VALUES (1,'Bullet removal',2,to_date('2010-03-11','YYYY-MM-DD'));
INSERT INTO medical_procedure VALUES (2,'Sharpnel removal',1,to_date('2011-04-21','YYYY-MM-DD'));
INSERT INTO medical_procedure VALUES (3,'Weapon X Project',4,to_date('2011-01-21','YYYY-MM-DD'));
INSERT INTO medical_procedure VALUES (4,'Liposuction',6,to_date('2011-08-21','YYYY-MM-DD'));

-- DIAGNOSTIC IMAGE
INSERT INTO diagnostic_image VALUES (1,'Radiography Torax',1,'RADIOGRAPHY',to_date('2011-08-21','YYYY-MM-DD')); 
INSERT INTO diagnostic_image VALUES (2,'ECG',1,'ECG',to_date('2011-08-22','YYYY-MM-DD'));
INSERT INTO diagnostic_image VALUES (3,'Ecography',3,'ECOGRAPHY',to_date('2012-08-21','YYYY-MM-DD'));  