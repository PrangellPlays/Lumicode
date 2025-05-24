package dev.prangell.lumicode.mixin.server;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.prangell.lumicode.item.util.CustomColorItem;
import dev.prangell.lumicode.util.LumiText;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ItemStack.class})
public abstract class ItemStackMixin {
    public ItemStackMixin() {
    }

    @Shadow
    public abstract Item getItem();

    @Inject(method = {"getName"}, at = {@At("RETURN")}, cancellable = true)
    public void getName(CallbackInfoReturnable<Text> cir) {
        if (this instanceof CustomColorItem colorItem) {
            cir.setReturnValue(LumiText.withColor((Text)cir.getReturnValue(), colorItem.getNameColor()));
        }

    }

    @Inject(method = {"getRarity"}, at = {@At("RETURN")}, cancellable = true)
    public void getRarity(CallbackInfoReturnable<Rarity> cir) {
        if (this.getItem() instanceof CustomColorItem) {
            cir.setReturnValue(Rarity.COMMON);
        }

    }

    @ModifyExpressionValue(method = {"getTooltip"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;", ordinal = 0)})
    private MutableText lumicode$tooltipChangeItemNameColor(MutableText mutableText) {
        Item var3 = this.getItem();
        if (var3 instanceof CustomColorItem colorItem) {
            return mutableText.setStyle(mutableText.getStyle().withColor(colorItem.getNameColor()));
        } else {
            return mutableText;
        }
    }

    @ModifyExpressionValue(method = {"toHoverableText"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;", ordinal = 1)})
    private MutableText lumicode$hoverTextChangeItemNameColor(MutableText mutableText) {
        Item var3 = this.getItem();
        if (var3 instanceof CustomColorItem colorItem) {
            return mutableText.setStyle(mutableText.getStyle().withColor(colorItem.getNameColor()));
        } else {
            return mutableText;
        }
    }
}