package cn.edu.seu.market.visual.controller;


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
 * @since 2020-02-27
 */
@RestController
@RequestMapping("/visual")
@CrossOrigin
public class PieController {

    @Autowired
    private IPieService pieService;

    @RequestMapping(value = "/pie", method = {RequestMethod.GET})
    public List<Pie> getPieData(){
        return pieService.list();
    }

}