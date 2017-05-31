package cn.wglgg.tts.ttstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类AppLauncher的实现描述：TODO 类实现描述
 *
 * @author za-wanggang 2017/5/18 17:46
 */
@SpringBootApplication
//@EnableKafka
public class AppLauncher {

    public static void main(String[] args){
        SpringApplication.run(AppLauncher.class,args);
    }
}
