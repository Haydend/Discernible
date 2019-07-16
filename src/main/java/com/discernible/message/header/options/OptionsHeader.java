package com.discernible.message.header.options;

import com.discernible.message.Socket;
import com.discernible.message.header.options.extension.OptionExtension;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptionsHeader {

  private byte[] mobileId;
  private MobileIdTypeField mobileIdType;
  private byte[] authentication;
  private byte[] routing;
  private ForwardingField forwarding;
  private Socket responseRedirection;
  private OptionExtension optionExtension;

}
