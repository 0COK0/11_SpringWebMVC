package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class StopWatchInterceptor implements HandlerInterceptor {

    private final MenuService menuService;

    public StopWatchInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    // 컨트롤 o 해서 오버라이딩 받던 알트 인서트로 받던 하면됨 HandlerInterceptor 내장되어 있는 클래스들을
    /* 전처리 메소드 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandle 호출함...");

        long startTime = System.currentTimeMillis();

        //  인자에 있는 리퀘스트를 이용
        request.setAttribute("startTime", startTime);

        /* 트루이면 컨트롤러를 이어서 호출한다. false이면 핸들러 메소드를 호출하지 않는다.*/
        return true;
    }

    /* 후처리 메소드 */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();

        //removeAttribute 으로 동작했으면 startTime을 삭제해줌
        request.removeAttribute("startTime");

        // interval 키로 endTime - startTime 꺼내줌
        modelAndView.addObject("interval", endTime - startTime);

        System.out.println("postHandle 호출함...");

    }

    /* 요청을 했을 때 성공 실패 상관없이 무조건 동작되는데 마지막에 호출하는 메소드*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


        System.out.println("afterCompletion 호출함...");

        menuService.method();
    }
}
