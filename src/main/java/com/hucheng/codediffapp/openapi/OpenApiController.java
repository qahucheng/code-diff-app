package com.hucheng.codediffapp.openapi;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Base64;

import net.sourceforge.tess4j.*;


@Controller
@Slf4j
public class OpenApiController {

    @PostMapping("api/captchaText")
    @ResponseBody
    public JSONObject hello1(@RequestBody JSONObject jsonObject) {
        String captchaText = "";
        String base64String = jsonObject.getString("base64String");
        log.info("base64String字符串{}", base64String);
        File imageFile = new File("captcha_image.png"); // 临时保存图片文件
        try {
            // 解码Base64字符串为字节数组
            byte[] imageBytes = Base64.getDecoder().decode(base64String);

            try (OutputStream outputStream = new FileOutputStream(imageFile)) {
                outputStream.write(imageBytes);
            }
            // 使用Tesseract进行验证码识别
            ITesseract tesseract = new Tesseract();
            tesseract.setLanguage("chi_sim");
            tesseract.setDatapath("/opt/server/test-app/tessdata/tessdata");
            captchaText = tesseract.doOCR(imageFile);
            log.info("OCR识别成功{}:", captchaText);
        } catch (Exception e) {
            log.error("OCR识别失败{}", e.getMessage());
        } finally {
            // 删除临时图片文件
            imageFile.delete();
        }
        JSONObject result = new JSONObject();
        result.put("code", captchaText);
        return result;


    }
}
