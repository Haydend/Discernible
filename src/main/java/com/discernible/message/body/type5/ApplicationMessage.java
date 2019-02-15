package com.discernible.message.body.type5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import org.apache.commons.lang3.ArrayUtils;

import com.discernible.message.DateTimeField;
import com.discernible.message.SignedIntegerField;
import com.discernible.message.SignedShortField;
import com.discernible.message.UnsignedIntegerField;
import com.discernible.message.UnsignedShortField;
import com.discernible.message.body.MessageBody;
import com.discernible.message.body.UnitStatusField;
import com.discernible.message.body.type2.CommStatusField;
import com.discernible.message.body.type2.CoordinateField;
import com.discernible.message.body.type2.FixStatusField;
import com.discernible.message.body.type2.HdopField;
import com.discernible.message.body.type2.InputField;
import com.discernible.message.body.type5.payload.ApplicationMessagePayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ApplicationMessage extends MessageBody {

  private ServiceType serviceType;
  private boolean sentByLmu;

  private DateTimeField updateTime;
  private DateTimeField timeOfFix;
  private CoordinateField latitude;
  private CoordinateField longitude;
  private SignedIntegerField altitude;
  private SignedIntegerField speed;
  private SignedShortField heading;
  private UnsignedShortField satellitesCount;
  private FixStatusField fixStatus;
  private UnsignedIntegerField carrierId;
  private SignedShortField rssi;
  private CommStatusField commStatus;
  private HdopField hdop;
  private InputField input;
  private UnitStatusField unitStatus;

  private ApplicationMessagePayload applicationMessagePayload;

  public static ApplicationMessage decodeBody(Queue<Byte> messageBytes, ServiceType serviceType, boolean sentByLmu) {

    DateTimeField updateTime = null;
    DateTimeField timeOfFix = null;
    CoordinateField latitude = null;
    CoordinateField longitude = null;
    SignedIntegerField altitude = null;
    SignedIntegerField speed = null;
    SignedShortField heading = null;
    UnsignedShortField satellitesCount = null;
    FixStatusField fixStatus = null;
    UnsignedIntegerField carrierId = null;
    SignedShortField rssi = null;
    CommStatusField commStatus = null;
    HdopField hdop = null;
    InputField input = null;
    UnitStatusField unitStatus = null;
    if (sentByLmu) {
      updateTime = DateTimeField.decode(messageBytes);
      timeOfFix = DateTimeField.decode(messageBytes);
      latitude = CoordinateField.decode(messageBytes);
      longitude = CoordinateField.decode(messageBytes);
      altitude = SignedIntegerField.decode(messageBytes);
      speed = SignedIntegerField.decode(messageBytes);
      heading = SignedShortField.decode(messageBytes);
      satellitesCount = UnsignedShortField.decode(messageBytes);
      fixStatus = FixStatusField.decode(messageBytes);
      carrierId = UnsignedIntegerField.decode(messageBytes);
      rssi = SignedShortField.decode(messageBytes);
      commStatus = CommStatusField.decode(messageBytes);
      hdop = HdopField.decode(messageBytes);
      input = InputField.decode(messageBytes);
      unitStatus = UnitStatusField.decode(messageBytes);
    }

    ApplicationMessagePayload applicationMessagePayload = ApplicationMessagePayload.decode(messageBytes);

    return new ApplicationMessage(serviceType, sentByLmu, updateTime, timeOfFix, latitude, longitude, altitude, speed, heading, satellitesCount,
        fixStatus, carrierId, rssi, commStatus, hdop, input, unitStatus, applicationMessagePayload);
  }

  @Override
  public ServiceType getServiceType() {
    return serviceType;
  }

  @Override
  public MessageType getMessageType() {
    return MessageType.APPLICATION_DATA_MESSAGE;
  }

  @Override
  public byte[] encodeBody() {

    List<Byte> messageBytes = new ArrayList<>();

    if (sentByLmu) {
      add(messageBytes, updateTime.encode());
      add(messageBytes, timeOfFix.encode());
      add(messageBytes, latitude.encode());
      add(messageBytes, longitude.encode());
      add(messageBytes, altitude.encode());
      add(messageBytes, speed.encode());
      add(messageBytes, heading.encode());
      add(messageBytes, satellitesCount.encode());
      add(messageBytes, fixStatus.encode());
      add(messageBytes, carrierId.encode());
      add(messageBytes, rssi.encode());
      add(messageBytes, commStatus.encode());
      add(messageBytes, hdop.encode());
      add(messageBytes, input.encode());
      add(messageBytes, unitStatus.encode());
    }

    add(messageBytes, applicationMessagePayload.encode());

    Byte[] bytes = messageBytes.toArray(new Byte[messageBytes.size()]);
    return ArrayUtils.toPrimitive(bytes);
  }

  private void add(List<Byte> bytes, byte[] toAdd) {
    bytes.addAll(Arrays.asList(ArrayUtils.toObject(toAdd)));
  }

}
