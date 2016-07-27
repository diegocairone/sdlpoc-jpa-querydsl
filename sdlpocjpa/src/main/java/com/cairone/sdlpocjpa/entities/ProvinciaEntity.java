package com.cairone.sdlpocjpa.entities;
// Generated 08/06/2016 10:42:19 by Hibernate Tools 3.4.0.CR1


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Provincia generated by hbm2java
 */
@Entity @Table(name="PROVINCIA")
public class ProvinciaEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId private ProvinciaPKEntity pk = null;
	
	@Column(name="provincia", insertable = false, updatable = false) 
	private Integer id = null;
	
	@OneToOne @JoinColumn(name = "pais", insertable = false, updatable = false)
	private PaisEntity pais = null;
	
	@Column(name="descripcion", length=40)
	private String descripcion = null;
	
	@Column(name="descripcion_red", length=8)
	private String descripcionRed = null;
     
    public ProvinciaEntity() {
    	this.pk = new ProvinciaPKEntity();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.pk.setProvincia(id);
	}

	public PaisEntity getPais() {
		return pais;
	}

	public void setPais(PaisEntity pais) {
		this.pais = pais;
		this.pk.setPais(pais.getId());
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionRed() {
		return descripcionRed;
	}

	public void setDescripcionRed(String descripcionRed) {
		this.descripcionRed = descripcionRed;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProvinciaEntity other = (ProvinciaEntity) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	@Override
    public String toString() {
    	return String.format("%s (%s)", this.descripcion, this.id);
    }
}