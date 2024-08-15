package com.nexia.viacombatium.mixin;

import io.netty.channel.Channel;
import net.minecraft.network.Connection;
import net.raphimc.vialoader.netty.CompressionReorderEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Connection.class)
public class ConnectionMixin {
    @Shadow private Channel channel;

    @Inject(method = "setupCompression", at = @At("RETURN"))
    private void reorderCompression(int i, CallbackInfo ci) {
        channel.pipeline().fireUserEventTriggered(CompressionReorderEvent.INSTANCE);
    }
}
