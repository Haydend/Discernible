package com.discernible.message.body.type2;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EventReportMessage extends MessageBody {

  private ServiceType serviceType;
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
  private UnsignedShortField eventIndex;
  private UnsignedShortField eventCode;
  private List<AccumulatorField> accumulators;

  public static EventReportMessage decodeBody(Queue<Byte> messageBytes, ServiceType serviceType) {

    DateTimeField updateTime = DateTimeField.decode(messageBytes);
    DateTimeField timeOfFix = DateTimeField.decode(messageBytes);
    CoordinateField latitude = CoordinateField.decode(messageBytes);
    CoordinateField longitude = CoordinateField.decode(messageBytes);
    SignedIntegerField altitude = SignedIntegerField.decode(messageBytes);
    SignedIntegerField speed = SignedIntegerField.decode(messageBytes);
    SignedShortField heading = SignedShortField.decode(messageBytes);
    UnsignedShortField satellitesCount = UnsignedShortField.decode(messageBytes);
    FixStatusField fixStatus = FixStatusField.decode(messageBytes);
    UnsignedIntegerField carrierId = UnsignedIntegerField.decode(messageBytes);
    SignedShortField rssi = SignedShortField.decode(messageBytes);
    CommStatusField commStatus = CommStatusField.decode(messageBytes);
    HdopField hdopField = HdopField.decode(messageBytes);
    InputField inputField = InputField.decode(messageBytes);
    UnitStatusField untiStatusField = UnitStatusField.decode(messageBytes);
    UnsignedShortField eventIndex = UnsignedShortField.decode(messageBytes);
    UnsignedShortField eventCode = UnsignedShortField.decode(messageBytes);

    UnsignedShortField numberOfAccumulators = UnsignedShortField.decode(messageBytes);
    messageBytes.poll(); // Throw away 'append' byte.
    List<AccumulatorField> accumulatorFields = new ArrayList<>();
    for (int i = 0; i < numberOfAccumulators.getValue(); i++) {
      accumulatorFields.add(AccumulatorField.decode(messageBytes));
    }

    return new EventReportMessage(serviceType, updateTime, timeOfFix, latitude, longitude, altitude, speed, heading, satellitesCount, fixStatus,
        carrierId, rssi, commStatus, hdopField, inputField, untiStatusField, eventIndex, eventCode, accumulatorFields);
  }

  @Override
  public ServiceType getServiceType() {
    return serviceType;
  }

  @Override
  public MessageType getMessageType() {
    return MessageType.EVENT_REPORT_MESSAGE;
  }

  @Override
  public byte[] encodeBody() {
    List<Byte> messageBytes = new ArrayList<>();

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
    add(messageBytes, eventIndex.encode());
    add(messageBytes, eventCode.encode());
    add(messageBytes, new byte[] {(byte) accumulators.size()});
    messageBytes.add(new Byte((byte) 0x00)); // Append byte.
    for (AccumulatorField accumulator : accumulators) {
      add(messageBytes, accumulator.encode());
    }

    Byte[] bytes = messageBytes.toArray(new Byte[messageBytes.size()]);
    return ArrayUtils.toPrimitive(bytes);
  }

  private void add(List<Byte> bytes, byte[] toAdd) {
    bytes.addAll(Arrays.asList(ArrayUtils.toObject(toAdd)));
  }

}
