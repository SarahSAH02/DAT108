package com.example.demo;

public class Deltager {
    
	// Variabelfelt og regler:
	private String mobil;
	private String passord;
	private String repetertPassord;
	private String fornavn;
	private String etternavn;
	private String kjonn;
    
    // Konstruktører:
    public Deltager() {}
    
    public Deltager(String mobil, String passord, String fornavn, String etternavn, String kjonn) {
        this.mobil = mobil;
        this.passord = passord;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.kjonn = kjonn;
    }

    // Gettere og settere:
    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public String getKjonn() {
        return kjonn;
    }

    public void setKjonn(String kjonn) {
        this.kjonn = kjonn;
    }
    
    public String getRepetertPassord() {
        return repetertPassord;
    }

    public void setRepetertPassord(String repetertPassord) {
        this.repetertPassord = repetertPassord;
    }
    
}