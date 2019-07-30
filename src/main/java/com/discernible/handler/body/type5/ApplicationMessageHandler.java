package com.discernible.handler.body.type5;

import java.time.LocalDateTime;
import java.util.Queue;

import com.discernible.handler.ByteOutputStream;
import com.discernible.handler.DateTimeFieldHandler;
import com.discernible.handler.SignedIntegerFieldHandler;
import com.discernible.handler.SignedShortFieldHandler;
import com.discernible.handler.UnsignedIntegerFieldHandler;
import com.discernible.handler.UnsignedShortFieldHandler;
import com.discernible.handler.body.UnitStatusFieldHandler;
import com.discernible.handler.body.type2.CommStatusFieldHandler;
import com.discernible.handler.body.type2.CoordinateFieldHandler;
import com.discernible.handler.body.type2.FixStatusFieldHandler;
import com.discernible.handler.body.type2.HdopFieldHandler;
import com.discernible.handler.body.type2.InputFieldHandler;
import com.discernible.handler.body.type5.payload.ApplicationMessagePayloadHandler;
import com.discernible.message.body.Message.ServiceType;
import com.discernible.message.body.UnitStatusField;
import com.discernible.message.body.type2.CommStatusField;
import com.discernible.message.body.type2.CoordinateField;
import com.discernible.message.body.type2.FixStatusField;
import com.discernible.message.body.type2.HdopField;
import com.discernible.message.body.type2.InputField;
import com.discernible.message.body.type5.ApplicationMessage;
import com.discernible.message.body.type5.payload.ApplicationMessagePayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ApplicationMessageHandler {

  private final DateTimeFieldHandler dateTimeFieldHandler = new DateTimeFieldHandler();
  private final CoordinateFieldHandler coordinateFieldHandler = new CoordinateFieldHandler();
  private final SignedIntegerFieldHandler signedIntegerFieldHandler = new SignedIntegerFieldHandler();
  private final SignedShortFieldHandler signedShortFieldHandler = new SignedShortFieldHandler();
  private final UnsignedShortFieldHandler unsignedShortFieldHandler = new UnsignedShortFieldHandler();
  private final FixStatusFieldHandler fixStatusFieldHandler = new FixStatusFieldHandler();
  private final UnsignedIntegerFieldHandler unsignedIntegerFieldHandler = new UnsignedIntegerFieldHandler();
  private final CommStatusFieldHandler commStatusFieldHandler = new CommStatusFieldHandler();
  private final HdopFieldHandler hdopFieldHandler = new HdopFieldHandler();
  private final InputFieldHandler inputFieldHandler = new InputFieldHandler();
  private final UnitStatusFieldHandler unitStatusField = new UnitStatusFieldHandler();
  private final ApplicationMessagePayloadHandler applicationMessagePayloadHandler = new ApplicationMessagePayloadHandler();

  public ApplicationMessage decodeBody(Queue<Byte> messageBytes, ServiceType serviceType, boolean sentByLmu) {

    LocalDateTime updateTime = null;
    LocalDateTime timeOfFix = null;
    CoordinateField latitude = null;
    CoordinateField longitude = null;
    Integer altitude = null;
    Integer speed = null;
    Short heading = null;
    Short satellitesCount = null;
    FixStatusField fixStatus = null;
    Integer carrierId = null;
    Short rssi = null;
    CommStatusField commStatus = null;
    HdopField hdop = null;
    InputField input = null;
    UnitStatusField unitStatus = null;
    if (sentByLmu) {
      updateTime = dateTimeFieldHandler.decode(messageBytes);
      timeOfFix = dateTimeFieldHandler.decode(messageBytes);
      latitude = coordinateFieldHandler.decode(messageBytes);
      longitude = coordinateFieldHandler.decode(messageBytes);
      altitude = signedIntegerFieldHandler.decode(messageBytes);
      speed = signedIntegerFieldHandler.decode(messageBytes);
      heading = signedShortFieldHandler.decode(messageBytes);
      satellitesCount = unsignedShortFieldHandler.decode(messageBytes);
      fixStatus = fixStatusFieldHandler.decode(messageBytes);
      carrierId = unsignedIntegerFieldHandler.decode(messageBytes);
      rssi = signedShortFieldHandler.decode(messageBytes);
      commStatus = commStatusFieldHandler.decode(messageBytes);
      hdop = hdopFieldHandler.decode(messageBytes);
      input = inputFieldHandler.decode(messageBytes);
      unitStatus = unitStatusField.decode(messageBytes);
    }

    ApplicationMessagePayload applicationMessagePayload = applicationMessagePayloadHandler.decode(messageBytes);

    return new ApplicationMessage(serviceType, sentByLmu, updateTime, timeOfFix, latitude, longitude, altitude, speed, heading, satellitesCount,
        fixStatus, carrierId, rssi, commStatus, hdop, input, unitStatus, applicationMessagePayload);
  }

  public void encodeBody(ApplicationMessage message, boolean sentByLmu, ByteOutputStream output) {

    if (sentByLmu) {
      dateTimeFieldHandler.encode(message.getUpdateTime(), output);
      dateTimeFieldHandler.encode(message.getTimeOfFix(), output);
      coordinateFieldHandler.encode(message.getLatitude(), output);
      coordinateFieldHandler.encode(message.getLongitude(), output);
      signedIntegerFieldHandler.encode(message.getAltitude(), output);
      signedIntegerFieldHandler.encode(message.getSpeed(), output);
      signedShortFieldHandler.encode(message.getHeading(), output);
      unsignedShortFieldHandler.encode(message.getSatellitesCount(), output);
      fixStatusFieldHandler.encode(message.getFixStatus(), output);
      unsignedIntegerFieldHandler.encode(message.getCarrierId(), output);
      signedShortFieldHandler.encode(message.getRssi(), output);
      commStatusFieldHandler.encode(message.getCommStatus(), output);
      hdopFieldHandler.encode(message.getHdop(), output);
      inputFieldHandler.encode(message.getInput(), output);
      unitStatusField.encode(message.getUnitStatus(), output);
    }

    applicationMessagePayloadHandler.encode(message.getApplicationMessagePayload(), output);
  }
}
