package cn.wglgg.tts.ttstest.service;

import cn.wglgg.tts.ttstest.util.PCMUtil;
import com.iflytek.cloud.speech.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wanggang 2017/5/17 14:12
 */
@Component
public class TTSService {

    @Value("${tts.appId}")
    private String appId;
    @Value("${tts.audioSavePath}")
    private String audioSavePath;

    private SimpleDateFormat sdf;
    /*static {
        System.out.println(System.getProperty("user.dir"));
        String LIBFILENAME = "C:\\Users\\za-wanggang\\Desktop\\java_voice_xunfei\\lib\\msc64.dll";
        System.load(LIBFILENAME);
    }*/
    private SpeechSynthesizer mTts;

    public TTSService() {
        // 初始化
        // param.append( ","+SpeechConstant.LIB_NAME_32+"=myMscName" );
        SpeechUtility.createUtility("appid=" + appId);
        mTts = SpeechSynthesizer.createSynthesizer();
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速，范围 0~100
        mTts.setParameter(SpeechConstant.PITCH, "50");//设置语调，范围 0~100
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围 0~100
        sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
    }

    public String tts(String txt, String voiceName) {
        if (null == voiceName) {
            voiceName = "vinn";
        }
        mTts.setParameter(SpeechConstant.VOICE_NAME, voiceName);//设置发音人
        String fileName = sdf.format(new Date(System.currentTimeMillis()));

        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, audioSavePath + fileName + ".pcm");
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
                if (null == speechError) {

                    try {
                        PCMUtil.convertAudioFiles(audioSavePath + fileName+".pcm",audioSavePath+"\\wav\\" + fileName+".wav");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("成功");
                } else {
                    System.out.println(speechError);
                }
            }

            @Override
            public void onEvent(int i, int i1, int i2, int i3, Object o, Object o1) {

            }
        });
        return fileName;
    }

    public void getAudio(String fileName, HttpServletResponse resp) {
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(audioSavePath + fileName));
            int i = bis.read(buff);
            os = resp.getOutputStream();
            while (-1 != i) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
