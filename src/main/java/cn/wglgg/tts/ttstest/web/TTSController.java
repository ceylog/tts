package cn.wglgg.tts.ttstest.web;

import cn.wglgg.tts.ttstest.service.TTSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 *
 * @author za-wanggang 2017/5/18 18:02
 */
@Controller
public class TTSController {

    @Autowired
    private TTSService ttsService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/tts")
    @ResponseBody
    public String tts(String text){
        ttsService.tts(text);
        return "ok";
    }
}
