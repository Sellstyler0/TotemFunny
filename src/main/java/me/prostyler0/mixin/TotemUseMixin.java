package me.prostyler0.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DeathProtectionComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class TotemUseMixin {

    @Inject(method = "tryUseDeathProtector", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
    private void modifyTotemUse(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        for(Hand hand : Hand.values()) {
            ItemStack itemStack = entity.getStackInHand(hand);
            DeathProtectionComponent deathProtectionComponent = itemStack.get(DataComponentTypes.DEATH_PROTECTION);
            if (deathProtectionComponent == null) continue;
            if (!itemStack.hasEnchantments()) continue;
            itemStack.increment(1);
            break;
        }
    }

}
