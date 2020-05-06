package com.techprimers.db.springbootmysql.model;

import javax.persistence.*;

@Entity
public class SensorData {

    @Id
    @Column(name = "sensordataid")
    private String sensordataid;

    @ManyToOne
    private Sensor sensor;

    @Column(name = "smokeLevel")
    private int smokeLevel;

    @Column(name = "co2Level")
    private int co2Level;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    public SensorData() {
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public int getSmokeLevel() {
        return smokeLevel;
    }

    public void setSmokeLevel(int smokeLevel) {
        this.smokeLevel = smokeLevel;
    }

    public int getCo2Level() {
        return co2Level;
    }

    public void setCo2Level(int co2Level) {
        this.co2Level = co2Level;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSensordataid() {
        return sensordataid;
    }

    public void setSensordataid(String sensordataid) {
        this.sensordataid = sensordataid;
    }
}
