package dev.prangell.lumicode.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.prangell.lumicode.item.util.CustomColorItem;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({InGameHud.class})
public abstract class InGameHudMixin {
    @Shadow private ItemStack currentStack;
    public InGameHudMixin() {
    }

    @ModifyExpressionValue(method = {"renderHeldItemTooltip"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;", ordinal = 0)})
    private MutableText lumicode$heldTooltipChangeItemNameColor(MutableText mutableText) {
        Item var3 = this.currentStack.getItem();
        if (var3 instanceof CustomColorItem colorItem) {
            return mutableText.setStyle(mutableText.getStyle().withColor(colorItem.getNameColor()));
        } else {
            return mutableText;
        }
    }
}