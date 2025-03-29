package com.example.mixin;

import com.example.ExampleMod;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentHelperMixin {
	@Inject(method = "isAcceptableItem", at = @At("HEAD"), cancellable = true)
	private void allowInfinityOnTotem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
		Enchantment currentEnchantment = (Enchantment) (Object) this;
		if(ExampleMod.isSameEnchantment(currentEnchantment, Enchantments.INFINITY)) {
			if(stack.getItem() == Items.TOTEM_OF_UNDYING) {
				cir.setReturnValue(true);
			}
		}
	}
}