package com.example.scheduledevelop.users.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    //로그인이 된 상태인지 확인하는 필터.

    private static final String[] WHITE_LIST = {"/", "/login", "/users/signup"};
    //회원가입과 로그인, 로그아웃 + 기존 기능들은 인증을 하지 않는다.

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실행");

        if (!isWhiteList(requestURI)) {//filter실행 여부를 확인한다. 만약 WhiteList에 없는 URL이라면 인증이 필요하므로 filter 실행.

            HttpSession session = httpRequest.getSession(false);//session을 가져온다. false: 기존 세션이 존재하지 않다면 null을 입력.

            log.info("session.getId()={}", session);
// || session.getAttribute("세션값") == null
            if (session == null) {
                throw new RuntimeException("로그인 해주세요.");
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean isWhiteList(String requestURI) {//인증해야할 URL 체크 메서드

        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
        //포함해서 인증할 필요가 없으면 true,
        //포함하지 않아 인증해야한다면 false반환
    }
}
