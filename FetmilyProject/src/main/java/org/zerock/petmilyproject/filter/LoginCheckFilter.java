package org.zerock.petmilyproject.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.PatternMatchUtils;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

import static org.springframework.util.ObjectUtils.isEmpty;

@Log4j2
@RequiredArgsConstructor
public class LoginCheckFilter implements Filter {
    private static final String[] blackList = {"/board/register", "/mypage/*", "/cart/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpRequest.getSession(true);

        try{
            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (isEmpty(session) || isEmpty(session.getAttribute("memberId"))) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    httpResponse.sendRedirect("/member/login");
                    return; // 여기가 중요, 미인증 사용자는 다음으로 진행하지 않고 끝!
                }
            }

            chain.doFilter(request,response);
        }catch (Exception e){
            throw e;
        }
        
    }

    private boolean isLoginCheckPath(String requestURI) {
//        return false;
        return PatternMatchUtils.simpleMatch(blackList, requestURI);
    }

}
