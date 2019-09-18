package com.jyoon.tmdbbot.session;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SessionStorage {
    Map<String, String> botSessions;

    public SessionStorage(){
        botSessions = new HashMap<>();
    }

    public void createSession(String sessionId){
        botSessions.put(sessionId, "your session Id is: "+sessionId);
    }

    public void removeSession(String sessionId){
        botSessions.remove(sessionId);
    }

    public String getSession(String sessionId){
        return botSessions.get(sessionId);
    }
}
