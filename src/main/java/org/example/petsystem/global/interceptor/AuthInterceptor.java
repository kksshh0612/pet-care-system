package org.example.petsystem.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.petsystem.domain.exception.CustomException;
import org.example.petsystem.domain.exception.ErrorCode;
import org.example.petsystem.global.annotation.Auth;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // preflight 요청 넘기기
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        log.info("[AuthInterceptor / preHandle] 요청 Method : {}, URI: {}", request.getMethod(), request.getRequestURI());

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if(!handlerMethod.getMethod().isAnnotationPresent(Auth.class)) {
            return true;
        }

        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        HttpSession session = request.getSession();
        Long memberId = (Long) session.getAttribute("memberId");
        String memberRole = (String) session.getAttribute("memberRole");

        if(memberId == null) throw new CustomException(ErrorCode.UNAUTHORIZED);
        if(!memberRole.equals(auth.role().toString())) throw new CustomException(ErrorCode.FORBIDDEN);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
