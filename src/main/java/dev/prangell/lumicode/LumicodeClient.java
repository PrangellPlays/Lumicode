package dev.prangell.lumicode;

import dev.prangell.lumicode.util.LumicodeRegistries;
import net.fabricmc.api.ClientModInitializer;

public class LumicodeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LumicodeRegistries.clientInit();
    }
}
