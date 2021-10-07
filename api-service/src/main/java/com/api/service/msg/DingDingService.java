package com.api.service.msg;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.domain.DingMsg.DingBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by 李显琪 on 2021/10/7.
 */
@Component
public class DingDingService {

    @Autowired
    private RestTemplate restTemplate;

    private String dingUrl = "https://oapi.dingtalk.com/robot/send?access_token=9b25a7a210cc454217a3ea3b31644eb0628d2a53a6dc8f550285dda1dd587320";

    public void sendDingMsg(int dayNum, String name) {
        JSONObject param = getMsg(dayNum, name);
        restTemplate.postForEntity(dingUrl, param, String.class);
    }

    private JSONObject getMsg(int dayNum, String name) {
        DingBody msg = new DingBody();
        String content = "警告：" + name + " 以达到 " + dayNum + " 日线 ";
        msg.setMsgtype("text");
        msg.getText().setContent(content);
        String jsonString = JSON.toJSONString(msg);
        return JSONObject.parseObject(jsonString);
    }

}
