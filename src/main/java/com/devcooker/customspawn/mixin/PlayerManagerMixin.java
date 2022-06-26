package com.devcooker.customspawn.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {

    @Inject(method = "onPlayerConnect(Lnet/minecraft/network/ClientConnection;Lnet/minecraft/server/network/ServerPlayerEntity;)V",
    at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;setWorld(Lnet/minecraft/server/world/ServerWorld;)V", shift = At.Shift.AFTER))
    public void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, CallbackInfo info){
        ServerWorld currentWorld = player.getWorld();
        System.out.println(currentWorld.getRegistryKey().getValue().getNamespace());
    }

}
