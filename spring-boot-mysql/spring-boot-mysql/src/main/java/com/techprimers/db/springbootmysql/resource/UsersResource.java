package com.techprimers.db.springbootmysql.resource;

import com.techprimers.db.springbootmysql.model.Users;
import com.techprimers.db.springbootmysql.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/users")
public class UsersResource {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping(value = "/all")
    public List<Users> getAll(){
        return usersRepository.findAll();
    }

    @PostMapping(value = "/load")
    public List<Users> persist(@RequestBody final Users user){
        usersRepository.save(user);
        return usersRepository.findAll();
    }

    @PostMapping(value = "/auth")
    public Users authUSer(@RequestBody final Users user) throws  Exception{
        List<Users> us =usersRepository.findAll();
        for(Users u:us){
            if(u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword())){
                return u;
            }
        }
        throw new Exception("User not found");
    }

}
