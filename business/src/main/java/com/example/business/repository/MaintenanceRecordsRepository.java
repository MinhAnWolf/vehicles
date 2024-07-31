package com.example.business.repository;

import com.example.business.entity.MaintenanceRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRecordsRepository extends JpaRepository<MaintenanceRecords, Long> {
}
