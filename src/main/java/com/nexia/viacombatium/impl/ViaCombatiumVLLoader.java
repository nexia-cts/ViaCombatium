package com.nexia.viacombatium.impl;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.VersionProvider;
import com.viaversion.viaversion.protocol.version.BaseVersionProvider;
import net.raphimc.viaaprilfools.api.VAFServerVersionProvider;
import net.raphimc.vialoader.impl.viaversion.VLLoader;

public class ViaCombatiumVLLoader extends VLLoader {
    @Override
    public void load() {
        Via.getManager().getProviders().use(VersionProvider.class, new VAFServerVersionProvider(new BaseVersionProvider()));
    }
}
