package com.hucheng.codediffapp.openapi;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        byte[] imageBytes = Base64.getDecoder().decode(base64String);

        try (OutputStream outputStream = new FileOutputStream(imageFile)) {
            outputStream.write(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //最大次数5次
        int count = 5;
        while (captchaText.length() != 4 && count > 0) {
            count--;
            try {
                // 使用Tesseract进行验证码识别
                ITesseract tesseract = new Tesseract();
                tesseract.setLanguage("eng");
                tesseract.setDatapath("/opt/server/test-app/tessdata/tessdata");
                captchaText = extractAlphanumeric(tesseract.doOCR(imageFile));
                log.info("OCR识别成功{}:", captchaText);
            } catch (TesseractException e) {
                e.printStackTrace();
            }
        }

        // 删除临时图片文件
        imageFile.delete();

        JSONObject result = new JSONObject();
        result.put("code", captchaText);
        return result;
    }

    private String extractAlphanumeric(String input) {
        // 使用正则表达式匹配字母和数字
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+");
        Matcher matcher = pattern.matcher(input);

        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group());
        }

        return sb.toString();
    }

}
