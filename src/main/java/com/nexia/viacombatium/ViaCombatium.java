package com.nexia.viacombatium;

import com.nexia.viacombatium.impl.ViaCombatiumVLLoader;
import com.nexia.viacombatium.impl.ViaVersionPlatformImpl;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.api.protocol.version.VersionType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.raphimc.vialoader.ViaLoader;
import net.raphimc.vialoader.impl.platform.ViaAprilFoolsPlatformImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViaCombatium implements ModInitializer {
    public static ProtocolVersion AUTO_DETECT_PROTOCOL = new ProtocolVersion(VersionType.SPECIAL, -2, -1, "Auto Detect (1.7+ servers)", null);
    public static Logger logger = LoggerFactory.getLogger("ViaCombatium");

    @Override
    public void onInitialize() {
        ViaVersionPlatformImpl viaVersionPlatform = new ViaVersionPlatformImpl(FabricLoader.getInstance().getConfigDir().toFile());

        ViaLoader.init(
                viaVersionPlatform,
                new ViaCombatiumVLLoader(),
                null/*ViaInjector*/,
                null/*ViaCommandHandler*/,
                ViaAprilFoolsPlatformImpl::new
        );
        viaVersionPlatform.init();
        ProtocolVersion.register(AUTO_DETECT_PROTOCOL);
        System.out.println(Via.getPlatform().getDump());
    }
}
