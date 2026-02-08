package net.kuko.fisch;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

// wdiscute's LibTooltips ported to Fabric
public class Tooltips implements ClientModInitializer  {
    public static float hue;

    @Override
    public void onInitializeClient() {
        // modify lines (List<Text>) here
        ItemTooltipCallback.EVENT.register(Tooltips::modifyItemTooltip);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Called every client tick
            renderFrame();
        });
    }

    public static void renderFrame() {
        Tooltips.hue += 0.006f;
    }
    public static void modifyItemTooltip(ItemStack stack, TooltipContext context, List<Text> tooltipComponents) {
        Identifier rl = Registries.ITEM.getId(stack.getItem());
        String namespace = rl.getNamespace();
        String path = rl.getPath();
        String baseTooltip = "tooltip." + namespace + "." + path;
        String baseTooltipNoShift = "tooltip.always." + namespace + "." + path;

        if (I18n.hasTranslation(baseTooltip + ".name")) {
            tooltipComponents.removeFirst();
            tooltipComponents.addFirst(decodeTranslationKey(baseTooltip + ".name"));
        }

        if (I18n.hasTranslation(baseTooltipNoShift + ".0")) {
            for (int i = 0; i < 100; i++) {
                if (!I18n.hasTranslation(baseTooltipNoShift + "." + i))
                    break;
                tooltipComponents.add(decodeTranslationKey(baseTooltipNoShift + "." + i));
            }
        }

        if (I18n.hasTranslation(baseTooltip + ".0")) {
            if (Screen.hasShiftDown()) {
                tooltipComponents.add(Text.translatable("tooltip.libtooltips.generic.shift_down"));
                tooltipComponents.add(Text.translatable("tooltip.libtooltips.generic.empty"));

                for (int i = 0; i < 100; i++) {
                    if (!I18n.hasTranslation(baseTooltip + "." + i))
                        break;
                    tooltipComponents.add(decodeTranslationKey(baseTooltip + "." + i));
                }
            } else {
                tooltipComponents.add(Text.translatable("tooltip.libtooltips.generic.shift_up"));
            }
        }
    }


    public static Text decodeTranslationKey(String s) {
        return decodeString(I18n.translate(s));
    }

    public static Text decodeString(String s) {
        MutableText component = Text.empty();

        //transform all <rgb> and <gradient> into it's corresponding things
        for (int i = 0; i < 100; i++) {
            if (s.indexOf("<rgb>") < s.indexOf("<gradient-")) {
                if (s.contains("<rgb>") && s.contains("</rgb>")) {
                    component.append(Text.literal(s.substring(0, s.indexOf("<rgb>"))));
                    component.append(Tooltips.RGBEachLetter(s.substring(s.indexOf("<rgb>") + 5, s.indexOf("</rgb>"))));
                    s = s.substring(s.indexOf("</rgb>") + 6);
                    continue;
                }
            }

            if (s.indexOf("<gradient-") < s.indexOf("<rgb>")) {
                if (s.contains("<gradient-") && s.contains("</gradient-")) {
                    float min = Float.parseFloat("0." + s.substring(s.indexOf("<gradient") + 10, s.indexOf("<gradient") + 12));
                    float max = Float.parseFloat("0." + s.substring(s.indexOf("</gradient") + 11, s.indexOf("</gradient") + 13));

                    component.append(Text.literal(s.substring(0, s.indexOf("<gradient"))));
                    component.append(Tooltips.gradient(s.substring(s.indexOf("<gradient") + 13, s.indexOf("</gradient")), min, max));
                    s = s.substring(s.indexOf("</gradient-") + 14);
                    continue;
                }
            }

            if (s.contains("<rgb>") && s.contains("</rgb>")) {
                component.append(Text.literal(s.substring(0, s.indexOf("<rgb>"))));
                component.append(Tooltips.RGBEachLetter(s.substring(s.indexOf("<rgb>") + 5, s.indexOf("</rgb>"))));
                s = s.substring(s.indexOf("</rgb>") + 6);
                continue;
            }

            if (s.contains("<gradient-") && s.contains("</gradient-")) {
                float min = Float.parseFloat("0." + s.substring(s.indexOf("<gradient") + 10, s.indexOf("<gradient") + 12));
                float max = Float.parseFloat("0." + s.substring(s.indexOf("</gradient") + 11, s.indexOf("</gradient") + 13));

                component.append(Text.literal(s.substring(0, s.indexOf("<gradient"))));
                component.append(Tooltips.gradient(s.substring(s.indexOf("<gradient") + 13, s.indexOf("</gradient")), min, max));
                s = s.substring(s.indexOf("</gradient-") + 14);
                continue;
            }

            component.append(s);
            break;

        }

        return component;
    }

    public static Text gradient(String text, float min, float max) {
        MutableText c = Text.empty();

        for (int i = 0; i < text.length(); i++) {
            String s = text.substring(i, i + 1);
            float pingPongedHue = mapHuePingPong(i * 0.01f + hue, min, max);
            int color = hueToRGBInt(pingPongedHue);
           Text l = Text.literal(s).setStyle(Text.literal(s).getStyle().withColor(color));
            c.append(l);
        }

        return c;
    }

    public static float mapHuePingPong(float h, float min, float max) {
        float t = Math.abs(2f * (h % 1) - 1f);
        return min + t * (max - min);
    }

    public static Text RGBEachLetter(String text) {
        return RGBEachLetter(text, 0.01f);
    }

    public static Text RGBEachLetter(String text, float speed) {
        MutableText c = Text.empty();

        for (int i = 0; i < text.length(); i++) {
            String s = text.substring(i, i + 1);

            int color = hueToRGBInt(i * speed + hue);

            Text l = Text.literal(s).setStyle(Text.literal(s).getStyle().withColor(color));;

            c.append(l);
        }

        return c;
    }


    public static int hueToRGBInt(float hue) {
        int r = 0, g = 0, b = 0;

        float h = (hue - (float) Math.floor(hue)) * 6.0f;
        float f = h - (float) Math.floor(h);
        float q = 1.0f - f;
        float t = 1.0f - (1.0f - f);
        switch ((int) h) {
            case 0:
                r = (int) (255.0f + 0.5f);
                g = (int) (t * 255.0f + 0.5f);
                break;
            case 1:
                r = (int) (q * 255.0f + 0.5f);
                g = (int) (255.0f + 0.5f);
                break;
            case 2:
                g = (int) (255.0f + 0.5f);
                b = (int) (t * 255.0f + 0.5f);
                break;
            case 3:
                g = (int) (q * 255.0f + 0.5f);
                b = (int) (255.0f + 0.5f);
                break;
            case 4:
                r = (int) (t * 255.0f + 0.5f);
                g = 0;
                b = (int) (255.0f + 0.5f);
                break;
            case 5:
                r = (int) (255.0f + 0.5f);
                g = 0;
                b = (int) (q * 255.0f + 0.5f);
                break;
        }
        return 0xff000000 | (r << 16) | (g << 8) | (b);
    }

}
