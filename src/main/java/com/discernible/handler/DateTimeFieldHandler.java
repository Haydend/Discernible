package com.discernible.handler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateTimeFieldHandler implements FieldHandler<LocalDateTime> {

  private UnsignedLongFieldHandler unsignedLongFieldHandler = new UnsignedLongFieldHandler();

  public LocalDateTime decode(ByteInputStream in) {
    long epochSeconds = unsignedLongFieldHandler.decode(in);
    LocalDateTime dateTime = LocalDateTime.ofEpochSecond(epochSeconds, 0, ZoneOffset.UTC);

    return dateTime;
  }

  @Override
  public void encode(LocalDateTime datetime, ByteOutputStream out) {
    long epochSeconds = datetime.atZone(ZoneOffset.UTC).toEpochSecond();
    unsignedLongFieldHandler.encode(epochSeconds, out);
  }

}
