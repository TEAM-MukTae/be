package io.github.muktae.be_codebase.domain.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/api/v1/test")
    public String test() {
        log.info("testController");
        return "test test test test";
    }

}
