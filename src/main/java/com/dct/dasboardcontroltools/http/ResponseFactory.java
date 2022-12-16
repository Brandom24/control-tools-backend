package com.dct.dasboardcontroltools.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseFactory<T> {
	
	private BaseResponse<T> response;
	
	public ResponseFactory() {
		response = new BaseResponse<T>();
	}
	
	public ResponseEntity<?> getResponse(T data, Http2Response code, HttpStatus status) {
		return code.getCode() == Http2Response.OK.getCode()
				? getOkResponse(data, code, status) : getErrorResponse(data, code, status);
	}
	
	public ResponseEntity<?> getResponse(Http2Response code, HttpStatus status) {
		return code.getCode() == Http2Response.OK.getCode()
				? getOkResponse(null, code, status) : getErrorResponse(null, code, status);
	}
	
	public ResponseEntity<?> getOkResponse(T data, Http2Response code, HttpStatus status) {
		response.setCode(code.getCode());
		response.setMessage(code.getMessage());
		response.setData(data);
		
		return new ResponseEntity<>(response, status);
	}
	
	public ResponseEntity<?> getErrorResponse(T data, Http2Response code, HttpStatus status) {
		response.setCode(code.getCode());
		response.setError(code.getMessage());
		response.setData(data);
		
		return new ResponseEntity<>(response, status);
	}

}
