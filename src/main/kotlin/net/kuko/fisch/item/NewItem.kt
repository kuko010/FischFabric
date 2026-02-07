package net.kuko.fisch.item

import net.kuko.fisch.Fisch
import net.kyori.adventure.platform.fabric.FabricAudiences
import net.kyori.adventure.platform.fabric.FabricClientAudiences
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World


class NewItem(settings: Settings) : Item(settings) {
    // change


    override fun use(world: World, user: PlayerEntity, hand: Hand?): TypedActionResult<ItemStack?>? {
        if (world.isClient()) {
            val mm: MiniMessage = MiniMessage.miniMessage()
            val parsed = mm.deserialize("Hello <rainbow>world</rainbow>, isn't <underlined>MiniMessage</underlined> fun?")
            val text = FabricClientAudiences.of().toNative(parsed)
            user.sendMessage(text);
        }
        Fisch.LOGGER.info("Sent text!")

        return TypedActionResult.success(user.mainHandStack)
    }
}
