package net.kuko.fisch.client.screen

import io.wispforest.owo.ui.component.Components
import io.wispforest.owo.ui.container.Containers
import io.wispforest.owo.ui.container.FlowLayout
import io.wispforest.owo.ui.core.HorizontalAlignment
import io.wispforest.owo.ui.core.Sizing
import io.wispforest.owo.ui.core.Surface
import io.wispforest.owo.ui.core.VerticalAlignment
import net.kuko.fisch.Utils

object DeprecatedFeatures {

    private fun oldUI(rootComponent: FlowLayout?) {
        val babyBlueColor = 0x9F92a3f7;

        val babyNeonColorStart = 0x9F33bbff;
        val babyNeonColorEnd = 0x9F3355ff;

        rootComponent?.surface(Surface.VANILLA_TRANSLUCENT)
            ?.horizontalAlignment(HorizontalAlignment.CENTER)
            ?.verticalAlignment(VerticalAlignment.CENTER)

        // Create stack container
        val stack = Containers.stack(Sizing.content(), Sizing.content())

        // Glow Box (BACK)
        stack.child(
            Components.box(Sizing.fill(40), Sizing.fill(78+5))
                .fill(true)
                .startColor(Utils.packARGB(babyNeonColorStart))
                .endColor(Utils.packARGB(babyNeonColorEnd))
//                .positioning(Positioning.absolute(10,10))
        )

        // Main Box (FRONT)
        stack.child(
            Components.box(Sizing.fill(35), Sizing.fill(78))
                .fill(true)
                .color(Utils.packARGB(babyBlueColor))
        )

        rootComponent?.child(stack)
    }
}