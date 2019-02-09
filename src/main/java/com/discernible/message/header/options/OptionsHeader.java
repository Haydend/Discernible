package com.discernible.message.header.options;

import com.discernible.message.ByteFieldWithLength;
import com.discernible.message.SocketFieldWithLength;

import lombok.Data;

@Data
public class OptionsHeader {

  private ByteFieldWithLength mobileId;
  private MobileIdTypeField mobileIdType;
  private ByteFieldWithLength authentication;
  private ByteFieldWithLength routing;
  private ForwardingField forwarding;
  private SocketFieldWithLength responseRedirection;
  private String esn;
  private String vin;

}
