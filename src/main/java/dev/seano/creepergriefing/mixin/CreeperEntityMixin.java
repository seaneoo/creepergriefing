package dev.seano.creepergriefing.mixin;

import dev.seano.creepergriefing.CreeperGriefing;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity {

    protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    protected abstract void spawnEffectsCloud();

    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void explode(CallbackInfo ci) {
        if (!getWorld().isClient() && getWorld() instanceof ServerWorld serverWorld) {
            if (!serverWorld.getGameRules().getBoolean(CreeperGriefing.Companion.getGameRuleCreeperGriefing())) {
                ci.cancel();
                dead = true;
                serverWorld.createExplosion(this, this.getX(), this.getY(), this.getZ(), 0, World.ExplosionSourceType.MOB);
                spawnEffectsCloud();
                onRemoval(serverWorld, RemovalReason.KILLED);
                discard();
            }
        }
    }
}
