package com.cairone.sdlpocjpa.entities;
// Generated 08/06/2016 10:42:19 by Hibernate Tools 3.4.0.CR1


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProvinciaId generated by hbm2java
 */
@Embeddable
public class ProvinciaPKEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="pais", nullable=false) private Integer pais = null;
	@Column(name="provincia", nullable=false) private Integer provincia = null;

    public ProvinciaPKEntity() {}

	public ProvinciaPKEntity(Integer paisId, Integer provinciaId) {
		super();
		this.pais = paisId;
		this.provincia = provinciaId;
	}
	
	public ProvinciaPKEntity(PaisEntity pais, Integer provinciaId) {
		super();
		this.pais = pais.getId();
		this.provincia = provinciaId;
	}

	public Integer getPais() {
		return pais;
	}

	public void setPais(Integer pais) {
		this.pais = pais;
	}

	public Integer getProvincia() {
		return provincia;
	}

	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result
				+ ((provincia == null) ? 0 : provincia.hashCode());
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
		ProvinciaPKEntity other = (ProvinciaPKEntity) obj;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		return true;
	}
}


