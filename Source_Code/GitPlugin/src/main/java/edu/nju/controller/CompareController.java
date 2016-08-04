package edu.nju.controller;

import edu.nju.service.CompareService;
import edu.nju.vo.PeopleOfRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Compare data of a repository
 * @author cuihao
 */
@Controller
@RequestMapping("/compare")
public class CompareController {

    @Resource
    private CompareService compareService;

    /**
     * Compare wacth number
     * @param num number of watchers
     * @return the number is higher than {result}% of repositories
     */
    @RequestMapping(value = "/watch", method = RequestMethod.GET)
    @ResponseBody
    public double compareWatch(@RequestParam("num")String num) {
        return compareService.compareWatch(Long.valueOf(num));
    }

    /**
     * Compare star number
     * @param num number of stars
     * @return the number is higher than {result}% of repositories
     */
    @RequestMapping(value = "/star", method = RequestMethod.GET)
    @ResponseBody
    public double compareStar(@RequestParam("num")String num) {
        return compareService.compareStar(Long.valueOf(num));
    }

    /**
     * Compare fork number
     * @param num number of fork
     * @return the number is higher than {result}% of repositories
     */
    @RequestMapping(value = "/fork", method = RequestMethod.GET)
    @ResponseBody
    public double compareFork(@RequestParam("num")String num) {
        return compareService.compareFork(Long.valueOf(num));
    }

    /**
     * Complare contributors of the repository
     * @param owner owner name of the repository
     * @param name name of the repository
     * @return compare info {@link PeopleOfRepo}
     */
    @RequestMapping(value = "/people", method = RequestMethod.GET)
    @ResponseBody
    public double comparePeople(@RequestParam("owner")String owner, @RequestParam("name")String name) {
        return compareService.comparePeople(owner,name);
    }

}
