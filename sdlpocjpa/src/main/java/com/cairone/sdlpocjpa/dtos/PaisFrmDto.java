package com.cairone.sdlpocjpa.dtos;

import com.cairone.sdlpocjpa.edm.PaisEdm;

public class PaisFrmDto {

	private Integer id = null;
	private String descripcion = null;
	private String nacionalidad = null;
	private Integer prefijo = null;
	private Boolean paraisoFiscal = null;
	private Boolean noColaboraLd = null;
	
	public PaisFrmDto() {}

	public PaisFrmDto(Integer id, String descripcion, String nacionalidad, Integer prefijo, Boolean paraisoFiscal, Boolean noColaboraLd) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.nacionalidad = nacionalidad;
		this.prefijo = prefijo;
		this.paraisoFiscal = paraisoFiscal;
		this.noColaboraLd = noColaboraLd;
	}
	
	public PaisFrmDto(PaisEdm pais) {
		this(
				pais.getId(),
				pais.getDescripcion() == null || pais.getDescripcion().trim().isEmpty() ? null : pais.getDescripcion().trim().toUpperCase(),
				pais.getNacionalidad() == null || pais.getNacionalidad().trim().isEmpty() ? null : pais.getNacionalidad().trim().toUpperCase(),
				pais.getPrefijo(),
				pais.getParaisoFiscal(),
				pais.getNoColaboraLd()
		);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Integer getPrefijo() {
		return prefijo;
	}

	public void setPrefijo(Integer prefijo) {
		this.prefijo = prefijo;
	}

	public Boolean getParaisoFiscal() {
		return paraisoFiscal;
	}

	public void setParaisoFiscal(Boolean paraisoFiscal) {
		this.paraisoFiscal = paraisoFiscal;
	}

	public Boolean getNoColaboraLd() {
		return noColaboraLd;
	}

	public void setNoColaboraLd(Boolean noColaboraLd) {
		this.noColaboraLd = noColaboraLd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PaisFrmDto other = (PaisFrmDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
