package com.sample.credentials.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


/**
 * Classe representant la table d'association entre chaque groupe et module.
 */
@Entity
@Table(name = "MIO_SOCL_GROUPE_MODULE")
public class GroupeModule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private GroupeModuleId id = new GroupeModuleId();

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("codeModule")
	private RftCodeModule module;

	@Column(name = "can_read")
	private int canRead;
	@Column(name = "can_write")
	private int canWrite;

	@Column(name = "DT_DEBUT_VAL")
	private Date dateDebut;

	@Column(name = "DT_FIN_VAL")
	private Date dateFin;

	@Column(name = "DT_INTEGRATION")
	private Date dateInteg;

	@Column(name = "USER_INTEGRATION")
	private String userInteg;

	@Column(name = "REF_FICHIER")
	private String refFichier;

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Date getDateInteg() {
		return dateInteg;
	}

	public void setDateInteg(Date dateInteg) {
		this.dateInteg = dateInteg;
	}

	public String getUserInteg() {
		return userInteg;
	}

	public void setUserInteg(String userInteg) {
		this.userInteg = userInteg;
	}

	public String getRefFichier() {
		return refFichier;
	}

	public void setRefFichier(String refFichier) {
		this.refFichier = refFichier;
	}

	/**
	 * Constructeur privé de la classe GroupeModule.
	 */
	public GroupeModule() {
	}

	/**
	 * Constructeur de la classe GroupeModule.
	 * 
	 * @param groupe
	 * @param module
	 */
	public GroupeModule(String libelleGroupe, RftCodeModule module) {
		this.module = module;
		GroupeModuleId id = new GroupeModuleId();
		id.setCodeModule(module.getCodeModule());
		id.setLibelleGroup(libelleGroupe);
		this.id = id;

	}

	/**
	 * Accesseur de l'id du GroupeModule.
	 * 
	 * @return
	 */
	public GroupeModuleId getId() {
		return id;
	}

	/**
	 * Mutateur de l'id GroupeModule.
	 * 
	 * @param id
	 */
	public void setId(GroupeModuleId id) {
		this.id = id;
	}

	public RftCodeModule getModule() {
		return module;
	}

	public void setModule(RftCodeModule module) {
		this.module = module;
	}

	public int getCanRead() {
		return canRead;
	}

	public void setCanRead(int canRead) {
		this.canRead = canRead;
	}

	public int getCanWrite() {
		return canWrite;
	}

	public void setCanWrite(int canWrite) {
		this.canWrite = canWrite;
	}

}
