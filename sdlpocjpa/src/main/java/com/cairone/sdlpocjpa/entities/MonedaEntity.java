package com.cairone.sdlpocjpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity @Table(name="MONEDA")
public class MonedaEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @Column(name="moneda")
	@TableGenerator(name = "GEN_MONEDA", table="SEQUENCE_TABLE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", pkColumnValue="MONEDA_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_MONEDA") 
	private Integer id = null;
	
	@Column(name="descripcion", nullable = false, length = 50)
	private String descripcion = null;

	@Column(name="descripcion_red", nullable = false, length = 8)
	private String descripcionRed = null;

	@Column(name="prefijo", nullable = true, length = 5)
	private String prefijo = null;

	@Column(name="contabiliza", nullable = true, length = 1)
	private String contabiliza = null;
	
	@Column(name="moneda_nacional", nullable = false)
	private Boolean monedaNacional = null;

	public MonedaEntity() {}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContabiliza() {
		return this.contabiliza;
	}

	public void setContabiliza(String contabiliza) {
		this.contabiliza = contabiliza;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcionRed() {
		return this.descripcionRed;
	}

	public void setDescripcionRed(String descripcionRed) {
		this.descripcionRed = descripcionRed;
	}

	public boolean getMonedaNacional() {
		return this.monedaNacional;
	}

	public void setMonedaNacional(Boolean monedaNacional) {
		this.monedaNacional = monedaNacional;
	}

	public String getPrefijo() {
		return this.prefijo;
	}

	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
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
		MonedaEntity other = (MonedaEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.descripcion;
	}
	
}