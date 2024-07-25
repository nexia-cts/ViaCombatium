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
    public void reorderCompression(int i, CallbackInfo ci) {
        System.out.println("event fired!!1");
        channel.pipeline().fireUserEventTriggered(CompressionReorderEvent.INSTANCE);
    }
}
