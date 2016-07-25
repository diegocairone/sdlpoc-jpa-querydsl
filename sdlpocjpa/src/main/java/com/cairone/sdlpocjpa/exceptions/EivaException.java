package com.cairone.sdlpocjpa.exceptions;

public class EivaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public static final int ENTITY_NOT_FOUND = 1;
	
	private Integer codigo = null;
	
	public EivaException() {}
	
	public EivaException(String message) {
		super(message);
	}
	
	public EivaException(Integer codigo, String message) {
		super(message);
		this.codigo = codigo;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

	public Integer getCodigo() {
		return codigo;
	}
}
