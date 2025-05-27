package dev.prangell.lumicode.init;

import dev.prangell.lumicode.Lumicode;
import dev.prangell.lumicode.item.RankDebugItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.LinkedHashMap;
import java.util.Map;

public class LumicodeItems {
    protected static final Map<Item, Identifier> ITEMS = new LinkedHashMap();
    public static final Item DEBUG_RANK_ITEM;

    public static void init() {
        ITEMS.forEach((item, id) -> {
            Registry.register(Registries.ITEM, id, item);
        });
    }

    protected static <T extends Item> T register(String name, T item) {
        ITEMS.put(item, Lumicode.id(name));
        return item;
    }

    public LumicodeItems() {
    }

    static {
        DEBUG_RANK_ITEM = register((String) "debug_rank_item", (Item) (new RankDebugItem(new FabricItemSettings().maxCount(1))));
    }
}
