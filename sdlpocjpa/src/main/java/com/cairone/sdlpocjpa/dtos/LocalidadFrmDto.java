package com.cairone.sdlpocjpa.dtos;

import com.cairone.sdlpocjpa.edm.LocalidadEdm;

public class LocalidadFrmDto {

	private Integer paisID = null;
	private Integer provinciaID = null;
	private String codigoPostal = null;
	private String descripcion = null;
	private Integer prefijo = null;

	public LocalidadFrmDto() {}

	public LocalidadFrmDto(Integer paisID, Integer provinciaID, String codigoPostal, String descripcion, Integer prefijo) {
		super();
		this.paisID = paisID;
		this.provinciaID = provinciaID;
		this.codigoPostal = codigoPostal;
		this.descripcion = descripcion;
		this.prefijo = prefijo;
	}
	
	public LocalidadFrmDto(LocalidadEdm localidad) {
		this(
			localidad.getPaisID(),
			localidad.getProvinciaID(),
			localidad.getCodigoPostal() == null || localidad.getCodigoPostal().trim().isEmpty() ? null : localidad.getCodigoPostal().trim().toUpperCase(),
			localidad.getDescripcion() == null || localidad.getDescripcion().trim().isEmpty() ? null : localidad.getDescripcion().trim().toUpperCase(),
			localidad.getPrefijo()
		);
	}

	public Integer getPaisID() {
		return paisID;
	}

	public void setPaisID(Integer paisID) {
		this.paisID = paisID;
	}

	public Integer getProvinciaID() {
		return provinciaID;
	}

	public void setProvinciaID(Integer provinciaID) {
		this.provinciaID = provinciaID;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal == null || codigoPostal.trim().isEmpty() ? null : codigoPostal.trim().toUpperCase();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion == null || descripcion.trim().isEmpty() ? null : descripcion.trim().toUpperCase();
	}

	public Integer getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(Integer prefijo) {
		this.prefijo = prefijo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoPostal == null) ? 0 : codigoPostal.hashCode());
		result = prime * result + ((paisID == null) ? 0 : paisID.hashCode());
		result = prime * result
				+ ((provinciaID == null) ? 0 : provinciaID.hashCode());
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
		LocalidadFrmDto other = (LocalidadFrmDto) obj;
		if (codigoPostal == null) {
			if (other.codigoPostal != null)
				return false;
		} else if (!codigoPostal.equals(other.codigoPostal))
			return false;
		if (paisID == null) {
			if (other.paisID != null)
				return false;
		} else if (!paisID.equals(other.paisID))
			return false;
		if (provinciaID == null) {
			if (other.provinciaID != null)
				return false;
		} else if (!provinciaID.equals(other.provinciaID))
			return false;
		return true;
	}
}
