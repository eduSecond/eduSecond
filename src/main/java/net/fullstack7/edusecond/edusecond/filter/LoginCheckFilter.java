package net.fullstack7.edusecond.edusecond.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import net.fullstack7.edusecond.edusecond.dto.member.MemberLoginDTO;
import net.fullstack7.edusecond.edusecond.util.JSFunc;

@Component
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
        
        String requestURI = httpRequest.getRequestURI();
        
        // 로그인 여부 확인
        MemberLoginDTO loginMember = (MemberLoginDTO) session.getAttribute("memberInfo");
        
        if (loginMember == null) {
            // 로그인되지 않은 경우
            if (requestURI.startsWith("/es/")) {
                // /es/* 경로는 메인으로 리다이렉트
                httpResponse.sendRedirect("/");
                return;
            } else if (requestURI.contains("/product/regist") || 
                      requestURI.contains("/notice/view")) {
                // product/regist, notice/view는 이전 페이지로
                httpResponse.setContentType("text/html; charset=UTF-8");
                JSFunc.alertBack("로그인이 필요한 서비스입니다.", httpResponse);
                return;
            }
        }
        
        chain.doFilter(request, response);
    }
}
