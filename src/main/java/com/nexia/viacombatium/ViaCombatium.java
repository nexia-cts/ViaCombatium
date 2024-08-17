package com.nexia.viacombatium;

import com.nexia.viacombatium.impl.ViaCombatiumVLLoader;
import net.fabricmc.api.ModInitializer;
import net.raphimc.vialoader.ViaLoader;
import net.raphimc.vialoader.impl.platform.ViaAprilFoolsPlatformImpl;
import net.raphimc.vialoader.impl.platform.ViaBackwardsPlatformImpl;
import net.raphimc.vialoader.impl.platform.ViaLegacyPlatformImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ViaCombatium implements ModInitializer {
    public static final Logger logger = LogManager.getLogger("ViaCombatium");

    @Override
    public void onInitialize() {
        ViaLoader.init(
                null,
                new ViaCombatiumVLLoader(),
                null/*ViaInjector*/,
                null/*ViaCommandHandler*/,
                ViaAprilFoolsPlatformImpl::new,
                ViaBackwardsPlatformImpl::new,
                ViaLegacyPlatformImpl::new
        );
    }
}
