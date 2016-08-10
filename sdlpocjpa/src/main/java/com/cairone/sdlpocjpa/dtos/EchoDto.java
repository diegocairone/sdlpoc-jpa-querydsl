package com.cairone.sdlpocjpa.dtos;

public class EchoDto {

	private String usuario = null;
	private Long timestamp = null;
	
	public EchoDto() {}

	public EchoDto(String usuario, Long timestamp) {
		super();
		this.usuario = usuario;
		this.timestamp = timestamp;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
