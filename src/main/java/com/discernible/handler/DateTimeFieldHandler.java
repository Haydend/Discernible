package com.discernible.handler;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

public class DateTimeFieldHandler implements FieldHandler<LocalDateTime> {

  public LocalDateTime decode(JBBPBitInputStream messageBytes) {

    byte[] dateTimeFieldBytes = ByteUtils.getFieldBytes(4, messageBytes);
    long epochSeconds = ByteUtils.unsignedIntToLong(dateTimeFieldBytes);
    LocalDateTime dateTime = LocalDateTime.ofEpochSecond(epochSeconds, 0, ZoneOffset.UTC);

    return dateTime;
  }

  @Override
  public byte[] encode(LocalDateTime datetime) {

    long epochSeconds = datetime.atZone(ZoneOffset.UTC).toEpochSecond();
    byte[] dateTimeBytes = BigInteger.valueOf(epochSeconds).toByteArray();

    byte[] messageBytes = new byte[4];
    int padding = 4 - dateTimeBytes.length;
    System.arraycopy(dateTimeBytes, 0, messageBytes, padding, dateTimeBytes.length);

    return messageBytes;
  }

}
