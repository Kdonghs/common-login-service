/*
package com.login.loginAPI.interceptor;

import com.login.loginAPI.controller.sessionConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class sessionCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 세션 획득 후 확인
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object authInfo = session.getAttribute(sessionConst.LOGIN_MEMBER);
            if (authInfo != null) {
                return true;
            }
        }
        //세션이 비어있다면 or authInfo 속성이 없다면, 리다이렉트 후 false 리턴으로 컨트롤러가 실행되지 않도록 함.
        response.sendRedirect(request.getContextPath() + "/");
        return false;
    }
}
*/
