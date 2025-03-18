package springsecurity.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
@SecurityRequirement(name = "bearerAuth")
public class TestController {
    @GetMapping("/")
    private String getHello() {
        return "Say Hello";
    }
}
