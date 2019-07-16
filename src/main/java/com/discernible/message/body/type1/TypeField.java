package com.discernible.message.body.type1;

import com.discernible.message.body.Message.MessageType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TypeField {

  private MessageType type;

}
