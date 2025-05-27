package dev.prangell.lumicode.util;

import dev.prangell.lumicode.init.LumicodeItems;
import dev.prangell.lumicode.networking.PositionedScreenshakePacket;
import dev.prangell.lumicode.networking.ScreenshakePacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class LumicodeRegistries {
    public static void init() {
        LumicodeItems.init();
    }

    public static void clientInit() {
        registerClientEvents();
    }

    //server
    private static void registerEntityAttributes() {

    }

    //client
    private static void registerClientEvents() {
        ClientPlayNetworking.registerGlobalReceiver(ScreenshakePacket.SCREENSHAKE_ID, (client, handler, buf, responseSender) -> new ScreenshakePacket(buf).apply(client.getNetworkHandler()));
        ClientPlayNetworking.registerGlobalReceiver(PositionedScreenshakePacket.SCREENSHAKE_ID, (client, handler, buf, responseSender) -> PositionedScreenshakePacket.fromBuf(buf).apply(client.getNetworkHandler()));
    }
}
