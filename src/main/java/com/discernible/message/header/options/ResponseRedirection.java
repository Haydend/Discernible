package com.discernible.message.header.options;

import lombok.Data;

@Data
public class ResponseRedirection {

  private String address;
  private Integer port;

}
