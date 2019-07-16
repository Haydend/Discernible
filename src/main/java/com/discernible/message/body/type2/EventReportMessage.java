package com.discernible.message.body.type2;

import java.time.LocalDateTime;
import java.util.List;

import com.discernible.message.body.MessageBody;
import com.discernible.message.body.UnitStatusField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EventReportMessage extends MessageBody {

  private ServiceType serviceType;
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
  private Short eventIndex;
  private Short eventCode;
  private List<AccumulatorField> accumulators;

  @Override
  public ServiceType getServiceType() {
    return serviceType;
  }

  @Override
  public MessageType getMessageType() {
    return MessageType.EVENT_REPORT_MESSAGE;
  }

}
