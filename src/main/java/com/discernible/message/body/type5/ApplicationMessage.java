package com.discernible.message.body.type5;

import java.time.LocalDateTime;

import com.discernible.message.body.Message;
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
public class ApplicationMessage extends Message {

  private ServiceType serviceType;
  private boolean sentByLmu;

  private LocalDateTime updateTime;
  private LocalDateTime timeOfFix;
  private CoordinateField latitude;
  private CoordinateField longitude;
  private Integer altitude;
  private Integer speed;
  private Short heading;
  private Short satellitesCount;
  private FixStatusField fixStatus;
  private Integer carrierId;
  private Short rssi;
  private CommStatusField commStatus;
  private HdopField hdop;
  private InputField input;
  private UnitStatusField unitStatus;

  private ApplicationMessagePayload applicationMessagePayload;

  @Override
  public ServiceType getServiceType() {
    return serviceType;
  }

  @Override
  public MessageType getMessageType() {
    return MessageType.APPLICATION_DATA_MESSAGE;
  }

}
