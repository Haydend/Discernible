package com.discernible.message;

import com.discernible.message.body.MessageBody;
import com.discernible.message.header.options.OptionsHeader;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {

  private OptionsHeader optionHeader;
  private MessageBody messageBody;

}
