package com.controll;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.jwt.JwtService;
import com.response.JwtResponse;
import com.service.UserService;

@RestController
@RequestMapping("")
public class AuthenController {
	  private static final Logger logger = LoggerFactory.getLogger(AuthenController.class);
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token  = jwtService.generateToken(authentication);
            return ResponseEntity.ok().body(new JwtResponse(user.getUsername(),token));

        } catch (Exception exception){
        	  logger.error("Authen faile",exception);
        }
        return ResponseEntity.ok().body("Tài khoản hoặc mật khẩu không đúng");
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        try {
            if(userService.findUserByUsername(user.getUsername()) != null) {
                return "tên này đã tồn tại hãy chọn tên khác";
            }
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.createAccount(user);
            return "đăng kí thành công";

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return "đăng kí gặp lỗi";
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK).onLogoutSuccess(request, response, authentication);
            SecurityContextHolder.clearContext(); 
            return ResponseEntity.ok("Đăng xuất thành công");
        } else {
            return ResponseEntity.status(401).body("Không xác thực: Không có người dùng để đăng xuất");
        }
    }
}
