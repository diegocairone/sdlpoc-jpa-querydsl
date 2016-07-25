package com.cairone.sdlpocjpa.entities;
// Generated 08/06/2016 10:42:19 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.cairone.sdlpocjpa.edm.PaisEdm;

@Entity @Table(name="PAIS")
public class PaisEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @Column(name="pais", unique=true, nullable=false)
	@TableGenerator(name = "GEN_PAIS", table="SEQUENCE_TABLE", pkColumnName="SEQ_NAME", valueColumnName="SEQ_COUNT", pkColumnValue="PAIS_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GEN_PAIS") 
	private Integer id = null;
	
	@Column(name="descripcion", nullable=false, length=50)
	private String descripcion = null;
	
	@Column(name="nacionalidad", length=50)
	private String nacionalidad = null;
	
	@Column(name="prefijo")
	private Integer prefijo = null;
	
	@Column(name="paraiso_fiscal")
	private Boolean paraisoFiscal = null;
	
	@Column(name="no_colabora_ld")
	private Boolean noColaboraLd = null;

    public PaisEntity() {}
    
    public PaisEntity(PaisEdm pais) {
    	this.actualizarCampos(pais);
    }
       
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getNacionalidad() {
        return this.nacionalidad;
    }
    
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    public Integer getPrefijo() {
        return this.prefijo;
    }
    
    public void setPrefijo(Integer prefijo) {
        this.prefijo = prefijo;
    }
    
    public Boolean getParaisoFiscal() {
        return this.paraisoFiscal;
    }
    
    public void setParaisoFiscal(Boolean paraisoFiscal) {
        this.paraisoFiscal = paraisoFiscal;
    }
    
    public Boolean getNoColaboraLd() {
        return this.noColaboraLd;
    }
    
    public void setNoColaboraLd(Boolean noColaboraLd) {
        this.noColaboraLd = noColaboraLd;
    }
    
    public void actualizarCampos(PaisEdm pais) {
    	this.descripcion = pais.getDescripcion() == null ? this.descripcion : pais.getDescripcion().trim().toUpperCase();
    	this.nacionalidad = pais.getNacionalidad() == null ? this.nacionalidad : pais.getNacionalidad().trim().toUpperCase();
    	this.prefijo = pais.getPrefijo() == null ? this.prefijo : pais.getPrefijo();
    	this.paraisoFiscal = pais.getParaisoFiscal() == null ? this.paraisoFiscal : pais.getParaisoFiscal();
    	this.noColaboraLd = pais.getNoColaboraLd() == null ? this.noColaboraLd : pais.getNoColaboraLd();
    }
    
    @Override
    public String toString() {
    	return String.format("%s (%s)", this.descripcion, this.id);
    }
}
