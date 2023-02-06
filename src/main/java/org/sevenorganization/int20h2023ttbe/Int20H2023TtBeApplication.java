package org.sevenorganization.int20h2023ttbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableFeignClients
@SpringBootApplication
public class Int20H2023TtBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Int20H2023TtBeApplication.class, args);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(
            @RequestParam(name = "name", required = false, defaultValue = "world") String name
    ) {
        return ResponseEntity.ok("hello " + name);
    }
}
