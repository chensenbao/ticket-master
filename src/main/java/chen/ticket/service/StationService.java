package chen.ticket.service;

import chen.ticket.dao.StationDao;
import chen.ticket.entity.StationEntity;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chen
 * @description 车站信息
 */
@Service
public class StationService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StationDao stationDao;

    public String setSiteInfo(String url) {
        if (StrUtil.isBlank(url)) {
            url = "https://kyfw.12306.cn/otn/resources/js/framework/station_name.js";
        }
        String response = HttpUtil.get(url);
        if (response == null) {
            logger.warn("获取站点信息出错");
            return "获取站点信息出错";
        }
        response = StrUtil.replace(response, "var station_names ='", "");
        response = StrUtil.replace(response, "';", "");
//        原始字符串解析为数组
        List<String> sourceList = StrUtil.split(response, "@", -1, true, true);
        stationDao.delete();
        for (String item : sourceList) {
            List<String> stationInfo = StrUtil.split(item, '|');
            if (11!=stationInfo.size()){
                continue;
            }
            StationEntity stationEntity = new StationEntity();

            stationEntity.setStationCode(stationInfo.get(2));
            stationEntity.setStationCity(stationInfo.get(7));
            stationEntity.setStationName(stationInfo.get(1));
            stationEntity.setStationInfo(item);
            stationEntity.setStationPinyin(stationInfo.get(3));
            stationEntity.setStationPinyinSimple(stationInfo.get(4));
            stationDao.insert(stationEntity);
        }
        return "更新站点信息成功";
    }
}
