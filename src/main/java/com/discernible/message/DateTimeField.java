package com.discernible.message;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Queue;

import com.discernible.util.ByteUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DateTimeField implements Field {

  private LocalDateTime datetime;

  public static DateTimeField decode(Queue<Byte> messageBytes) {

    byte[] dateTimeFieldBytes = ByteUtils.getFieldBytes(4, messageBytes);
    long epochSeconds = ByteUtils.unsignedIntToLong(dateTimeFieldBytes);
    LocalDateTime dateTime = LocalDateTime.ofEpochSecond(epochSeconds, 0, ZoneOffset.UTC);

    return new DateTimeField(dateTime);
  }

  @Override
  public byte[] encode() {

    long epochSeconds = datetime.atZone(ZoneOffset.UTC).toEpochSecond();
    byte[] dateTimeBytes = BigInteger.valueOf(epochSeconds).toByteArray();

    byte[] messageBytes = new byte[4];
    int padding = 4 - dateTimeBytes.length;
    System.arraycopy(dateTimeBytes, 0, messageBytes, padding, dateTimeBytes.length);

    return messageBytes;
  }

}
