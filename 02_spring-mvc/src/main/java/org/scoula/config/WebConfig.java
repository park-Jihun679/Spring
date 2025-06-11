package org.scoula.config;

import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;

/*
Web 설정
-> 기존 자바 웹 어플리케이션의 핵심 설정 파일
-> 서블릿 컨테이너(톰켓)가 웹 어플리케이션을 실행할 때 가장 먼저 읽는 파일
 */
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { ServletConfig.class };
    }

    // DispatcherServlet이 담당할 Url 매핑 패턴
    // "/" : 모든 요청에 대한 매핑
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    // http의 body로 전송되는 post 방식의 데이터는
    // 프론트 컨트롤러가 받기 전에 미리 utf-8로 인코딩을 먼저 한 후 받게 설정함
    // -> 한글 안깨지게
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }

    /*
    DispatcherServlet이 매핑되지 않은 요청을 받았을 때
    기본적으로 404 NOT FOUND를 클라이언트에게 반환함
    이 설정을 추가하면 예외(NoHandlerFoundException)을 던지도록 강제
     */
    @Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setInitParameter("ThrowExceptionIfNoHandlerFound", "true");
    }
}