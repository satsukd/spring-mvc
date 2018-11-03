package com.study.shop.security;

import com.study.shop.entity.User;
import com.study.shop.security.entity.Session;
import com.study.shop.service.UserService;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SecurityService {
    private ConcurrentMap<String, Session> sessions = new ConcurrentHashMap();
    private UserService userService;

    public Session auth(String login, String password) {
        User user = userService.getUser(login, password);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            Session session = new Session();
            session.setUser(user);
            session.setToken(token);

            sessions.put(token, session);
            return session;
        }

        return null;
    }

    public Session getSession(String userToken) {
        if (userToken == null) {
            return null;
        }
        return sessions.get(userToken);

    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
