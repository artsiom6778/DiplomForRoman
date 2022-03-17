package com.example.sendtobotform.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequestDto {
  @NotBlank
  private String name;

  @NotBlank
  private String phone;

  @NotNull
  private MultipartFile file;
  private Integer hashcode;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public RequestDto(String name, String phone, MultipartFile file,Integer hashcode) {
    this.name = name;
    this.phone = phone;
    this.file = file;
    this.hashcode = file.hashCode();
  }



  public void setFile(MultipartFile file) {
    this.file = file;
  }

  public MultipartFile getFile() {
    return file;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("SendToBotDto{");
    sb.append("name='").append(name).append('\'');
    sb.append(", phone='").append(phone).append('\'');
    sb.append(", hashcode=").append(hashcode).append('\'');
    sb.append(", file=").append(file);
    sb.append('}');
    return sb.toString();
  }
}
