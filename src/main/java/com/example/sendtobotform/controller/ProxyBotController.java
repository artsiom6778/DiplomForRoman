package com.example.sendtobotform.controller;

import com.example.sendtobotform.dto.RequestDto;
import com.example.sendtobotform.dto.ResponseDto;
import java.io.IOException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
public class ProxyBotController {

  @PostMapping("/sendtobot")
  public ResponseEntity<ResponseDto> index(@ModelAttribute @Valid RequestDto requestDto, BindingResult result) throws IOException {
    String photoUrl = "https://api.telegram.org/bot341996777:AAHbnuvQib-vHU47i-6hbUrCU9D-qHYekxc/sendPhoto";
    String messageUrl = "https://api.telegram.org/bot341996777:AAHbnuvQib-vHU47i-6hbUrCU9D-qHYekxc/sendMessage";

    RestTemplate restTemplate = new RestTemplate();

    try {
      if (result.hasErrors()) {
        return ResponseEntity.ok(new ResponseDto("NOTVALID"));
      }
      restTemplate.postForObject(photoUrl, createMultipartPhoto(requestDto), String.class);
      restTemplate.postForObject(messageUrl, createMultipartMessage(requestDto), String.class);

      return ResponseEntity.ok(new ResponseDto("SUCCESS"));
    } catch (Exception e) {
      return ResponseEntity.ok(new ResponseDto("ERROR"));
    }
  }


  private HttpEntity createMultipartPhoto(RequestDto requestDto) throws IOException {
    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
    formData.add("chat_id", "-718237298");
    formData.add("photo", requestDto.getFile().getResource());
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "multipart/form-data");
    headers.set("Accept", "text/plain");
    return new HttpEntity<>(formData, headers);
  }

  private HttpEntity createMultipartMessage(RequestDto requestDto) throws IOException {
    MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
    formData.add("chat_id", "-718237298");
    formData.add("text", requestDto.getName() + " | " + requestDto.getPhone() + "|" + requestDto.getFile().hashCode());
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Type", "multipart/form-data");
    headers.set("Accept", "text/plain");
    return new HttpEntity<>(formData, headers);
  }
}
