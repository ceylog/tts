package cn.wglgg.tts.ttstest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 类MSGReceiver的实现描述：TODO 类实现描述
 *
 * @author za-wanggang 2017/5/19 18:30
 */
@Component
public class MSGReceiver {

    @Autowired
    private TTSService ttsService;


    ObjectMapper om = null;

    public MSGReceiver(){
        om = new ObjectMapper();
    }

    //@KafkaListener(topics = "msg2tts")
    @JmsListener(destination = "wglgg.msg2tts")
    public void processMessage(String content) {

        Map map = null;
        try {
            map = om.readValue(content, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("<<< "+content);
        ttsService.tts((String)map.get("msg"),(String)map.get("voiceName"));
    }
}
