package com.discernible.handler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.discernible.util.ByteUtils;

public class DateTimeFieldHandler implements FieldHandler<LocalDateTime> {

  public LocalDateTime decode(ByteInputStream in) {

    byte[] dateTimeFieldBytes = in.read(4);
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
