package com.discernible.handler.header.options;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;

import com.discernible.handler.ByteFieldHandler;
import com.discernible.handler.FieldHandler;
import com.discernible.handler.SocketFieldHandler;
import com.discernible.handler.header.options.extension.OptionExtensionFieldHandler;
import com.discernible.message.Socket;
import com.discernible.message.header.options.ForwardingField;
import com.discernible.message.header.options.MobileIdTypeField;
import com.discernible.message.header.options.OptionsHeader;
import com.discernible.message.header.options.extension.OptionExtension;
import com.discernible.util.ByteUtils;
import com.igormaznitsa.jbbp.JBBPParser;
import com.igormaznitsa.jbbp.io.JBBPBitInputStream;
import com.igormaznitsa.jbbp.model.JBBPFieldStruct;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OptionsHeaderFieldHandler implements FieldHandler<OptionsHeader> {

  private static final JBBPParser HEADER =
      JBBPParser.prepare(
          "bit:1 mobileIdFlag;bit:1 mobileIdTypeFlag;bit:1 authenticationFlag;bit:1 routingFlag;bit:1 forwardingFlag;bit:1 responseRedirectionFlag;bit:1 optionExtension;bit:1;");

  private final ByteFieldHandler byteFieldHandler = new ByteFieldHandler();
  private final MobileIdTypeFieldHandler mobileIdTypeFieldHandler = new MobileIdTypeFieldHandler();
  private final ForwardingFieldHandler forwardingFieldHandler = new ForwardingFieldHandler();
  private final SocketFieldHandler socketFieldHandler = new SocketFieldHandler();
  private final OptionExtensionFieldHandler optionExtensionFieldHandler = new OptionExtensionFieldHandler();

  @Override
  public OptionsHeader decode(JBBPBitInputStream messageBytes) {

    JBBPFieldStruct header = ByteUtils.parse(messageBytes, HEADER);

    byte[] mobileId = null;
    if (ByteUtils.isFlagSet(header, "mobileIdFlag")) {
      mobileId = byteFieldHandler.decode(messageBytes);
    }

    MobileIdTypeField mobileIdTypeField = null;
    if (ByteUtils.isFlagSet(header, "mobileIdTypeFlag")) {
      mobileIdTypeField = mobileIdTypeFieldHandler.decode(messageBytes);
    }

    byte[] authentication = null;
    if (ByteUtils.isFlagSet(header, "authenticationFlag")) {
      authentication = byteFieldHandler.decode(messageBytes);
    }

    byte[] routing = null;
    if (ByteUtils.isFlagSet(header, "routingFlag")) {
      routing = byteFieldHandler.decode(messageBytes);
    }

    ForwardingField forwarding = null;
    if (ByteUtils.isFlagSet(header, "forwardingFlag")) {
      forwarding = forwardingFieldHandler.decode(messageBytes);
    }

    Socket responseRedirection = null;
    if (ByteUtils.isFlagSet(header, "responseRedirectionFlag")) {
      responseRedirection = socketFieldHandler.decode(messageBytes);
    }

    OptionExtension optionExtension = null;
    if (ByteUtils.isFlagSet(header, "optionExtension")) {
      optionExtension = optionExtensionFieldHandler.decode(messageBytes);
    }

    return new OptionsHeader(mobileId, mobileIdTypeField, authentication, routing, forwarding, responseRedirection, optionExtension);
  }

  @Override
  public byte[] encode(OptionsHeader optionsHeader) {

    List<Byte> messageBytes = new ArrayList<>();
    messageBytes.add(buildFlagByte(optionsHeader));

    if (Objects.nonNull(optionsHeader.getMobileId())) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(byteFieldHandler.encode(optionsHeader.getMobileId()))));
    }

    if (Objects.nonNull(optionsHeader.getMobileIdType())) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(mobileIdTypeFieldHandler.encode(optionsHeader.getMobileIdType()))));
    }

    if (Objects.nonNull(optionsHeader.getAuthentication())) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(byteFieldHandler.encode(optionsHeader.getAuthentication()))));
    }

    if (Objects.nonNull(optionsHeader.getRouting())) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(byteFieldHandler.encode(optionsHeader.getRouting()))));
    }

    if (Objects.nonNull(optionsHeader.getForwarding())) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(forwardingFieldHandler.encode(optionsHeader.getForwarding()))));
    }

    if (Objects.nonNull(optionsHeader.getResponseRedirection())) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(socketFieldHandler.encode(optionsHeader.getResponseRedirection()))));
    }

    if (Objects.nonNull(optionsHeader.getOptionExtension())) {
      messageBytes.addAll(Arrays.asList(ArrayUtils.toObject(optionExtensionFieldHandler.encode(optionsHeader.getOptionExtension()))));
    }

    Byte[] bytes = messageBytes.toArray(new Byte[messageBytes.size()]);
    return ArrayUtils.toPrimitive(bytes);
  }

  private byte buildFlagByte(OptionsHeader optionsHeader) {

    StringBuilder bits = new StringBuilder("10000000");

    if (Objects.nonNull(optionsHeader.getMobileId())) {
      bits.replace(7, 8, "1");
    }

    if (Objects.nonNull(optionsHeader.getMobileIdType())) {
      bits.replace(6, 7, "1");
    }

    if (Objects.nonNull(optionsHeader.getAuthentication())) {
      bits.replace(5, 6, "1");
    }

    if (Objects.nonNull(optionsHeader.getRouting())) {
      bits.replace(4, 5, "1");
    }

    if (Objects.nonNull(optionsHeader.getForwarding())) {
      bits.replace(3, 4, "1");
    }

    if (Objects.nonNull(optionsHeader.getResponseRedirection())) {
      bits.replace(2, 3, "1");
    }

    if (Objects.nonNull(optionsHeader.getOptionExtension())) {
      bits.replace(1, 2, "1");
    }

    return (byte) Integer.parseInt(bits.toString(), 2);
  }

}
