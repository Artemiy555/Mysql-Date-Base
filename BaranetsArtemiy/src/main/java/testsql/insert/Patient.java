package testsql.insert;

public class Patient {


    private String name;
    private String surename;
    private String diagnosis;
    private Integer idDoctors;

    public Patient(String name, String surename, String diagnosis, Integer idDoctors) {
        this.name = name;
        this.surename = surename;
        this.diagnosis = diagnosis;
        this.idDoctors = idDoctors;
    }

    public Patient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Integer getIdDoctors() {
        return idDoctors;
    }

    public void setIdDoctors(Integer idDoctors) {
        this.idDoctors = idDoctors;
    }
}
