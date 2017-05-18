package cn.wglgg.tts.ttstest.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.iflytek.cloud.speech.*;

/**
 *
 *
 * @author wanggang 2017/5/17 14:12
 */
@Component
@ConfigurationProperties(prefix = "tts")
public class TTSService {

    private String appId;


    /*static {
        System.out.println(System.getProperty("user.dir"));
        String LIBFILENAME = "C:\\Users\\za-wanggang\\Desktop\\java_voice_xunfei\\lib\\msc64.dll";
        System.load(LIBFILENAME);
    }*/
    private SpeechSynthesizer mTts;
    public TTSService(){
        // 初始化
        // param.append( ","+SpeechConstant.LIB_NAME_32+"=myMscName" );
        SpeechUtility.createUtility( "appid=" + appId);
        mTts = SpeechSynthesizer.createSynthesizer();
    }

    public void tts(String txt){
        mTts.setParameter(SpeechConstant.VOICE_NAME,"vinn");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速，范围 0~100
        mTts.setParameter(SpeechConstant.PITCH, "50");//设置语调，范围 0~100
        mTts.setParameter(SpeechConstant.VOLUME, "100");//设置音量，范围 0~100
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH,"D:\\tts_test.pcm");
        mTts.startSpeaking(txt, new SynthesizerListener() {
            @Override
            public void onBufferProgress(int i, int i1, int i2, String s) {

            }

            @Override
            public void onSpeakBegin() {

            }

            @Override
            public void onSpeakProgress(int i, int i1, int i2) {

            }

            @Override
            public void onSpeakPaused() {

            }

            @Override
            public void onSpeakResumed() {

            }

            @Override
            public void onCompleted(SpeechError speechError) {
                if (null == speechError){
                    System.out.println("成功");
                }else {
                    System.out.println(speechError);
                }
            }

            @Override
            public void onEvent(int i, int i1, int i2, int i3, Object o, Object o1) {

            }
        });
    }

}
