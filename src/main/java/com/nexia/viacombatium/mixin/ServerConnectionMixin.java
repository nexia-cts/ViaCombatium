package com.nexia.viacombatium.mixin;

import com.nexia.viacombatium.impl.ViaCombatiumVLPipeline;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.connection.UserConnectionImpl;
import com.viaversion.viaversion.protocol.ProtocolPipelineImpl;
import io.netty.channel.Channel;
import net.raphimc.vialoader.netty.VLLegacyPipeline;
import net.raphimc.vialoader.netty.ViaDecoder;
import net.raphimc.vialoader.netty.ViaEncoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.channels.SocketChannel;

@Mixin(targets = "net.minecraft.server.network.ServerConnectionListener$1")
public class ServerConnectionMixin {
    @Inject(method = "initChannel", at = @At(value = "TAIL"), remap = false)
    private void onInitChannel(Channel channel, CallbackInfo ci) {
        if (channel instanceof SocketChannel) {
            UserConnection user = new UserConnectionImpl(channel);
            new ProtocolPipelineImpl(user);

            //channel.pipeline().addLast(new ViaCombatiumVLPipeline(user, ProtocolVersion.getProtocol(803)));
            channel.pipeline().addBefore("encoder", VLLegacyPipeline.VIA_ENCODER_NAME, new ViaEncoder(user));
            channel.pipeline().addBefore("decoder", VLLegacyPipeline.VIA_DECODER_NAME, new ViaDecoder(user));
        }
    }
}