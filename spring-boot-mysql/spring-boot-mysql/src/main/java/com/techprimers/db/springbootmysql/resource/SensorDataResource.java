package com.techprimers.db.springbootmysql.resource;

import com.techprimers.db.springbootmysql.NotifyUser.SendEmail;
import com.techprimers.db.springbootmysql.model.SensorData;
import com.techprimers.db.springbootmysql.model.Users;
import com.techprimers.db.springbootmysql.repository.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/sensordata")
public class SensorDataResource {

    @Autowired
    SensorDataRepository sensorDataRepository;

    @GetMapping(value = "/all")
    public List<SensorData> getAll(){
        return sensorDataRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<SensorData> persist(@RequestBody final SensorData sensorData){


        if(sensorData.getCo2Level() >5 ){
            SendEmail.sendEmailtoUser();
        }
        if(sensorData.getSmokeLevel() > 5 ){
            SendEmail.sendEmailtoUser();
        }


        sensorDataRepository.save(sensorData);
        return sensorDataRepository.findAll();
    }

}
