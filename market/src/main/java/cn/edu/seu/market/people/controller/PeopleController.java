package cn.edu.seu.market.people.controller;


import cn.edu.seu.market.people.entity.People;
import cn.edu.seu.market.people.service.IPeopleService;
import cn.edu.seu.market.visual.entity.Pie;
import cn.edu.seu.market.visual.service.IPieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Dean
 * @since 2020-02-28
 */
@RestController
@RequestMapping("/people")
@CrossOrigin
public class PeopleController {
    @Autowired
    private IPeopleService peopleService;

    @RequestMapping(value = "/data", method = {RequestMethod.GET})
    public List<People> getPieData(){
        return peopleService.list();
    }

}
