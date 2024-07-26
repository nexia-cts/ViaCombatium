package com.nexia.viacombatium.util;

import com.nexia.viacombatium.ViaCombatium;
import net.fabricmc.loader.api.FabricLoader;
import net.lenni0451.reflect.ClassLoaders;

import java.io.File;

public class ClassLoaderPriorityUtil {
    public static void loadOverridingJars() {
        final File jarsFolder = new File(FabricLoader.getInstance().getConfigDir().toAbsolutePath() + "/jars");
        if (!jarsFolder.exists()) {
            jarsFolder.mkdir();
            return;
        }

        if (jarsFolder.isDirectory()) {
            for (File file : jarsFolder.listFiles()) {
                try {
                    if (file.getName().endsWith(".jar")) {
                        ClassLoaders.loadToFront(file.toURI().toURL());
                        ViaCombatium.logger.warn("Loaded overriding jar {}", file.getName());
                    }
                } catch (Throwable e) {
                    ViaCombatium.logger.error("Failed to load overriding jar {}", file.getName(), e);
                }
            }
        }
    }

}
