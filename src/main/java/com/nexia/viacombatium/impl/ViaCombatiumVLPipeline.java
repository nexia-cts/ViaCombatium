package com.nexia.viacombatium.impl;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import io.netty.channel.ChannelHandler;
import net.raphimc.netminecraft.constants.MCPipeline;
import net.raphimc.vialoader.netty.VLPipeline;
import net.raphimc.vialoader.netty.ViaCodec;

public class ViaCombatiumVLPipeline extends VLPipeline {
    public ViaCombatiumVLPipeline(UserConnection user, ProtocolVersion version) {
        super(user, version);
    }

    @Override
    public ChannelHandler createViaCodec() {
        return new ViaCodec(this.user);
    }

    @Override
    protected String compressionCodecName() {
        return MCPipeline.COMPRESSION_HANDLER_NAME;
    }

    @Override
    protected String packetCodecName() {
        return MCPipeline.PACKET_CODEC_HANDLER_NAME;
    }

    @Override
    protected String lengthCodecName() {
        return MCPipeline.SIZER_HANDLER_NAME;
    }
}
