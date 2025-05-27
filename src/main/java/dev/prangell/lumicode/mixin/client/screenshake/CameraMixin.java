package dev.prangell.lumicode.mixin.client.screenshake;

import dev.prangell.lumicode.Lumicode;
import dev.prangell.lumicode.client.screenshake.ScreenshakeHandler;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class CameraMixin {
    @Inject(method = "update", at = @At("RETURN"))
    private void lumicode$Screenshake(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        if (1 > 0) {
            ScreenshakeHandler.cameraTick((Camera) (Object) this, Lumicode.RANDOM);
        }
    }
}