package com.cairone.sdlpocjpa.edm;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.cairone.sdlpocjpa.entities.LocalidadEntity;
import com.sdl.odata.api.edm.annotations.EdmEntity;
import com.sdl.odata.api.edm.annotations.EdmEntitySet;
import com.sdl.odata.api.edm.annotations.EdmNavigationProperty;
import com.sdl.odata.api.edm.annotations.EdmProperty;

@EdmEntity(name = "Localidad", namespace = "com.cairone.sdlpocjpa", key = { "paisID", "provinciaID", "codigoPostal" }, containerName = "SDLPoC")
@EdmEntitySet("Localidades")
public class LocalidadEdm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EdmProperty(name = "paisID", nullable = true)
	private Integer paisID = null;
	
	@EdmProperty(name = "provinciaID", nullable = true)
	private Integer provinciaID = null;

	@EdmProperty(name = "codigoPostal", nullable = true)
	private String codigoPostal = null;

	@EdmNavigationProperty(name = "pais", nullable = true) 
	private PaisEdm pais = null;

	@EdmNavigationProperty(name = "provincia", nullable = true) 
	private ProvinciaEdm provincia = null;

	@EdmProperty(name = "descripcion", nullable = true)
	private String descripcion = null;
	
	@EdmProperty(name = "prefijo", nullable = true)
	private Integer prefijo = null;
    
	public LocalidadEdm() {}

	public LocalidadEdm(String codigoPostal, ProvinciaEdm provincia, String descripcion, Integer prefijo) {
		super();
		this.paisID = provincia.getPaisID();
		this.pais = provincia.getPais();
		this.provinciaID = provincia.getId();
		this.provincia = provincia;
		this.codigoPostal = codigoPostal;
		this.descripcion = descripcion;
		this.prefijo = prefijo;
	}
	
	public LocalidadEdm(LocalidadEntity localidadEntity) {
		this(
			localidadEntity.getLocPostal(),
			new ProvinciaEdm(localidadEntity.getProvincia()),
			localidadEntity.getDescripcion(),
			localidadEntity.getPrefijo()
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
		this.codigoPostal = codigoPostal;
	}

	public PaisEdm getPais() {
		return pais;
	}

	public void setPais(PaisEdm pais) {
		this.pais = pais;
	}

	public ProvinciaEdm getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaEdm provincia) {
		this.provincia = provincia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		LocalidadEdm other = (LocalidadEdm) obj;
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

	@Override
	public String toString() {
		return String.format("%s (%s)", this.descripcion, this.codigoPostal);
	}

	public static final List<LocalidadEdm> crearLista(List<LocalidadEntity> localidadEntities) {
		return localidadEntities.stream().map(entity -> { return new LocalidadEdm(entity); }).collect(Collectors.toList());
	}
}
