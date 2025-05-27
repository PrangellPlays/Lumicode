package dev.prangell.lumicode;

import dev.prangell.lumicode.init.LumicodeItems;
import dev.prangell.lumicode.util.LumicodeRegistries;
import dev.prangell.lumicode.util.PlayerURLData;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Lumicode implements ModInitializer {
	public static final String MOD_ID = "lumicode";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Random RANDOM = new Random();

	@Override
	public void onInitialize() {
		LumicodeRegistries.init();
		PlayerURLData.init();
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}