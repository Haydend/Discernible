package com.discernible.handler.body.type2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.discernible.handler.DateTimeFieldHandler;
import com.discernible.handler.SignedIntegerFieldHandler;
import com.discernible.handler.SignedShortFieldHandler;
import com.discernible.handler.UnsignedIntegerFieldHandler;
import com.discernible.handler.UnsignedShortFieldHandler;
import com.discernible.handler.body.UnitStatusFieldHandler;
import com.discernible.message.body.Message.ServiceType;
import com.discernible.message.body.UnitStatusField;
import com.discernible.message.body.type2.AccumulatorField;
import com.discernible.message.body.type2.CommStatusField;
import com.discernible.message.body.type2.CoordinateField;
import com.discernible.message.body.type2.EventReportMessage;
import com.discernible.message.body.type2.FixStatusField;
import com.discernible.message.body.type2.HdopField;
import com.discernible.message.body.type2.InputField;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EventReportMessageHandler {

  private final DateTimeFieldHandler dateTimeFieldHandler = new DateTimeFieldHandler();
  private final CoordinateFieldHandler coordinateFieldHandler = new CoordinateFieldHandler();
  private final SignedIntegerFieldHandler signedIntegerFieldHandler = new SignedIntegerFieldHandler();
  private final UnsignedIntegerFieldHandler unsignedIntegerFieldHandler = new UnsignedIntegerFieldHandler();
  private final SignedShortFieldHandler signedShortFieldHandler = new SignedShortFieldHandler();
  private final UnsignedShortFieldHandler unsignedShortFieldHandler = new UnsignedShortFieldHandler();
  private final FixStatusFieldHandler fixStatusFieldHandler = new FixStatusFieldHandler();
  private final CommStatusFieldHandler commStatusFieldHandler = new CommStatusFieldHandler();
  private final HdopFieldHandler hdopFieldHandler = new HdopFieldHandler();
  private final InputFieldHandler inputFieldHandler = new InputFieldHandler();
  private final UnitStatusFieldHandler unitStatusFieldHandler = new UnitStatusFieldHandler();
  private final AccumulatorFieldHandler accumulatorFieldHandler = new AccumulatorFieldHandler();

  public EventReportMessage decodeBody(JBBPBitInputStream messageBytes, ServiceType serviceType) {

    LocalDateTime updateTime = dateTimeFieldHandler.decode(messageBytes);
    LocalDateTime timeOfFix = dateTimeFieldHandler.decode(messageBytes);
    CoordinateField latitude = coordinateFieldHandler.decode(messageBytes);
    CoordinateField longitude = coordinateFieldHandler.decode(messageBytes);
    Integer altitude = signedIntegerFieldHandler.decode(messageBytes);
    Integer speed = signedIntegerFieldHandler.decode(messageBytes);
    Short heading = signedShortFieldHandler.decode(messageBytes);
    Short satellitesCount = unsignedShortFieldHandler.decode(messageBytes);
    FixStatusField fixStatus = fixStatusFieldHandler.decode(messageBytes);
    Integer carrierId = unsignedIntegerFieldHandler.decode(messageBytes);
    Short rssi = signedShortFieldHandler.decode(messageBytes);
    CommStatusField commStatus = commStatusFieldHandler.decode(messageBytes);
    HdopField hdopField = hdopFieldHandler.decode(messageBytes);
    InputField inputField = inputFieldHandler.decode(messageBytes);
    UnitStatusField untiStatusField = unitStatusFieldHandler.decode(messageBytes);
    Short eventIndex = unsignedShortFieldHandler.decode(messageBytes);
    Short eventCode = unsignedShortFieldHandler.decode(messageBytes);

    Short numberOfAccumulators = unsignedShortFieldHandler.decode(messageBytes);
    ByteUtils.getByte(messageBytes); // Throw away 'append' byte.
    List<AccumulatorField> accumulatorFields = new ArrayList<>();
    for (int i = 0; i < numberOfAccumulators; i++) {
      accumulatorFields.add(accumulatorFieldHandler.decode(messageBytes));
    }

    return new EventReportMessage(serviceType, updateTime, timeOfFix, latitude, longitude, altitude, speed, heading, satellitesCount, fixStatus,
        carrierId, rssi, commStatus, hdopField, inputField, untiStatusField, eventIndex, eventCode, accumulatorFields);
  }

  public byte[] encodeBody(EventReportMessage message) {
    List<Byte> messageBytes = new ArrayList<>();

    add(messageBytes, dateTimeFieldHandler.encode(message.getUpdateTime()));
    add(messageBytes, dateTimeFieldHandler.encode(message.getTimeOfFix()));
    add(messageBytes, coordinateFieldHandler.encode(message.getLatitude()));
    add(messageBytes, coordinateFieldHandler.encode(message.getLongitude()));
    add(messageBytes, signedIntegerFieldHandler.encode(message.getAltitude()));
    add(messageBytes, signedIntegerFieldHandler.encode(message.getSpeed()));
    add(messageBytes, signedShortFieldHandler.encode(message.getHeading()));
    add(messageBytes, unsignedShortFieldHandler.encode(message.getSatellitesCount()));
    add(messageBytes, fixStatusFieldHandler.encode(message.getFixStatus()));
    add(messageBytes, unsignedIntegerFieldHandler.encode(message.getCarrierId()));
    add(messageBytes, signedShortFieldHandler.encode(message.getRssi()));
    add(messageBytes, commStatusFieldHandler.encode(message.getCommStatus()));
    add(messageBytes, hdopFieldHandler.encode(message.getHdop()));
    add(messageBytes, inputFieldHandler.encode(message.getInput()));
    add(messageBytes, unitStatusFieldHandler.encode(message.getUnitStatus()));
    add(messageBytes, unsignedShortFieldHandler.encode(message.getEventIndex()));
    add(messageBytes, unsignedShortFieldHandler.encode(message.getEventCode()));
    add(messageBytes, new byte[] {(byte) message.getAccumulators().size()});
    messageBytes.add(new Byte((byte) 0x00)); // Append byte.
    for (AccumulatorField accumulator : message.getAccumulators()) {
      add(messageBytes, accumulatorFieldHandler.encode(accumulator));
    }

    Byte[] bytes = messageBytes.toArray(new Byte[messageBytes.size()]);
    return ArrayUtils.toPrimitive(bytes);
  }

  private void add(List<Byte> bytes, byte[] toAdd) {
    bytes.addAll(Arrays.asList(ArrayUtils.toObject(toAdd)));
  }

}
