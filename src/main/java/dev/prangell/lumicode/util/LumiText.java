package dev.prangell.lumicode.util;

import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class LumiText {
    /**
     * Takes a text and returns the same text but with the given int color.
     */
    public static Text withColor(Text text, int color) {
        Style style = text.getStyle().withColor(color);
        List<Text> styled = text.getWithStyle(style);
        if (!styled.isEmpty()) {
            return styled.get(0);
        }
        return text;
    }

    /**
     * Takes a text and returns the same text but with without italics.
     */
    public static Text withoutItalics(Text text) {
        List<Text> styled = text.getWithStyle(text.getStyle().withItalic(false));
        if (!styled.isEmpty()) {
            return styled.get(0);
        }
        return text;
    }

    /**
     * Makes text have a scrolling rainbow effect
     */
    public static long lastTime = System.currentTimeMillis();

    private static final Formatting[] rainbow = {
            Formatting.RED, Formatting.GOLD, Formatting.YELLOW,
            Formatting.GREEN, Formatting.AQUA, Formatting.BLUE,
            Formatting.LIGHT_PURPLE
    };

    public static String colorTransform(String input, Formatting[] colors, double delay, int step, int posstep) {
        StringBuilder sb = new StringBuilder(input.length() * 3);
        if (delay <= 0.0D) delay = 0.001D;

        // Fix: proper parentheses for precedence
        int offset = (int) ((System.currentTimeMillis() - lastTime) / delay) % colors.length;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            int col = ((i * posstep) + colors.length - offset) % colors.length;
            sb.append(colors[col].toString()).append(c);
        }

        return sb.toString();
    }

    public static String makeRainbow(String input) {
        return colorTransform(input, rainbow, 80.0D, 1, 1);
    }
}
