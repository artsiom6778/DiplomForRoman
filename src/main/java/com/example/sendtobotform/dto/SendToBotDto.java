package com.example.sendtobotform.dto;

import java.io.InputStream;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

public class SendToBotDto {

  private String chatId;
  private ByteArrayResource media;

  public SendToBotDto(String chatId, ByteArrayResource media) {
    this.chatId = chatId;
    this.media = media;
  }

  public String getChatId() {
    return chatId;
  }

  public void setChatId(String chatId) {
    this.chatId = chatId;
  }

  public ByteArrayResource getMedia() {
    return media;
  }

  public void setMedia(ByteArrayResource media) {
    this.media = media;
  }
}
