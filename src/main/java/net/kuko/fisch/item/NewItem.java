package net.kuko.fisch.item;

import net.kuko.fisch.Tooltips;
import net.kuko.fisch.Utils;
import net.kyori.adventure.platform.fabric.FabricClientAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class NewItem extends Item {
    public static long energy_val = 0;


    public NewItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient()) {
            MiniMessage mm = MiniMessage.miniMessage();
            Component parsed = mm.deserialize("Hello <rainbow>world</rainbow>, isn't <underlined>MiniMessage</underlined> fun?");
            Text text = FabricClientAudiences.of().toNative(parsed);
            user.sendMessage(text);
        }

        return TypedActionResult.consume(user.getMainHandStack());
    }

    /*
        override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        super.appendTooltip(stack, world, tooltip, context)
//        val rainbowPhase = TooltipState.phaseAcc.toInt()
//        val parsed = MM.deserialize("Energy: <rainbow:$rainbowPhase>${energy_val}</rainbow> <color:#>E</color>")
//        tooltip?.add(audience.toNative(parsed))
    }
     */


    /**
     * The Kotlin port is impossible, let's do it with {@link net.kuko.fisch.Tooltips}
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        var text = Text.empty();
        var rgb = Tooltips.RGBEachLetter(String.valueOf(energy_val));
        text.append("Energy: ");
        text.append(rgb);
        text.append(Text.literal(" E")
                .styled(style -> style.withColor(Utils.packARGB(0x54daf4))));
        tooltip.add(text);
    }
}
