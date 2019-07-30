package com.discernible.handler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Queue;

import com.discernible.util.ByteUtils;

public class DateTimeFieldHandler implements FieldHandler<LocalDateTime> {

  public LocalDateTime decode(Queue<Byte> messageBytes) {

    byte[] dateTimeFieldBytes = ByteUtils.getFieldBytes(4, messageBytes);
    long epochSeconds = ByteUtils.unsignedIntToLong(dateTimeFieldBytes);
    LocalDateTime dateTime = LocalDateTime.ofEpochSecond(epochSeconds, 0, ZoneOffset.UTC);

    return dateTime;
  }

  @Override
  public void encode(LocalDateTime datetime, ByteOutputStream out) {
    long epochSeconds = datetime.atZone(ZoneOffset.UTC).toEpochSecond();
    out.writeUnsignedInt(epochSeconds);
  }

}
