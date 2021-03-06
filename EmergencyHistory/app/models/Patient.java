package models;

import com.avaje.ebean.Model;
import models.mongo.PatientMongo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * POJO class to store patient's information
 * 
 * @author Sebastian Gamba Pinilla
 */
@Entity
@Table(name = "patient")
public class Patient extends Model implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "login")
	private String login;
	@Column(name = "birth_date")
	private Date birthDate;
	@Column(name = "blood_type")
	private String bloodType;
	@Column(name = "height_cm")
	private Integer heightCm;
	@Column(name = "weight_gr")
	private Integer weightGr;
	@Column(name = "active")
	private boolean active;

	@ManyToMany(mappedBy = "patients")
	private List<Allergy> allergies;

	@ManyToMany(mappedBy = "patients")
	private List<Pathology> pathologies;

	@OneToMany(mappedBy = "patient")
	private List<DiagnosticImage> diagnosticImages;
	@OneToMany(mappedBy = "patient")
	private List<MedicalProcedure> medicalProcedures;

	public static Finder<Integer, Patient> find = new Finder<Integer,Patient>("secundary", Integer.class, Patient.class);
	public static Finder<Integer,Patient> findH2 = new Finder<Integer, Patient>(Patient.class);

	public Patient(Integer id, String name, String login, Date birthDate, String bloodType, Integer heightCm, Integer weightGr, boolean active) {
		this.id = id;
		this.name = name;
		this.login = login;
		this.birthDate = birthDate;
		this.bloodType = bloodType;
		this.heightCm = heightCm;
		this.weightGr = weightGr;
		this.active = active;
	}

	/**
	 * Builds a resource patient with a mongo Patient
	 * @param patientMongo
	 */
	public Patient(PatientMongo patientMongo){
		this.id = patientMongo.id_ext;
		this.name =  patientMongo.name;
		this.login = patientMongo.login;
		this.birthDate = patientMongo.birthDate;
		this.bloodType = patientMongo.bloodType;
		this.heightCm = patientMongo.heightCm;
		this.weightGr = patientMongo.weightGr;
		this.active = patientMongo.active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public Integer getHeightCm() {
		return heightCm;
	}

	public void setHeightCm(Integer heightCm) {
		this.heightCm = heightCm;
	}

	public Integer getWeightGr() {
		return weightGr;
	}

	public void setWeightGr(Integer weightGr) {
		this.weightGr = weightGr;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setAllergies(List<Allergy> allergies) {
		this.allergies = allergies;
	}

	public void setPathologies(List<Pathology> pathologies) {
		this.pathologies = pathologies;
	}

	public void setDiagnosticImages(List<DiagnosticImage> diagnosticImages) {
		this.diagnosticImages = diagnosticImages;
	}

	public void setMedicalProcedures(List<MedicalProcedure> medicalProcedures) {
		this.medicalProcedures = medicalProcedures;
	}

	@Override
	public String toString() {
		return "Patient{" +
				"id=" + id +
				", name='" + name + '\'' +
				", login='" + login + '\'' +
				", birthDate=" + birthDate +
				", bloodType='" + bloodType + '\'' +
				", heightCm=" + heightCm +
				", weightGr=" + weightGr +
				", active=" + active +
				", allergies=" + allergies +
				", pathologies=" + pathologies +
				", diagnosticImages=" + diagnosticImages +
				", medicalProcedures=" + medicalProcedures +
				'}';
	}
}
