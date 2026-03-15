package net.kuko.fabish.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.sounds.SoundSource;

public class PewPewItem extends BowItem {
    private final ItemStack arrowStack;
    public PewPewItem(ItemStack arrowStack) {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON));
        this.arrowStack = arrowStack;
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int i) {
        releaseUsing(itemStack, level, livingEntity, 0);
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int i) {
        if (livingEntity instanceof Player player) {
            boolean bl = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, itemStack) > 0;
            ItemStack projectileStack = this.arrowStack.copy();
            if (!projectileStack.isEmpty() || bl) {
                if (projectileStack.isEmpty()) {
                    projectileStack = this.arrowStack.copy();
                }

                int j = this.getUseDuration(itemStack) - i;
                float f = getPowerForTime(j);
                if (!((double)f < 0.1)) {
                    boolean bl2 = bl && projectileStack.is(this.arrowStack.getItem());
                    if (!level.isClientSide) {
                        server(projectileStack, itemStack, level, player, bl2, f);
                    }



                    level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!bl2 && !player.getAbilities().instabuild) {
                        projectileStack.shrink(1);
                        if (projectileStack.isEmpty()) {
                            player.getInventory().removeItem(projectileStack);
                        }
                    }



                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    public void server(ItemStack itemStack2, ItemStack itemStack, Level level, Player player, boolean bl2, float f) {
        ArrowItem arrowItem = (ArrowItem) this.arrowStack.getItem(); /* (ArrowItem) (itemStack2.getItem() instanceof ArrowItem ? itemStack2.getItem() : Items.ARROW); */
        Projectile abstractArrow = arrowItem.createArrow(level, itemStack2, player);
        abstractArrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);

        level.addFreshEntity(abstractArrow);
    }
}