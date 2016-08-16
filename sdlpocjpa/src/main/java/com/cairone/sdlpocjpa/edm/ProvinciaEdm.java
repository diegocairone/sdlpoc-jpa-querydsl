package com.cairone.sdlpocjpa.edm;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.cairone.sdlpocjpa.entities.ProvinciaEntity;
import com.sdl.odata.api.edm.annotations.EdmEntity;
import com.sdl.odata.api.edm.annotations.EdmEntitySet;
import com.sdl.odata.api.edm.annotations.EdmNavigationProperty;
import com.sdl.odata.api.edm.annotations.EdmProperty;

@EdmEntity(name = "Provincia", namespace = "com.cairone.sdlpocjpa", key = { "id", "paisID" }, containerName = "SDLPoC")
@EdmEntitySet("Provincias")
public class ProvinciaEdm implements Serializable {

	private static final long serialVersionUID = 1L;

	@EdmProperty(name = "id", nullable = true)
	private Integer id = null;
	
	@EdmProperty(name = "paisID", nullable = true)
	private Integer paisID = null;

	@EdmNavigationProperty(name = "pais", nullable = true) 
	private PaisEdm pais = null;
	
	@EdmProperty(name = "descripcion", nullable = true, maxLength = 40)
	private String descripcion = null;
	
	@EdmProperty(name = "descripcionReducida", nullable = true, maxLength = 8)
	private String descripcionReducida = null;
	
	public ProvinciaEdm() {}

	public ProvinciaEdm(Integer id, PaisEdm pais, String descripcion, String descripcionReducida) {
		super();
		this.id = id;
		this.paisID = pais.getId();
		this.pais = pais;
		this.descripcion = descripcion;
		this.descripcionReducida = descripcionReducida;
	}
	
	public ProvinciaEdm(ProvinciaEntity provinciaEntity) {
		this(
			provinciaEntity.getId(), 
			new PaisEdm(provinciaEntity.getPais()),
			provinciaEntity.getDescripcion(), 
			provinciaEntity.getDescripcionRed()
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

	public PaisEdm getPais() {
		return pais;
	}

	public void setPais(PaisEdm pais) {
		this.pais = pais;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionReducida() {
		return descripcionReducida;
	}

	public void setDescripcionReducida(String descripcionReducida) {
		this.descripcionReducida = descripcionReducida;
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
		ProvinciaEdm other = (ProvinciaEdm) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s (%s)", this.descripcion, this.id);
	}

	public static final List<ProvinciaEdm> crearLista(List<ProvinciaEntity> provinciaEntities) {
		return provinciaEntities.stream().map(entity -> { return new ProvinciaEdm(entity); }).collect(Collectors.toList());
	}
}
