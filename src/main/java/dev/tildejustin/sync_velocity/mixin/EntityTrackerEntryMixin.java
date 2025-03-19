package dev.tildejustin.sync_velocity.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.*;
import net.minecraft.network.Packet;
import net.minecraft.server.network.EntityTrackerEntry;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Consumer;

@Mixin(EntityTrackerEntry.class)
public abstract class EntityTrackerEntryMixin {
    @Shadow
    protected abstract void sendSyncPacket(Packet<?> packet);

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 2))
    private void syncVelocity(Consumer<?> instance, Object object, Operation<Void> original) {
        this.sendSyncPacket((Packet<?>) object);
    }
}
