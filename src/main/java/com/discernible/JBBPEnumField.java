package com.discernible;

import com.igormaznitsa.jbbp.compiler.JBBPNamedFieldInfo;
import com.igormaznitsa.jbbp.model.JBBPAbstractField;

public class JBBPEnumField extends JBBPAbstractField {

  private final Enum<?> value;

  public JBBPEnumField(JBBPNamedFieldInfo namedField, Enum<?> value) {
    super(namedField);
    this.value = value;
  }

  @SuppressWarnings("unchecked")
  public <E extends Enum<E>> E getValue() {
    return (E) value;
  }

  @Override
  public String getTypeAsString() {
    return "enum";
  }

}
