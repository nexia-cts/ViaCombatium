package com.nexia.viacombatium.impl;

import com.nexia.viacombatium.platform.NativeVersionProvider;
import com.nexia.viacombatium.platform.ViaCombatiumNativeVersionProvider;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;
import com.viaversion.viaversion.api.command.ViaCommandSender;
import com.viaversion.viaversion.api.configuration.ViaVersionConfig;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.platform.ViaPlatform;
import com.viaversion.viaversion.configuration.AbstractViaConfig;
import com.viaversion.viaversion.libs.gson.JsonArray;
import com.viaversion.viaversion.libs.gson.JsonObject;
import com.viaversion.viaversion.util.VersionInfo;
import net.fabricmc.loader.api.FabricLoader;
import net.raphimc.vialoader.ViaLoader;
import net.raphimc.vialoader.commands.UserCommandSender;
import net.raphimc.vialoader.impl.viaversion.VLApiBase;
import net.raphimc.vialoader.impl.viaversion.VLViaConfig;
import net.raphimc.vialoader.util.JLoggerToSLF4J;
import net.raphimc.vialoader.util.VLTask;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ViaVersionPlatformImpl implements ViaPlatform<UserConnection> {

    private static final Logger LOGGER = new JLoggerToSLF4J(LoggerFactory.getLogger("ViaVersion"));

    private final File dataFolder;
    private final AbstractViaConfig config;
    private final ViaAPI<UserConnection> api;

    public ViaVersionPlatformImpl(final File rootFolder) {
        this.dataFolder = new File(rootFolder, "ViaLoader");
        this.config = this.createConfig();
        this.api = this.createApi();
    }

    public void init() {
        // We'll use it early for ViaInjector
        installNativeVersionProvider();
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    public String getPlatformName() {
        return "ViaLoader";
    }

    @Override
    public String getPlatformVersion() {
        return ViaLoader.VERSION;
    }

    @Override
    public String getPluginVersion() {
        return VersionInfo.getVersion();
    }

    @Override
    public VLTask runAsync(Runnable runnable) {
        return new VLTask(Via.getManager().getScheduler().execute(runnable));
    }

    @Override
    public VLTask runRepeatingAsync(Runnable runnable, long period) {
        return new VLTask(Via.getManager().getScheduler().scheduleRepeating(runnable, 0, period * 50, TimeUnit.MILLISECONDS));
    }

    @Override
    public VLTask runSync(Runnable runnable) {
        return this.runAsync(runnable);
    }

    @Override
    public VLTask runSync(Runnable runnable, long delay) {
        return new VLTask(Via.getManager().getScheduler().schedule(runnable, delay * 50, TimeUnit.MILLISECONDS));
    }

    @Override
    public VLTask runRepeatingSync(Runnable runnable, long period) {
        return this.runRepeatingAsync(runnable, period);
    }

    @Override
    public ViaCommandSender[] getOnlinePlayers() {
        return Via.getManager().getConnectionManager().getConnectedClients().values().stream().map(UserCommandSender::new).toArray(ViaCommandSender[]::new);
    }

    @Override
    public void sendMessage(UUID uuid, String msg) {
        if (uuid == null) {
            this.getLogger().info(msg);
        } else {
            this.getLogger().info("[" + uuid + "] " + msg);
        }
    }

    @Override
    public boolean kickPlayer(UUID uuid, String s) {
        return false;
    }

    @Override
    public boolean isPluginEnabled() {
        return true;
    }

    @Override
    public boolean hasPlugin(String s) {
        return false; // Used for ViaPlatform#getUnsupportedSoftwareClasses
    }

    @Override
    public ViaAPI<UserConnection> getApi() {
        return this.api;
    }

    @Override
    public ViaVersionConfig getConf() {
        return this.config;
    }

    @Override
    public File getDataFolder() {
        return this.dataFolder;
    }

    @Override
    public void onReload() {
    }

    protected void installNativeVersionProvider() {
        Via.getManager().getProviders().use(NativeVersionProvider.class, new ViaCombatiumNativeVersionProvider());
    }

    @Override
    public JsonObject getDump() {
        JsonObject platformSpecific = new JsonObject();
        JsonArray mods = new JsonArray();
        FabricLoader.getInstance().getAllMods().stream().map((mod) -> {
            JsonObject jsonMod = new JsonObject();
            jsonMod.addProperty("id", mod.getMetadata().getId());
            jsonMod.addProperty("name", mod.getMetadata().getName());
            jsonMod.addProperty("version", mod.getMetadata().getVersion().getFriendlyString());
            JsonArray authors = new JsonArray();
            mod.getMetadata().getAuthors().stream().map(it -> {
                JsonObject info = new JsonObject();
                JsonObject contact = new JsonObject();
                it.getContact().asMap().entrySet()
                        .forEach(c -> contact.addProperty(c.getKey(), c.getValue()));
                if (contact.size() != 0) {
                    info.add("contact", contact);
                }
                info.addProperty("name", it.getName());

                return info;
            }).forEach(authors::add);
            jsonMod.add("authors", authors);

            return jsonMod;
        }).forEach(mods::add);

        platformSpecific.add("mods", mods);
        NativeVersionProvider ver = Via.getManager().getProviders().get(NativeVersionProvider.class);
        if (ver != null) {
            platformSpecific.addProperty("native version", ver.getNativeServerProtocolVersion().getVersion());
        }
        return platformSpecific;
    }

    protected AbstractViaConfig createConfig() {
        return new VLViaConfig(new File(this.dataFolder, "viaversion.yml"), this.getLogger());
    }

    protected ViaAPI<UserConnection> createApi() {
        return new VLApiBase();
    }

}
