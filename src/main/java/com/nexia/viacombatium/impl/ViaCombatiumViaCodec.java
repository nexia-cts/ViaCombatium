package com.nexia.viacombatium.impl;

import com.nexia.viacombatium.ViaCombatium;
import com.viaversion.viaversion.api.connection.UserConnection;
import io.netty.channel.ChannelHandlerContext;
import net.raphimc.vialoader.netty.ViaCodec;

public class ViaCombatiumViaCodec extends ViaCodec {
    public ViaCombatiumViaCodec(UserConnection user) {
        super(user);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            super.channelRead(ctx, msg);
        } catch (Throwable e) {
            ViaCombatium.logger.error("ProtocolTranslator packet translation error occurred", e);
        }
    }
}
