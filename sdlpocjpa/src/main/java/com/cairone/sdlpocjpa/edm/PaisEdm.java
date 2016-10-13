package com.cairone.sdlpocjpa.edm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cairone.sdlpocjpa.entities.PaisEntity;
import com.sdl.odata.api.edm.annotations.EdmEntity;
import com.sdl.odata.api.edm.annotations.EdmEntitySet;
import com.sdl.odata.api.edm.annotations.EdmProperty;

@EdmEntity(name = "Pais", namespace = "com.cairone.sdlpocjpa", key = "id", containerName = "SDLPoC")
@EdmEntitySet("Paises")
public class PaisEdm implements Serializable
{
	private static final long serialVersionUID = 1L;

	@EdmProperty(name = "id", nullable = true)
	private Integer id = null;
	
	@EdmProperty(name = "descripcion", nullable = true)
	private String descripcion = null;
	
	@EdmProperty(name = "nacionalidad", nullable = true)
	private String nacionalidad = null;
	
	@EdmProperty(name = "prefijo", nullable = true)
	private Integer prefijo = null;
	
	@EdmProperty(name = "paraisoFiscal", nullable = true)
	private Boolean paraisoFiscal = null;
	
	@EdmProperty(name = "noColaboraLd", nullable = true)
	private Boolean noColaboraLd = null;

	public PaisEdm() {}
	
	public PaisEdm(Integer id, String descripcion, String nacionalidad, Integer prefijo, Boolean paraisoFiscal, Boolean noColaboraLd) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.nacionalidad = nacionalidad;
		this.prefijo = prefijo;
		this.paraisoFiscal = paraisoFiscal;
		this.noColaboraLd = noColaboraLd;
	}
	
	public PaisEdm(PaisEntity paisEntity) {
		this(paisEntity.getId(), paisEntity.getDescripcion(), paisEntity.getNacionalidad(), paisEntity.getPrefijo(), paisEntity.getParaisoFiscal(), paisEntity.getNoColaboraLd());
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
		this.descripcion = descripcion == null || descripcion.trim().isEmpty() ? null : descripcion.trim().toUpperCase();
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad == null || nacionalidad.trim().isEmpty() ? null : nacionalidad.trim().toUpperCase();
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
		PaisEdm other = (PaisEdm) obj;
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

	public static final List<PaisEdm> crearLista(Iterable<PaisEntity> paisEntities) {
		
		List<PaisEdm> paises = new ArrayList<PaisEdm>();
		
		for(PaisEntity paisEntity : paisEntities) {
			paises.add(new PaisEdm(paisEntity));
		}
		
		return paises;
	}
}
