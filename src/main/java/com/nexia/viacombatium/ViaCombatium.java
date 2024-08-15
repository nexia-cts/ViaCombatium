package com.nexia.viacombatium;

import com.nexia.viacombatium.impl.ViaCombatiumVLLoader;
import net.fabricmc.api.ModInitializer;
import net.raphimc.vialoader.ViaLoader;
import net.raphimc.vialoader.impl.platform.ViaAprilFoolsPlatformImpl;
import net.raphimc.vialoader.impl.platform.ViaBackwardsPlatformImpl;
import java.util.logging.Logger;

public class ViaCombatium implements ModInitializer {
    public static final Logger logger = Logger.getLogger("ViaCombatium");

    @Override
    public void onInitialize() {
        ViaLoader.init(
                null,
                new ViaCombatiumVLLoader(),
                null/*ViaInjector*/,
                null/*ViaCommandHandler*/,
                ViaBackwardsPlatformImpl::new,
                ViaAprilFoolsPlatformImpl::new
        );
    }
}
