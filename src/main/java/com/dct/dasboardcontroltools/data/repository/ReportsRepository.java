package com.dct.dasboardcontroltools.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dct.dasboardcontroltools.data.dao.Reports;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, Long> {

}
