package com.piyush.controller;

import com.piyush.config.JwtProvider;
import com.piyush.model.TwoFactorOTP;
import com.piyush.model.User;
import com.piyush.repository.UserRepository;
import com.piyush.response.AuthResponse;
import com.piyush.service.CustomeUserDetailsService;
import com.piyush.service.EmailService;
import com.piyush.service.TwoFactorOtpService;
import com.piyush.service.WatchListService;
import com.piyush.utils.OtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomeUserDetailsService customeUserDetailsService;

    @Autowired
    private TwoFactorOtpService twoFactorOtpService;

    @Autowired
    private WatchListService watchListService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception {


        User isEmailExist=userRepository.findByEmail(user.getEmail());

        if(isEmailExist!= null){
            throw new Exception("Email already exist");
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setFullName(user.getFullName());

        User savedUser = userRepository.save(newUser);

        watchListService.createWatchList(savedUser);

        Authentication auth=new UsernamePasswordAuthenticationToken(
                user.getEmail()
                ,user.getPassword()

        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt= JwtProvider.generateToken(auth);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("register success");





        return new ResponseEntity<>(res, HttpStatus.CREATED);



    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception {

        String userName= user.getEmail();
        String password = user.getPassword();




        Authentication auth=authenticate(userName , password);

        
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt= JwtProvider.generateToken(auth);

        User authuser = userRepository.findByEmail(userName);

        if(user.getTwoFactorAuth().isEnabled()){
            AuthResponse res  = new AuthResponse();
            res.setMessage("Two Factor Enabled");
            res.setTwoFactorAuthEnabled(true);
            String otp = OtpUtils.generateOTP();

            TwoFactorOTP oldTwoFactorOTP = twoFactorOtpService.findByUser(authuser.getId());
            if (oldTwoFactorOTP!=null){
                twoFactorOtpService.deleteTwoFactorOtp(oldTwoFactorOTP);

            }

            TwoFactorOTP newTwoFactorOTP=twoFactorOtpService.createTwoFactorOtp(authuser,otp,jwt);

            emailService.sendVerificationOtpEmail(userName,otp);
            res.setSession(newTwoFactorOTP.getId());
            return  new ResponseEntity<>(res,HttpStatus.ACCEPTED);



        }

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("login success");





        return new ResponseEntity<>(res, HttpStatus.CREATED);



    }

    private Authentication authenticate(String userName, String password) {
        UserDetails userDetails = customeUserDetailsService.loadUserByUsername(userName);

        if(userDetails==null){
            throw new BadCredentialsException("invalid username ");
        }

        if(!password.equals(userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());


    }

    @PostMapping("/two-factor/otp/{otp}")
    public ResponseEntity<AuthResponse> verifySigingOtp(@PathVariable String otp,@RequestParam String id) throws Exception {
        TwoFactorOTP twoFactorOTP=twoFactorOtpService.findById(id);

        if(twoFactorOtpService.verifyTwoFactorOtp(twoFactorOTP,otp)){
            AuthResponse res = new AuthResponse();
            res.setMessage("Two factor authentication verified");
            res.setTwoFactorAuthEnabled(true);
            res.setJwt(twoFactorOTP.getJwt());
            return new ResponseEntity<>(res,HttpStatus.OK);


        }



        throw new Exception(("Invalid Otp"));
    }
}
