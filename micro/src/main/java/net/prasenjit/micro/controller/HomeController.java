/*
 * Copyright (c) 2016.
 * Prasenjit Purohit
 * prasenjit.net
 */

package net.prasenjit.micro.controller;

import net.prasenjit.micro.domain.AutoCloseA;
import net.prasenjit.micro.domain.CloneClass;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * Created by pp03582 on 2/16/2016.
 */
@RestController
public class HomeController {

    @RequestMapping("/api")
    public Map<String, String> home() throws IOException {
        return Collections.singletonMap("samplekey", "sample value");
    }

    @RequestMapping("/clone")
    public CloneClass cloneClass() throws CloneNotSupportedException {
        CloneClass cc = new CloneClass("my name");
        return cc.clone();
    }

    @RequestMapping("/header")
    public String forwordHeader(@RequestHeader HttpHeaders httpHeaders, HttpServletRequest request) {
        return "addr=" + request.getRemoteAddr()
                + "\nr-host=" + request.getRemoteHost()
                + "\nr-port=" + request.getRemotePort()
                + "\nsecure=" + request.isSecure();
    }

    @RequestMapping("/close")
    public String autoClose() throws IOException {
        try (AutoCloseA a = new AutoCloseA("first");
             AutoCloseA b = new AutoCloseA("second");
             AutoCloseA c = new AutoCloseA("third");) {
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "done...";
    }
}
