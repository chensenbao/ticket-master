package chen.ticket.request;

import lombok.Data;

/**
 * @description 车票查询条件
 * @author sen
 */
@Data
public class TicketRequest {
    /**
     * 日期 yyyy-MM-dd
     */
    private String trainDate;
    /**
     * 开始时间 HH:mm
     */
    private String trainStartTime;
    /**
     * 结束时间 HH:mm
     */
    private String trainEndTime;
    /**
     * 起点名称
     */
    private String fromStationName;
    /**
     * 起点编号
     */
    private String fromStationCode;
    /**
     * 终点名称
     */
    private String toStationName;
    /**
     * 终点编号
     */
    private String toStationCode;
    /**
     * 车票类型：ADULT-成人
     */
    private String purposeCodes;
}
