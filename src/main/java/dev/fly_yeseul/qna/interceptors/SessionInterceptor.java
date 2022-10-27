package dev.fly_yeseul.qna.interceptors;

import dev.fly_yeseul.qna.entities.SessionEntity;
import dev.fly_yeseul.qna.entities.member.UserEntity;
import dev.fly_yeseul.qna.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie sessionKeyCookie = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("sk")) {
                    sessionKeyCookie = cookie;
                    break;
                }
            }
        }

        if (sessionKeyCookie != null && sessionKeyCookie.getValue() != null) {
            String sessionKey = sessionKeyCookie.getValue();
            SessionEntity sessionEntity = this.userService.getSession(sessionKey);
            if (sessionEntity != null && sessionEntity.getUserEmail() != null) {
                UserEntity userEntity = this.userService.getUser(sessionEntity.getUserEmail());
                if (userEntity != null && userEntity.getEmail() != null) {
                    this.userService.extendSession(sessionEntity);
                    request.setAttribute("sessionEntity", sessionEntity);
                    request.setAttribute("userEntity", userEntity);
                }
            }
        }

        if(request.getAttribute("userEntity") == null && sessionKeyCookie != null) {
            sessionKeyCookie.setMaxAge(0);
        }

        return true;
    }
}
