package com.sample.credentials.common.config.rest.handlers;

public enum EnumError {

    /** E000 - Le service est indisponible. */
    E000("E000", "Le service est indisponible"),

    /** E001 - Absence d'un paramètre d’appel obligatoire. */
    E001("E001", "Absence d'un paramètre d’appel obligatoire"),

    /** E002 - Format d’un paramètre d’appel servi non valide. */
    E002("E002", "Format d’un paramètre d’appel servi non valide");

    /** code. */
    private String code;

    /** description. */
    private String description;

    EnumError(String code,String description){
        this.code=code;
        this.description=description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
