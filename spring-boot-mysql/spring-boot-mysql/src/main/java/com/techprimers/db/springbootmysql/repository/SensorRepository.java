package com.techprimers.db.springbootmysql.repository;

import com.techprimers.db.springbootmysql.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, String> {
}
