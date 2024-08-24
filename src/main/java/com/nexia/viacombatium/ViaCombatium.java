package com.nexia.viacombatium;

import com.nexia.viacombatium.impl.ViaCombatiumVLLoader;
import com.viaversion.viaversion.api.Via;
import net.fabricmc.api.ModInitializer;
import net.raphimc.vialoader.ViaLoader;
import net.raphimc.vialoader.impl.platform.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ViaCombatium implements ModInitializer {
    public static final Logger logger = LogManager.getLogger("ViaCombatium");

    @Override
    public void onInitialize() {
        ViaLoader.init(
                null,
                new ViaCombatiumVLLoader(),
                null,
                null,
                ViaBackwardsPlatformImpl::new,
                ViaRewindPlatformImpl::new,
                ViaLegacyPlatformImpl::new,
                ViaAprilFoolsPlatformImpl::new
        );

        Via.getManager().debugHandler().setEnabled(true);
    }
}
