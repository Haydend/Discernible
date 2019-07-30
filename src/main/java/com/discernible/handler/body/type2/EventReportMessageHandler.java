package com.discernible.handler.body.type2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import com.discernible.handler.ByteOutputStream;
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

  public EventReportMessage decodeBody(Queue<Byte> messageBytes, ServiceType serviceType) {

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
    messageBytes.poll(); // Throw away 'append' byte.
    List<AccumulatorField> accumulatorFields = new ArrayList<>();
    for (int i = 0; i < numberOfAccumulators; i++) {
      accumulatorFields.add(accumulatorFieldHandler.decode(messageBytes));
    }

    return new EventReportMessage(serviceType, updateTime, timeOfFix, latitude, longitude, altitude, speed, heading, satellitesCount, fixStatus,
        carrierId, rssi, commStatus, hdopField, inputField, untiStatusField, eventIndex, eventCode, accumulatorFields);
  }

  public void encodeBody(EventReportMessage message, ByteOutputStream out) {
    dateTimeFieldHandler.encode(message.getUpdateTime(), out);
    dateTimeFieldHandler.encode(message.getTimeOfFix(), out);
    coordinateFieldHandler.encode(message.getLatitude(), out);
    coordinateFieldHandler.encode(message.getLongitude(), out);
    signedIntegerFieldHandler.encode(message.getAltitude(), out);
    signedIntegerFieldHandler.encode(message.getSpeed(), out);
    signedShortFieldHandler.encode(message.getHeading(), out);
    unsignedShortFieldHandler.encode(message.getSatellitesCount(), out);
    fixStatusFieldHandler.encode(message.getFixStatus(), out);
    unsignedIntegerFieldHandler.encode(message.getCarrierId(), out);
    signedShortFieldHandler.encode(message.getRssi(), out);
    commStatusFieldHandler.encode(message.getCommStatus(), out);
    hdopFieldHandler.encode(message.getHdop(), out);
    inputFieldHandler.encode(message.getInput(), out);
    unitStatusFieldHandler.encode(message.getUnitStatus(), out);
    unsignedShortFieldHandler.encode(message.getEventIndex(), out);
    unsignedShortFieldHandler.encode(message.getEventCode(), out);
    out.write(message.getAccumulators().size());
    out.write(0x00);// Append byte.
    for (AccumulatorField accumulator : message.getAccumulators()) {
      accumulatorFieldHandler.encode(accumulator, out);
    }
  }

}
