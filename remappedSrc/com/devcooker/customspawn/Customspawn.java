package com.devcooker.customspawn;

import com.devcooker.customspawn.utils.Refer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customspawn implements ModInitializer {

    public static Logger logger = LoggerFactory.getLogger(Refer.MOD_NAME);

    @Override
    public void onInitialize() {
        //ServerLifecycleEvents.SERVER_STARTING.register();
        ServerPlayConnectionEvents.JOIN.register(new JoinListener());
    }

    public static class JoinListener implements ServerPlayConnectionEvents.Join{

        @Override
        public void onPlayReady(net.minecraft.server.network.ServerPlayNetworkHandler handler, PacketSender sender, net.minecraft.server.MinecraftServer server) {

        }
    }
}
