package com.nexia.viacombatium.impl;

import com.nexia.viacombatium.ViaCombatium;
import com.nexia.viacombatium.platform.NativeVersionProvider;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.api.protocol.version.VersionProvider;
import com.viaversion.viaversion.protocol.version.BaseVersionProvider;
import net.raphimc.vialoader.impl.viaversion.VLLoader;

public class ViaCombatiumVLLoader extends VLLoader {
    @Override
    public void load() {
        super.load();

        Via.getManager().getProviders().use(VersionProvider.class, new BaseVersionProvider() {
            @Override
            public ProtocolVersion getClosestServerProtocol(UserConnection connection) throws Exception {
                NativeVersionProvider natProvider = Via.getManager().getProviders().get(NativeVersionProvider.class);
                if (natProvider != null) {
                    return natProvider.getNativeServerProtocolVersion();
                }
                return super.getClosestServerProtocol(connection);
            }
        });
    }
}
