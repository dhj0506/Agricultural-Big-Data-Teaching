package cn.edu.seu.market.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/view")
public class JspController {
    @RequestMapping(value = "/hello")
    public String viewHello(){
        return "hello";
    }
}
