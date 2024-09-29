package chen.ticket.controller;

import chen.ticket.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 车站信息
 * @author chen
 * 8.5-12
 * 2-6
 */
@RestController
@RequestMapping("station")
public class StationController {
@Autowired
private StationService stationService;
    /**
     * 获取最新站点信息
     */
    @GetMapping("setSiteInfo")
    public String setSiteInfo(@RequestParam("url") String url){
        String result =stationService.setSiteInfo(url);
        return result;
    }


}
