package com.cairone.sdlpocjpa.enums;

public enum MonedaCotizacionClaseEnum {
	CONTABLE(1), VENTA(2), COMPRA(3);
	
	private final int valor;
	
	private MonedaCotizacionClaseEnum(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
