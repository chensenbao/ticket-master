package chen.ticket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 车站信息
 */
@RestController
@RequestMapping("station")
public class StationController {

    /**
     * 获取最新站点信息
     */
    @GetMapping("getSiteInfo")
    public String getSiteInfo(@RequestParam("url") String url){
        return "请求成功";
    }


}
