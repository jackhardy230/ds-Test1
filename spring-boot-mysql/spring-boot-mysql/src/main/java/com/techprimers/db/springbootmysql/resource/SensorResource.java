package com.techprimers.db.springbootmysql.resource;

import com.techprimers.db.springbootmysql.model.Sensor;
import com.techprimers.db.springbootmysql.model.SensorData;
import com.techprimers.db.springbootmysql.repository.SensorDataRepository;
import com.techprimers.db.springbootmysql.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/sensor")
public class SensorResource {

    @Autowired
    SensorRepository sensorRepository;

    @GetMapping(value = "/all")
    public List<Sensor> getAll(){
        return sensorRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<Sensor> persist(@RequestBody final Sensor sensor){

        System.out.println(sensor.getFloorNo());
        System.out.println(sensor.isActive());

        sensorRepository.save(sensor);
        return sensorRepository.findAll();
    }

    @PostMapping(value = "/all")
    public List<Sensor> getAll(@RequestBody final Sensor sensor){
        return sensorRepository.findAll();
    }


    @DeleteMapping(value ="/delete/{sensorid}")
    void deleteEmployee(@PathVariable String sensorid) {
        sensorRepository.deleteById(sensorid);
    }

    @GetMapping(value ="/getsensor/{id}")
    Sensor one(@PathVariable String id) throws Exception {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new Exception(String.valueOf(id)));
    }

}
