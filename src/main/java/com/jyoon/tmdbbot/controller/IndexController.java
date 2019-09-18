package com.jyoon.tmdbbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jyoon.tmdbbot.session.SessionStorage;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
public class IndexController {

    @Autowired
    SessionStorage sessionStorage;

    @GetMapping("/session/create")
    @ResponseBody
    public String createSession(HttpServletRequest request){
        String sessionId = request.getSession().getId();
        sessionStorage.createSession(sessionId);
        return sessionStorage.getSession(sessionId);
    }
}
