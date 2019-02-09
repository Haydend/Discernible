package com.discernible.message.header.options;

import com.discernible.message.ByteFieldWithLength;
import com.discernible.message.SocketField;
import com.discernible.message.WithLength;

import lombok.Data;

@Data
public class OptionsHeader {

  private ByteFieldWithLength mobileId;
  private MobileIdTypeField mobileIdType;
  private ByteFieldWithLength authentication;
  private ByteFieldWithLength routing;
  private ForwardingField forwarding;
  private WithLength<SocketField> responseRedirection;
  private String esn;
  private String vin;

}
