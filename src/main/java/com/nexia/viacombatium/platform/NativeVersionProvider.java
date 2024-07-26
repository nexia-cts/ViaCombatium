package com.nexia.viacombatium.platform;

import com.viaversion.viaversion.api.platform.providers.Provider;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;

public interface NativeVersionProvider extends Provider {
    int getNativeServerVersion();

    default ProtocolVersion getNativeServerProtocolVersion() {
        return ProtocolVersion.getProtocol(getNativeServerVersion());
    }
}
