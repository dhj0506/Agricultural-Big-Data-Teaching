package cn.edu.seu.market.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "/view")
@SessionAttributes({"name"})
public class JspController {

    @RequestMapping(value = "/hello")
    public String viewHello(Model model){
        model.addAttribute("name", "Loretta");
        List<String> names = new LinkedList<>();
        names.add("Maria");
        names.add("Nora");
        names.add("Johne");
        model.addAttribute("names", names);
        return "hello";
    }

}
