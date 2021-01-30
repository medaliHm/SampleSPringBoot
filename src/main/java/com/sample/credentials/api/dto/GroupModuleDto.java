package com.sample.credentials.api.dto;

public class GroupModuleDto {

    private String libelleGroupe;

    private long codeModule;

    private int lecture;

    private int ecriture;


    public String getLibelleGroupe() {
        return libelleGroupe;
    }

    public void setLibelleGroupe(String libelleGroupe) {
        this.libelleGroupe = libelleGroupe;
    }

    public long getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(long codeModule) {
        this.codeModule = codeModule;
    }

    public int getLecture() {
        return lecture;
    }

    public void setLecture(int lecture) {
        this.lecture = lecture;
    }

    public int getEcriture() {
        return ecriture;
    }

    public void setEcriture(int ecriture) {
        this.ecriture = ecriture;
    }

    public GroupModuleDto(){

    }

}
