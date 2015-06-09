package org.hyness.basic.security.demo.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {
    @RequestMapping(method = GET)
    public Map<String, String> hello() {
        return Collections.singletonMap("hello", "world");
    }
}
