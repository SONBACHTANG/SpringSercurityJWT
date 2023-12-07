package com.controll;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController {

    @Secured("ROLE_ADMIN")
    @GetMapping("/admin/hello")
    public String helloAdmin() {
        return "hello admin";
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/user/hello")
    public String helloUser() {
        return "hello user";
    }
}
