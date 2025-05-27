package dev.prangell.lumicode.mixin.client.pride_beacon;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin({BeaconBlockEntityRenderer.class})
public class BeaconBlockEntityRendererMixin {
    @Unique private static final Object2FloatMap<Block> SATURATIONS = new Object2FloatOpenHashMap();
    @Unique private long cachedTime = -1L;
    @Unique private long cachedLongPos = -1L;
    @Unique private float saturation = -1.0F;
    public BeaconBlockEntityRendererMixin() {
    }

    @Inject(method = {"render(Lnet/minecraft/block/entity/BeaconBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V"}, at = {@At("HEAD")})
    private void lumicode$prideBeam(BeaconBlockEntity beaconBlockEntity, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j, CallbackInfo ci) {
        Block above = beaconBlockEntity.getWorld().getBlockState(beaconBlockEntity.getPos().up()).getBlock();
        if (SATURATIONS.containsKey(above)) {
            this.cachedTime = Math.abs(beaconBlockEntity.getWorld().getTime());
            this.cachedLongPos = Math.abs(beaconBlockEntity.getPos().asLong());
            this.saturation = SATURATIONS.getOrDefault(above, -1.0F);
        }
    }

    @ModifyArg(method = {"render(Lnet/minecraft/block/entity/BeaconBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/entity/BeaconBlockEntityRenderer;renderBeam(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;FJII[F)V"))
    private float[] lumicode$prideBeam(float[] value) {
        if (this.cachedTime >= 0L) {
            int color = MathHelper.hsvToRgb((float)((this.cachedTime + this.cachedLongPos) * 3L % 360L) / 360.0F, this.saturation, 1.0F);
            this.cachedTime = -1L;
            this.cachedLongPos = -1L;
            return new float[]{(float)(color >> 16 & 255) / 255.0F, (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F};
        } else {
            return value;
        }
    }

    static {
        SATURATIONS.put(Blocks.SMALL_AMETHYST_BUD, 0.25F);
        SATURATIONS.put(Blocks.MEDIUM_AMETHYST_BUD, 0.5F);
        SATURATIONS.put(Blocks.LARGE_AMETHYST_BUD, 0.75F);
        SATURATIONS.put(Blocks.AMETHYST_CLUSTER, 1.0F);
    }
}