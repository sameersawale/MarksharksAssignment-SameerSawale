package com.example.MarksharksAssignment_SameerSawale.Controller;

import com.example.MarksharksAssignment_SameerSawale.DTOs.LoginReq;
import com.example.MarksharksAssignment_SameerSawale.DTOs.LoginRes;
import com.example.MarksharksAssignment_SameerSawale.DTOs.UserReqDTO;
import com.example.MarksharksAssignment_SameerSawale.DTOs.UserResDTO;
import com.example.MarksharksAssignment_SameerSawale.JWT.JWTHelper;
import com.example.MarksharksAssignment_SameerSawale.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;


    @Autowired
    JWTHelper jwtHelper;

    @Autowired
    AuthenticationManager authenticationManager;

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserReqDTO userReqDTO){
        try {
            String result= userService.registerUser(userReqDTO);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch (Exception e) {
            String result="please check user details";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/fetch/{username}")
    public ResponseEntity<?> fetchUser(@PathVariable("username") String username){
        try {
            UserResDTO userResDTO=userService.fetchUser(username);
            return new ResponseEntity<>(userResDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq){
        this.doAuthenticate(loginReq.getUsername(), loginReq.getPassword());
        UserDetails userDetails= userDetailsService.loadUserByUsername(loginReq.getUsername());
        String token=this.jwtHelper.generateToken(userDetails);

        LoginRes response=LoginRes.builder()
                .token(token)
                .username(userDetails.getUsername())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
