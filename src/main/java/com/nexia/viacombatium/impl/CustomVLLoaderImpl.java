package com.nexia.viacombatium.impl;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.api.protocol.version.VersionProvider;
import com.viaversion.viaversion.protocol.version.BaseVersionProvider;
import net.raphimc.vialoader.impl.viaversion.VLLoader;

public class CustomVLLoaderImpl extends VLLoader {
    @Override
    public void load() {
        super.load();

        Via.getManager().getProviders().use(VersionProvider.class, new BaseVersionProvider() {
            @Override
            public ProtocolVersion getClosestServerProtocol(UserConnection connection) {
                return ProtocolVersion.getProtocol(803);
            }
        });
    }
}
