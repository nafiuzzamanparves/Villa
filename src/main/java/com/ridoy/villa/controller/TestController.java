package com.ridoy.villa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping
    public String isRunning() {
        return decryptMessage("1 3 4 2");
    }

    private String decryptMessage(String msg) {
        StringBuilder decryptedMsg = new StringBuilder();
        String[] msgPart = msg.trim().split(" ");
        int l = Integer.parseInt(msgPart[msgPart.length - 1].trim());

        for (int i = 0; i < msgPart.length - 1; i++) {
            log.info(String.valueOf(Integer.parseInt(msgPart[i]) / l));
            decryptedMsg.append((char) (Integer.parseInt(msgPart[i]) / l));
        }
        return decryptedMsg.toString();
    }
}