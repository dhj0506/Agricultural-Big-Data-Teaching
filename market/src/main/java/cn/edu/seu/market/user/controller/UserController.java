package cn.edu.seu.market.user.controller;


import cn.edu.seu.market.user.entity.User;
import cn.edu.seu.market.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @since 2020-02-27
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/{userid}", method = {RequestMethod.GET})
    public User getUserData(@PathVariable String userid) {
        return userService.getById(userid);
    }

}
