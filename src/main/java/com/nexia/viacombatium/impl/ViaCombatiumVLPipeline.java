package com.nexia.viacombatium.impl;

import com.viaversion.viaversion.api.connection.UserConnection;
import io.netty.channel.ChannelHandler;
import net.raphimc.vialoader.netty.VLPipeline;

public class ViaCombatiumVLPipeline extends VLPipeline {
    public ViaCombatiumVLPipeline(UserConnection user) {
        super(user);
    }

    @Override
    public ChannelHandler createViaCodec() {
        return new ViaCombatiumViaCodec(this.user);
    }

    @Override
    protected String compressionCodecName() {
        return "compression";
    }

    @Override
    protected String packetCodecName() {
        return "packet_handler";
    }

    @Override
    protected String lengthCodecName() {
        return "splitter";
    }
}
