package dev.prangell.lumicode.mixin.client.screenshake;

import dev.prangell.lumicode.Lumicode;
import dev.prangell.lumicode.client.screenshake.ScreenshakeHandler;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
final class MinecraftClientMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void lumicode$clientTick(CallbackInfo ci) {
        ScreenshakeHandler.clientTick(MinecraftClient.getInstance().gameRenderer.getCamera(), Lumicode.RANDOM);
    }
}