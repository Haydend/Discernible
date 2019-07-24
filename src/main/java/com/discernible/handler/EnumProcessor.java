package com.discernible.handler;

import java.io.IOException;

import org.apache.commons.lang3.EnumUtils;

import com.discernible.JBBPEnumField;
import com.discernible.message.ByteEnum;
import com.igormaznitsa.jbbp.JBBPCustomFieldTypeProcessor;
import com.igormaznitsa.jbbp.compiler.JBBPNamedFieldInfo;
import com.igormaznitsa.jbbp.compiler.tokenizer.JBBPFieldTypeParameterContainer;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;
import com.igormaznitsa.jbbp.io.JBBPBitOrder;
import com.igormaznitsa.jbbp.model.JBBPAbstractField;


public abstract class EnumProcessor<E extends Enum<E> & ByteEnum> implements JBBPCustomFieldTypeProcessor {

  protected abstract Class<E> getClazz();

  @Override
  public boolean isAllowed(JBBPFieldTypeParameterContainer fieldType, String fieldName, int extraData, boolean isArray) {
    return true;
  }

  @Override
  public JBBPAbstractField readCustomFieldType(JBBPBitInputStream in, JBBPBitOrder bitOrder, int parserFlags,
      JBBPFieldTypeParameterContainer customTypeFieldInfo, JBBPNamedFieldInfo fieldName, int extraData, boolean readWholeStream, int arrayLength)
      throws IOException {

    byte byteToMatch = (byte) in.readByte();

    E value = EnumUtils.getEnumList(getClazz()).stream()
        .filter(e -> e.getByteForEnum() == byteToMatch)
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("No Enum found to match byte"));

    return new JBBPEnumField(fieldName, value);
  }

}
