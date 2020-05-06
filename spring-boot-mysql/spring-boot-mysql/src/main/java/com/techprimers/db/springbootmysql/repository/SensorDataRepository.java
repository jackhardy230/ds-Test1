package com.techprimers.db.springbootmysql.repository;

import com.techprimers.db.springbootmysql.model.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {
}
