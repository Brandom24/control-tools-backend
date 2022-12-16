package com.dct.dasboardcontroltools.reports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dct.dasboardcontroltools.http.Http2Response;
import com.dct.dasboardcontroltools.http.ResponseFactory;
import com.dct.dasboardcontroltools.reports.service.ReportService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/dct/api/reports")
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/obtaindByCode")
    public ResponseEntity<?> getReport(@RequestParam(value = "code", required = true) String code) {
        log.info("---* getReport by code:{} *---", code);
        try {
//            var auth = SecurityContextHolder.getContext().getAuthentication();
//            log.info("Datos del usuario: {}", auth.getPrincipal());
//            log.info("Datos de los permisos: {}", auth.getAuthorities());
//            log.info("Esta autenticado: {}", auth.isAuthenticated());
            String report = reportService.obtainReport();
            
            return new ResponseFactory<>().getResponse(report, Http2Response.OK, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error getMensajePublic");
            e.printStackTrace();
            return new ResponseFactory<>().getResponse(Http2Response.AP_UNEXPECTED_ERROR, HttpStatus.OK);
        }
    }

}
