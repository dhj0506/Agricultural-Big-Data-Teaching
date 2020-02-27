package cn.edu.seu.market.admin.controller;


import cn.edu.seu.market.admin.entity.League;
import cn.edu.seu.market.admin.service.ILeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.regex.Pattern;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @since 2020-02-27
 */
@Controller
@RequestMapping("/admin")
public class LeagueController {
    @Autowired
    private ILeagueService leagueService;

    @RequestMapping(value = "/leagueform", method = {RequestMethod.GET})
    public String viewLeagueForm() {
        return "admin/AddLeague";
    }

    @RequestMapping(value = "addleague", method = {RequestMethod.POST})
    public String addLeague(League league, Model model) {
        if (league.getYear() == null || "".equals(league.getYear())) {
            model.addAttribute("error_message", "年份必须填写！请重新添加！");
        } else if (!Pattern.compile("^[-\\+]?[\\d]*$").matcher(league.getYear()).matches()) {
            model.addAttribute("error_message", "年份必须为整数！请重新添加！");
        } else if (Integer.parseInt(league.getYear()) > 2100 || Integer.parseInt(league.getYear()) < 1900) {
            model.addAttribute("error_message", "年份必须在1900~2100！请重新添加！");
        } else if (league.getSeason() == null || "".equals(league.getSeason())) {
            model.addAttribute("error_message", "季节必须选择！请重新添加！");
        } else if (league.getTitle() == null || "".equals(league.getTitle())) {
            model.addAttribute("error_message", "标题必须填写！请重新添加！");
        } else {
            leagueService.save(league);
            model.addAttribute("league", league);
            model.addAttribute("status", "success");
        }
        return "admin/AddLeagueSucc";
    }
}
