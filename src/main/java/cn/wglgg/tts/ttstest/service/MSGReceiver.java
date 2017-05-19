package cn.wglgg.tts.ttstest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 类MSGReceiver的实现描述：TODO 类实现描述
 *
 * @author za-wanggang 2017/5/19 18:30
 */
@Component
public class MSGReceiver {

    @Autowired
    private TTSService ttsService;

    @KafkaListener(topics = "msg2tts")
    public void processMessage(String content) {
        System.out.println("<<< "+content);
        ttsService.tts(content,null);
    }
}
