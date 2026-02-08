package net.kuko.fisch.client.screen

import io.wispforest.owo.ui.base.BaseOwoScreen
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.*
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text

class TheScreen : BaseOwoScreen<FlowLayout>() {
    override fun createAdapter(): OwoUIAdapter<FlowLayout?> {
        return OwoUIAdapter.create(this, Containers::verticalFlow)
    }

    override fun getTitle(): Text? {
        return Text.literal("TheScreen")
    }

    override fun build(rootComponent: FlowLayout?) {
        rootComponent?.surface(Surface.VANILLA_TRANSLUCENT)
            ?.horizontalAlignment(HorizontalAlignment.CENTER)
            ?.verticalAlignment(VerticalAlignment.CENTER)

        val gridSize = 8  // or calculate based on some logic
        val grid = Containers.grid(
            Sizing.content(),
            Sizing.content(),
            gridSize,
            gridSize
        )

        rootComponent?.child(grid)
    }

    override fun close() {
        val mc: MinecraftClient = MinecraftClient.getInstance()
        mc.setScreen(null)
    }
}
