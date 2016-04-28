package nordichack.pcmapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestCspController {

    @RequestMapping("/js")
    public String test(ModelAndView modelAndView) {

        return "testcsp";
    }

}