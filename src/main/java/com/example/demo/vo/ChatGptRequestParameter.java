package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.vo.OpenAInfo;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatGptRequestParameter {

    private String model = "gpt-3.5-turbo";
    private static List<OpenAInfo> messages = new ArrayList<>();

    public List<OpenAInfo> getMessages() {
        return messages;
    }

    public static  void addMessage(OpenAInfo message) {
        messages.add(message);
    }

}
