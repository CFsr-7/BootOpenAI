package com.example.demo.openGPT;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.example.demo.vo.ChatGptRequestParameter;
import com.example.demo.vo.ChatResponseParameter;
import com.example.demo.vo.OpenAInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.core5.http.*;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;

@Service
public class OpenAIClient {

	/*
	* port
	* */
	private int proxyPort = 50616;

	/*
	 * host
	 * */
	private String proxyHost = "127.0.0.1";

	/**
	 * OpenAI 密钥
	 */
	private String apiKey = "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";

	/**
	 * OpenAI 响应模型
	 */
	private String model = "gpt-3.5-turbo-0301";

	/**
	 * 默认编码
	 */
	private Charset charset = StandardCharsets.UTF_8;

	/**
	 * OpenAI API 终端地址
	 */
	private String endpoint = "https://api.openai.com/v1/chat/completions";


	/**
	 * 创建一个ChatGptRequestParameter，用于携带请求参数
	 */
	private ChatGptRequestParameter chatGptRequestParameter = new ChatGptRequestParameter();


	/*
	* 获取结果
	* */
    public String getOpenMes(String prompt) throws IOException {
		try (
				CloseableHttpClient httpClient
						= HttpClients
						.custom()
						.setProxy(new HttpHost(proxyHost, proxyPort))
						.build()
		) {
			// 创建 HttpPost
			HttpPost httpPost = new HttpPost(endpoint);
			// 设置请求头
			httpPost.setHeader("Authorization", "Bearer " + apiKey);
			httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
			// 创建一个ObjectMapper，用于解析和创建json
			ObjectMapper objectMapper = new ObjectMapper();
			ChatGptRequestParameter.addMessage(new OpenAInfo("user",prompt));
			// 创建请求实体
			StringEntity requestEntity = new StringEntity(objectMapper.writeValueAsString(chatGptRequestParameter), charset);
			// 设置请求实体
			httpPost.setEntity(requestEntity);
			// 发送请求，并获取响应
			try (
					CloseableHttpResponse response = httpClient.execute(httpPost)
			) {
				try	{
					// 读取响应内容
					System.out.println(EntityUtils.toString(response.getEntity()));
					return EntityUtils.toString(response.getEntity());
				}catch (ParseException e) {
					throw new RuntimeException(e);
				}
            } catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
