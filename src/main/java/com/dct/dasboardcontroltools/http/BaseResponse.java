package com.dct.dasboardcontroltools.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BaseResponse<T> {

	private int code;
	private String error;
	private String message;
	private T data;
	
}
