package com.cairone.sdlpocjpa.entities;
// Generated 08/06/2016 10:42:19 by Hibernate Tools 3.4.0.CR1


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cairone.sdlpocjpa.enums.MonedaCotizacionClaseEnum;

/**
 * MonedaCotId generated by hbm2java
 */
@Embeddable
public class MonedaCotizacionPKEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="fecha", nullable=false, length=23) @Temporal(TemporalType.TIMESTAMP) private Date fecha = null;
	@Column(name="moneda_tipo", nullable=false) private Integer monedaTipo = null;
	@Column(name="tipo_cotizacion", nullable=false) private MonedaCotizacionClaseEnum monedaCotizacionClaseEnum = null;

    public MonedaCotizacionPKEntity() {}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getMonedaTipo() {
		return monedaTipo;
	}

	public void setMonedaTipo(Integer monedaTipo) {
		this.monedaTipo = monedaTipo;
	}

	public MonedaCotizacionClaseEnum getMonedaCotizacionClaseEnum() {
		return monedaCotizacionClaseEnum;
	}

	public void setMonedaCotizacionClaseEnum(
			MonedaCotizacionClaseEnum monedaCotizacionClaseEnum) {
		this.monedaCotizacionClaseEnum = monedaCotizacionClaseEnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime
				* result
				+ ((monedaCotizacionClaseEnum == null) ? 0
						: monedaCotizacionClaseEnum.hashCode());
		result = prime * result
				+ ((monedaTipo == null) ? 0 : monedaTipo.hashCode());
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
		MonedaCotizacionPKEntity other = (MonedaCotizacionPKEntity) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (monedaCotizacionClaseEnum != other.monedaCotizacionClaseEnum)
			return false;
		if (monedaTipo == null) {
			if (other.monedaTipo != null)
				return false;
		} else if (!monedaTipo.equals(other.monedaTipo))
			return false;
		return true;
	}
}
