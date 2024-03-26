package com.example.demo.controller;

import com.example.demo.vo.OpenAInfo;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo.openGPT.*;

import java.io.IOException;

@Controller
@CrossOrigin
@RequestMapping("/openAi")
public class OpenAIController {

    @PostMapping("/getAnswer")
	@ResponseBody
    public String getAnswer(@RequestBody OpenAInfo data) throws IOException {
    	// new GPT
    	OpenAIClient _GPT = new OpenAIClient();
    	return _GPT.getOpenMes(data.getContent());
    }


}
