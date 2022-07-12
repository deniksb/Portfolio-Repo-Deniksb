package com.servfinal.servfinal.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.servfinal.servfinal.model.User;
import com.servfinal.servfinal.repository.UserRepository;
import com.servfinal.servfinal.service.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.servfinal.servfinal.service.JWTTokenDecode.getEmailFromToken;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordHash passwordHash;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/currentUser/get/{token}")
    public User getCurrentUser(@PathVariable("token") String token){
        String email = getEmailFromToken(token);
        User user = userRepository.findUserByEmail(email).get();

        return user;

    }

    @GetMapping("user/login/{email}&{password}")
    public String login(@PathVariable("email") String email, @PathVariable("password")String password){
        String encodedPass = passwordHash.doHashing(password);
        System.out.println(encodedPass);
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        User user = userRepository.findUserByEmailAndPassword(email,encodedPass);
        if(user!=null){
            String access_token = JWT.create()
                    .withSubject(user.getEmail())
                    .withExpiresAt(new Date(System.currentTimeMillis() +  100 * 60 * 1000))
                    .withClaim("role",user.getRole()).sign(algorithm);

            System.out.println(getEmailFromToken(access_token));


            return access_token;
        }
        else{
            return "not found";
        }

    }

    @PostMapping("user/register")
    public User register(@RequestBody User user){
        user.setPassword(passwordHash.doHashing(user.getPassword()));
        return userRepository.save(user);
    }

    @GetMapping("user/findById/{id}")
    public User findUserById(@PathVariable("id") long id){
        return userRepository.findById(id).get();
    }


}
