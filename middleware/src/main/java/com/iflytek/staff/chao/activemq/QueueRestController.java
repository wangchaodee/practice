package com.iflytek.staff.chao.activemq;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chaowang5 on 2017/12/29.
 */
@RestController
@RequestMapping("/activemq")
public class QueueRestController {

    @Autowired
    private OrderCancelProducer orderCancelProducer;


    @ApiOperation(value = "发送消息", notes = "发送activemq消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg", value = "消息内容", required = true, paramType = "query", dataType = "String")
    })
    @RequestMapping(value = "send", method = RequestMethod.GET)
    public String send(HttpServletRequest request, String msg) {

        msg = StringUtils.isEmpty(msg) ? "empty msg " : msg;

        try {
            orderCancelProducer.send(msg);
        } catch (Exception e) {

        }
        return "Activemq has sent OK.";
    }

    @ApiOperation(value = "发送消息", notes = "发送activemq延时消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "msg", value = "消息内容", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "interval", value = "延时时长", required = true, paramType = "query", dataType = "Long")
    })
    @RequestMapping(value = "delaySend", method = RequestMethod.GET)
    public String delaySend(HttpServletRequest request, String msg, long interval) {
        msg = StringUtils.isEmpty(msg) ? "empty msg " : msg;

        try {
            orderCancelProducer.delaySend(msg, interval);
        } catch (Exception e) {

        }
        return "Activemq has sent OK.";
    }

}
