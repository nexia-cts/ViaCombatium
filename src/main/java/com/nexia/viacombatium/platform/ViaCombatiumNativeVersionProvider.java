package com.nexia.viacombatium.platform;

import net.minecraft.SharedConstants;

public class ViaCombatiumNativeVersionProvider implements NativeVersionProvider {
    @Override
    public int getNativeServerVersion() {
        return SharedConstants.getCurrentVersion().getProtocolVersion();
    }
}
