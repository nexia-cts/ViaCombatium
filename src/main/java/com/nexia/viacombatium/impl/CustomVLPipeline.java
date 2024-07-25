package com.nexia.viacombatium.impl;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import net.raphimc.vialoader.netty.VLPipeline;

public class CustomVLPipeline extends VLPipeline {
    public CustomVLPipeline(UserConnection user, ProtocolVersion version) {
        super(user, version);
    }

    @Override
    protected String compressionCodecName() {
        return "compression_codec";
    }

    @Override
    protected String packetCodecName() {
        return "packet_codec";
    }

    @Override
    protected String lengthCodecName() {
        return "length_codec";
    }
}
