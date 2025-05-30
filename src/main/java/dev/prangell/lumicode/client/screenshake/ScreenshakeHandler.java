package dev.prangell.lumicode.client.screenshake;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class ScreenshakeHandler {
    private static final PerlinNoiseSampler sampler = new PerlinNoiseSampler(Random.create());
    public static final ArrayList<ScreenshakeInstance> INSTANCES = new ArrayList<>();
    public static float intensity;
    public static float yawOffset;
    public static float pitchOffset;

    public static void cameraTick(Camera camera, RandomGenerator random) {
        if (intensity >= 0.1) {
            yawOffset = randomizeOffset(10);
            pitchOffset = randomizeOffset(-10);
            camera.setRotation(camera.getYaw() + yawOffset, camera.getPitch() + pitchOffset);
        }
    }

    public static void clientTick(Camera camera, RandomGenerator random) {
        double sum = Math.min(INSTANCES.stream().mapToDouble(i1 -> i1.updateIntensity(camera, random)).sum(), 1);

        intensity = (float) Math.pow(sum, 3);
        INSTANCES.removeIf(i -> i.progress >= i.duration);
    }

    public static void addScreenshake(ScreenshakeInstance instance) {
        INSTANCES.add(instance);
    }

    public static float randomizeOffset(int offset) {
        float min = -intensity * 2;
        float max = intensity * 2;
        float sampled = (float) sampler.sample((MinecraftClient.getInstance().world.getTime() + MinecraftClient.getInstance().getTickDelta())/intensity, offset, 0) * 1.5f;
        return min >= max ? min : sampled * max;
    }
}