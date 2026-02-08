package net.kuko.fisch.item

import net.kuko.fisch.Fisch
import net.kyori.adventure.platform.fabric.FabricClientAudiences
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World


class NewItem(settings: Settings) : Item(settings) {
    // change
    val energy_val = 0;


    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack?>? {
        if (world!!.isClient()) {
            val mm: MiniMessage = MiniMessage.miniMessage()
            val parsed = mm.deserialize("Hello <rainbow>world</rainbow>, isn't <underlined>MiniMessage</underlined> fun?")
            val text = FabricClientAudiences.of().toNative(parsed)
            user!!.sendMessage(text);
        }
        Fisch.LOGGER.info("Sent text!")

        return TypedActionResult.success(user?.mainHandStack)
    }

    companion object {
        val MM: MiniMessage = MiniMessage.miniMessage()
        val audience = FabricClientAudiences.of()
    }


    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        super.appendTooltip(stack, world, tooltip, context)
//        val rainbowPhase = TooltipState.phaseAcc.toInt()
//        val parsed = MM.deserialize("Energy: <rainbow:$rainbowPhase>${energy_val}</rainbow> <color:#54daf4>E</color>")
//        tooltip?.add(audience.toNative(parsed))
    }
}

