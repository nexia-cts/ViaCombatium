package com.nexia.viacombatium.mixin;

import com.nexia.viacombatium.impl.ViaCombatiumVLPipeline;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.connection.UserConnectionImpl;
import com.viaversion.viaversion.protocol.ProtocolPipelineImpl;
import io.netty.channel.Channel;
import net.minecraft.network.Connection;
import net.raphimc.vialoader.netty.CompressionReorderEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.channels.SocketChannel;

@Mixin(Connection.class)
public class ConnectionMixin {
    @Shadow private Channel channel;

    @Inject(method = "setupCompression", at = @At("RETURN"))
    public void reorderCompression(int i, CallbackInfo ci) {
        channel.pipeline().fireUserEventTriggered(CompressionReorderEvent.INSTANCE);

        if (channel instanceof SocketChannel) {
            UserConnection user = new UserConnectionImpl(channel);
            new ProtocolPipelineImpl(user);

            channel.pipeline().addLast(new ViaCombatiumVLPipeline(user, user.getProtocolInfo().serverProtocolVersion()));
        }
    }
}
