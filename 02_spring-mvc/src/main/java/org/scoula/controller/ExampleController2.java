package org.scoula.controller;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.scoula.dto.LoginRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
public class ExampleController2 {

    /*
    @RequestParam
    @PathVariable
    @ModelAttribute
     */

    // 컨트롤러 메서드가 void 형태면
    // 요청 url을 기준으로 뷰 이름을 추론하여 해당 뷰를 찾는다
    @GetMapping("/index")
    public void get() {
        log.info("get ============> 호출됨");
    }

    /*
    @ModelAttribute
    - 클라이언트의 요청 파라미터를 자바 객체에 바인딩하는 역할
    - 객체의 Setter를 통해 값이 자동 주입
     */
    @GetMapping("/example01")
    public String example01(@ModelAttribute LoginRequestDTO dto, Model model) {

        log.info("example id ==============> {}", dto.getId());
        log.info("example password ==============> {}", dto.getPassword());

        model.addAttribute("dto", dto);
        return "page/dtoMappingResult";
    }

    /*
    @RequestParam
    - 클라이언트의 요청 파라미터를 개별 변수에 바인딩할 때 사용
    - 이름이 일치하는 요청 파라미터를 개별 변수에 바인딩
    - 기본적으로 해당 파라미터가 없으면 예외가 발생
     */

    @GetMapping("/example02")
    public String example02(@RequestParam(name = "id", defaultValue = "defaultUser") String id,
        @RequestParam(name = "password", required = true) String password, Model model) {

        log.info("example id ==============> {}", id);
        log.info("example password ==============> {}", password);

        model.addAttribute("dto", new LoginRequestDTO(id, password));
        return "page/dtoMappingResult";
    }

    @GetMapping("/example03")
    public String example03(@RequestParam(name = "names") List<String> names, Model model) {

        log.info("example id ==============> {}", names);

        model.addAttribute("message", names);
        return "page/mappingResult";
    }

    /*
    @PathVariable
    - URL 경로에 포함된 값을 추출해서 변수에 바인딩 할 때 사용
    - RESTful 스타일 url에서 많이 사용됨
    - url의 중괄호로 표시한 경로변수값을 매서드 매개변수에 바인딩
     */
    @GetMapping("/example04/{id}")
    public String example04(@PathVariable("id") int id, Model model) {

        log.info("example id ==============> {}", id);

        model.addAttribute("dto", id);
        return "page/mappingResult";
    }
}
