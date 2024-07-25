package com.nexia.viacombatium;

import com.nexia.viacombatium.impl.CustomVLLoaderImpl;
import net.fabricmc.api.ModInitializer;
import net.raphimc.vialoader.ViaLoader;
import net.raphimc.vialoader.impl.platform.ViaAprilFoolsPlatformImpl;

public class ViaCombatium implements ModInitializer {
    @Override
    public void onInitialize() {
        ViaLoader.init(null/*ViaPlatform*/, new CustomVLLoaderImpl(), null/*ViaInjector*/, null/*ViaCommandHandler*/, ViaAprilFoolsPlatformImpl::new);
    }
}
