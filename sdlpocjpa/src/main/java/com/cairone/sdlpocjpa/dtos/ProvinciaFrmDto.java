package com.cairone.sdlpocjpa.dtos;

import com.cairone.sdlpocjpa.edm.ProvinciaEdm;

public class ProvinciaFrmDto {
	
	private Integer id = null;
	private Integer paisID = null;
	private String descripcion = null;
	private String descripcionReducida = null;

	public ProvinciaFrmDto() {}

	public ProvinciaFrmDto(Integer id, Integer paisID, String descripcion, String descripcionReducida) {
		super();
		this.id = id;
		this.paisID = paisID;
		this.descripcion = descripcion;
		this.descripcionReducida = descripcionReducida;
	}
	
	public ProvinciaFrmDto(ProvinciaEdm provincia) {
		this(	
			provincia.getId(),
			provincia.getPaisID(),
			provincia.getDescripcion() == null || provincia.getDescripcion().trim().isEmpty() ? null : provincia.getDescripcion().toUpperCase(),
			provincia.getDescripcionReducida() == null || provincia.getDescripcionReducida().trim().isEmpty() ? null : provincia.getDescripcionReducida().toUpperCase()
		);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPaisID() {
		return paisID;
	}

	public void setPaisID(Integer paisID) {
		this.paisID = paisID;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion == null || descripcion.trim().isEmpty() ? null : descripcion.trim().toUpperCase();
	}

	public String getDescripcionReducida() {
		return descripcionReducida;
	}

	public void setDescripcionReducida(String descripcionReducida) {
		this.descripcionReducida = descripcionReducida == null || descripcionReducida.trim().isEmpty() ? null : descripcionReducida.trim().toUpperCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((paisID == null) ? 0 : paisID.hashCode());
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
		ProvinciaFrmDto other = (ProvinciaFrmDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (paisID == null) {
			if (other.paisID != null)
				return false;
		} else if (!paisID.equals(other.paisID))
			return false;
		return true;
	}
	/*
	public BindingResult validar(ProvinciaFrmDtoValidator provinciaFrmDtoValidator) {
		
		DataBinder binder = new DataBinder(this);
		binder.
		binder.setValidator(provinciaFrmDtoValidator);
		binder.validate();
		
		return binder.getBindingResult();
	}*/
}
