package dev.prangell.lumicode.item;

import dev.prangell.lumicode.item.util.ColouredTooltipItem;
import net.minecraft.item.Item;

public class TooltipColouredItem extends Item implements ColouredTooltipItem {
    public int startcolour;
    public int endcolour;
    public int backgroundcolour;

    public TooltipColouredItem(int startcolour, int endcolour, int backgroundcolour, Settings settings) {
        super(settings);
        this.startcolour = startcolour;
        this.endcolour = endcolour;
        this.backgroundcolour = backgroundcolour;
    }

    @Override
    public int startColor() {
        return startcolour;
    }

    @Override
    public int endColor() {
        return endcolour;
    }

    @Override
    public int backgroundColor() {
        return backgroundcolour;
    }
}