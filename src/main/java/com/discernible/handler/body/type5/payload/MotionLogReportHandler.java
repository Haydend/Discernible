package com.discernible.handler.body.type5.payload;

import java.util.Queue;

import com.discernible.message.body.type5.payload.MotionLogReport;

public class MotionLogReportHandler {

  public MotionLogReport decodePayload(Queue<Byte> fieldBytes, int fieldLength) {

    // Byte recordType = fieldBytes.poll(); // 1 byte
    // UnsignedShortFieldHandler groupCount = UnsignedShortFieldHandler.decode(fieldBytes); // 1 byte
    // UnsignedShortFieldHandler messageNumber = UnsignedShortFieldHandler.decode(fieldBytes); // byte
    // UnsignedShortFieldHandler recordSize = UnsignedShortFieldHandler.decode(fieldBytes); // 1 byte
    // UnsignedIntegerFieldHandler numberOfRecords = UnsignedIntegerFieldHandler.decode(fieldBytes); // 2 bytes
    // UnsignedIntegerFieldHandler sampleInterval = UnsignedIntegerFieldHandler.decode(fieldBytes); // 2 bytes
    // DateTimeFieldHandler timeDateStamp = DateTimeFieldHandler.decode(fieldBytes); // 4 bytes
    //
    // for (int i = 0; i < numberOfRecords.getValue(); i++) {
    // CoordinateField latitude = CoordinateField.decode(fieldBytes);
    // CoordinateField longitude = CoordinateField.decode(fieldBytes);
    // UnsignedIntegerFieldHandler speed = UnsignedIntegerFieldHandler.decode(fieldBytes);
    // UnsignedShortFieldHandler heading = UnsignedShortFieldHandler.decode(fieldBytes);
    // UnsignedShortFieldHandler fixStatus = UnsignedShortFieldHandler.decode(fieldBytes);
    // DateTimeFieldHandler timeOfFix = DateTimeFieldHandler.decode(fieldBytes);
    //
    // continue;
    // }


    return null;
  }

  protected byte[] encodePayload(MotionLogReport motionLogReport) {
    return new byte[0];
  }

}
