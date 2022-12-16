package com.dct.dasboardcontroltools.http;

public enum Http2Response {

	// Error token
	INVALID_CREDENTIALS(-9701, "Credenciales invalidas"),
	EXPIRES_TOKEN(-9702, "Token caducado"),
	
	// Error Data Base
	DB_USER_NOT_FOUND(-9801, "Usuario no encontrado"),
	
	// Error: Application
	AP_UNEXPECTED_ERROR(-9901, "Error inesperado"),
	
	// Successful
	OK(-9999, "Operación exitosa");
	
	private int code;
	private String message;
	
	private Http2Response(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
