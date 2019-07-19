package com.discernible;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.igormaznitsa.jbbp.JBBPParser;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;
import com.igormaznitsa.jbbp.mapper.Bin;
import com.igormaznitsa.jbbp.mapper.BinType;
import com.igormaznitsa.jbbp.model.JBBPFieldArrayUByte;
import com.igormaznitsa.jbbp.model.JBBPFieldString;

import lombok.Data;

public class Test {

  @org.junit.Test
  public void test() throws IOException {

    JBBPBitInputStream inputStream = new JBBPBitInputStream(
        new ByteArrayInputStream(new byte[] {(byte) 0x01, (byte) 0b00000010, (byte) 0x04, (byte) 0x74, (byte) 0x65, (byte) 0x73, (byte) 0x74}));

    Flags flags = parseFlags(inputStream);
    byte[] esn = null;
    if (flags.isEsnFlag()) {
      esn = parseEsn(inputStream);
    }
    String vin = null;
    if (flags.isVinFlag()) {
      vin = parseVin(inputStream);
    }


    return;
  }

  private Flags parseFlags(JBBPBitInputStream inputStream) throws IOException {
    return JBBPParser.prepare("skip;bit:1 esnFlag;bit:1 vinFlag;bit:1 encryptionFlag;bit:1 lmDirectCompressionFlag;bit:1 lmDirectRouting;bit:3;")
        .parse(inputStream).mapTo(Flags.class);
  }

  private byte[] parseEsn(JBBPBitInputStream inputStream) throws IOException {
    return JBBPParser.prepare("ubyte esnLength;ubyte[(esnLength)] esn;")
        .parse(inputStream).findFieldForNameAndType("esn", JBBPFieldArrayUByte.class).getArray();
  }

  private String parseVin(JBBPBitInputStream inputStream) throws IOException {
    return JBBPParser.prepare("stringj vin;")
        .parse(inputStream).findFieldForNameAndType("vin", JBBPFieldString.class).getAsString();
  }

  @Data
  class Flags {
    @Bin(type = BinType.BIT)
    private boolean esnFlag;
    @Bin(type = BinType.BIT)
    private boolean vinFlag;
    @Bin(type = BinType.BIT)
    private boolean encryptionFlag;
    @Bin(type = BinType.BIT)
    private boolean lmDirectCompressionFlag;
    @Bin(type = BinType.BIT)
    private boolean lmDirectRouting;
  }


  @Data
  class Model {

    @Bin
    private byte[] test;

    @Bin
    private byte esnFlag;


    @Bin
    private byte[] esn;
    @Bin
    private String vin;
    @Bin
    private boolean lmDirectCompression;
  }

}
