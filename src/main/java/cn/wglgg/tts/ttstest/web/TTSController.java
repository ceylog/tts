package cn.wglgg.tts.ttstest.web;

import cn.wglgg.tts.ttstest.service.TTSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


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

    @PostMapping(value = "/tts",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String tts(String text,String voiceName){
        String fileName = ttsService.tts(text,voiceName);
        System.out.println("return ok");
        return "{status:'success',code:0,data:{fileName:'"+fileName+"'}}";
    }

    @GetMapping("/getAudio/{fileName}")
    public void getAudio(@PathVariable("fileName") String fileName, HttpServletResponse resp){
        ttsService.getAudio(fileName+".pcm",resp);
    }
}
