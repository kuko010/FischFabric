package net.kuko.fisch.mixin;

import net.kuko.fisch.interfs.LivingEntityFreeze;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(value = LivingEntity.class, priority = 2000)
public class LivingEntityFreezeMixin implements LivingEntityFreeze {
    @Unique
    private boolean frozen = false;
    @Unique
    private Vec3d frozenPos = null;
    @Unique
    private float frozenYaw = 0.0F;
    @Unique
    private float frozenPitch = 0.0F;

    @Unique
    public boolean isFrozen() {
        return this.frozen;
    }

    @Unique
    public void toggleFrozen() {
        LivingEntity self = (LivingEntity)(Object)this;
        if (!this.frozen) {
            this.frozen = true;
            this.frozenPos = self.getPos();
            this.frozenYaw = self.getYaw();
            this.frozenPitch = self.getPitch();
        } else {
            this.frozen = false;
            this.frozenPos = null;
        }

    }

    @Inject(
            method = {"tickMovement"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void freezeMovement(CallbackInfo ci) {
        if (this.frozen) {
            LivingEntity self = (LivingEntity) (Object) this;
            if (this.frozenPos == null) {
                this.frozen = false;
            } else {

                self.setVelocity(Vec3d.ZERO);
                self.velocityDirty = true;
                self.setYaw(this.frozenYaw);
                self.setPitch(this.frozenPitch);
                self.setOnGround(true);

                ci.cancel(); // VERY IMPORTANT (Sure, old gpt.)
            }
        }
    }
}
