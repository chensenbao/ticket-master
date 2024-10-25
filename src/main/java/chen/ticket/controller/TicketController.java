package chen.ticket.controller;


import chen.ticket.request.TicketRequest;
import chen.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.Ticket;

/**
 * @author sen
 * 车票控制器
 */
@RestController
@RequestMapping("ticket")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("getTicketInfo")
    public String getTicketInfo(@RequestBody TicketRequest ticketRequest) {
        ticketService.getTicketInfo(ticketRequest);
        return "成功获取车票信息";
    }
}
