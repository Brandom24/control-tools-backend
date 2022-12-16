package com.dct.dasboardcontroltools.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dct.dasboardcontroltools.data.dao.Reports;
import com.dct.dasboardcontroltools.data.repository.ReportsRepository;

@Service
public class ReportsService {
	
	@Autowired
	private ReportsRepository reportsRepository;
	
	public Reports getReports() {
		Optional<Reports> reports = reportsRepository.findById(1L);
		if(reports.isPresent()) {
			return reports.get();
		} else {
			return null;
		}
	}
	
	public List<Reports> getAllReports() {
		List<Reports> reports = reportsRepository.findAll();
		if(reports.isEmpty()) {
			return null;
		} else {
			return reports;
		}
	}

}
