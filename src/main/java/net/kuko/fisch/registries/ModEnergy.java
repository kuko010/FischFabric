package net.kuko.fisch.registries;

import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.SimpleEnergyItem;

public class ModEnergy {
    public static void register() {
        // Register the wand as an energy item
        EnergyStorage.ITEM.registerForItems((stack, ctx) ->
                SimpleEnergyItem.createStorage(ctx,
                        ((SimpleEnergyItem) stack.getItem()).getEnergyCapacity(stack),
                        ((SimpleEnergyItem) stack.getItem()).getEnergyMaxInput(stack),
                        ((SimpleEnergyItem) stack.getItem()).getEnergyMaxOutput(stack)
                ), ModItems.FluxControl);
    }
}
