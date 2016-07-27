package com.cairone.sdlpocjpa.entities;
// Generated 08/06/2016 10:42:19 by Hibernate Tools 3.4.0.CR1


import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.cairone.sdlpocjpa.edm.LocalidadEdm;

/**
 * LocalidadId generated by hbm2java
 * LA CLAVE PRIMARIA EN LA BASE DE DATOS NO INCLUYE A LA PROVINCIA 
 */
@Embeddable
public class LocalidadPKEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="loc_pais", nullable=false) private Integer locPais;
	@Column(name="loc_provincia", nullable=false) private Integer locProvincia;
	@Column(name="loc_postal", nullable=false, length=12) private String locPostal;

	public LocalidadPKEntity() {}

	public LocalidadPKEntity(Integer locPais, Integer locProvincia, String locPostal) {
		super();
		this.locPais = locPais;
		this.locProvincia = locProvincia;
		this.locPostal = locPostal;
	}
	
	public LocalidadPKEntity(ProvinciaEntity provincia, String locPostal) {
		super();
		this.locPais = provincia.getPais().getId();
		this.locProvincia = provincia.getId();
		this.locPostal = locPostal;
	}
	
	public LocalidadPKEntity(LocalidadEdm localidad) {
		this(localidad.getPaisID(), localidad.getProvinciaID(), localidad.getCodigoPostal());
	}
	
	public Integer getLocPais() {
		return locPais;
	}

	public void setLocPais(Integer locPais) {
		this.locPais = locPais;
	}

	public Integer getLocProvincia() {
		return locProvincia;
	}

	public void setLocProvincia(Integer locProvincia) {
		this.locProvincia = locProvincia;
	}

	public String getLocPostal() {
		return locPostal;
	}

	public void setLocPostal(String locPostal) {
		this.locPostal = locPostal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locPais == null) ? 0 : locPais.hashCode());
		result = prime * result
				+ ((locPostal == null) ? 0 : locPostal.hashCode());
		result = prime * result
				+ ((locProvincia == null) ? 0 : locProvincia.hashCode());
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
		LocalidadPKEntity other = (LocalidadPKEntity) obj;
		if (locPais == null) {
			if (other.locPais != null)
				return false;
		} else if (!locPais.equals(other.locPais))
			return false;
		if (locPostal == null) {
			if (other.locPostal != null)
				return false;
		} else if (!locPostal.equals(other.locPostal))
			return false;
		if (locProvincia == null) {
			if (other.locProvincia != null)
				return false;
		} else if (!locProvincia.equals(other.locProvincia))
			return false;
		return true;
	}
}