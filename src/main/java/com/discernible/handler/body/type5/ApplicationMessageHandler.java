package com.discernible.handler.body.type5;

import java.time.LocalDateTime;

import com.discernible.handler.ByteInputStream;
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

  public ApplicationMessage decodeBody(ByteInputStream in, ServiceType serviceType, boolean sentByLmu) {

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
      updateTime = dateTimeFieldHandler.decode(in);
      timeOfFix = dateTimeFieldHandler.decode(in);
      latitude = coordinateFieldHandler.decode(in);
      longitude = coordinateFieldHandler.decode(in);
      altitude = signedIntegerFieldHandler.decode(in);
      speed = signedIntegerFieldHandler.decode(in);
      heading = signedShortFieldHandler.decode(in);
      satellitesCount = unsignedShortFieldHandler.decode(in);
      fixStatus = fixStatusFieldHandler.decode(in);
      carrierId = unsignedIntegerFieldHandler.decode(in);
      rssi = signedShortFieldHandler.decode(in);
      commStatus = commStatusFieldHandler.decode(in);
      hdop = hdopFieldHandler.decode(in);
      input = inputFieldHandler.decode(in);
      unitStatus = unitStatusField.decode(in);
    }

    ApplicationMessagePayload applicationMessagePayload = applicationMessagePayloadHandler.decode(in);

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
