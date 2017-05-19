/*
* Copyright 2017 Zhongan.io All right reserved. This software is the
* confidential and proprietary information of Zhongan.io ("Confidential
* Information"). You shall not disclose such Confidential Information and shall
* use it only in accordance with the terms of the license agreement you entered
* into with Zhongan.io.
*/
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
