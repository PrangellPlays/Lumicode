package dev.prangell.lumicode.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class DualHandedSwordItem extends SwordItem {
    public DualHandedSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}
