package com.dct.dasboardcontroltools.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class FileUtils {
	
	public String FileToBase64 (File file) {
		try {
	        byte[] fileContent = Files.readAllBytes(file.toPath());
	        return Base64.getEncoder().encodeToString(fileContent);
	    } catch (IOException e) {
	        throw new IllegalStateException("could not read file " + file, e);
	    }
	}

}
