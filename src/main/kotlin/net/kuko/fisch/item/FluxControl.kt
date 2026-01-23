package net.kuko.fisch.item

import net.minecraft.client.item.TooltipContext
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.world.World
import team.reborn.energy.api.EnergyStorage
import team.reborn.energy.api.base.SimpleEnergyItem

class FluxControl(settings: Settings) : Item(settings), SimpleEnergyItem {

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        if (world?.isClient == true) {
            val energy: Long = getStoredEnergy(stack)    // SimpleEnergyItem method
            val capacity: Long = getEnergyCapacity(stack)
            val percent = if (capacity > 0L) (energy * 100 / capacity) else 0L

            tooltip.add(0, Text.literal("Energy: $energy / $capacity ($percent%)"))
        }
        super.appendTooltip(stack, world, tooltip, context)
    }


    override fun getEnergyCapacity(stack: ItemStack?): Long {
        return 100_000L;
    }

    override fun getEnergyMaxInput(stack: ItemStack?): Long {
        return 100L;
    }

    override fun getEnergyMaxOutput(stack: ItemStack?): Long {
        return 100L;
    }
}
