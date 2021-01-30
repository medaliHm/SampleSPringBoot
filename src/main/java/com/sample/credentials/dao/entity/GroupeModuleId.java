package com.sample.credentials.dao.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Composite id entre la table groupe et module.
 */
@Embeddable
public class GroupeModuleId  implements Serializable {

    @Column(name = "GROUPE_LIB_GROUPE")
    private String libelleGroup;

    @Column(name="MODULE_CD_MODULE")
    private long codeModule ;

    


    /**
     * Accesseur codeModule.
     * @return
     */
    public long getCodeModule() {
        return codeModule;
    }

    /**
     * Accesseur id du groupe.
     * @return
     */
  public String getLibelleGroup() {
	return libelleGroup;
}

    /**
     * Mutateur code module.
     * @param codeModule
     */
    public void setCodeModule(long codeModule) {
        this.codeModule = codeModule;
    }

  
    public void setLibelleGroup(String libelleGroup) {
		this.libelleGroup = libelleGroup;
	}
}
