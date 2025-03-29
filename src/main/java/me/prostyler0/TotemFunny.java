package me.prostyler0;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TotemFunny implements ModInitializer {
	public static final String MOD_ID = "totemfunny";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static DynamicRegistryManager dynamicRegistryManager;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			dynamicRegistryManager = server.getRegistryManager();
		});
	}
	public static boolean isSameEnchantment(Enchantment enchantment, RegistryKey<Enchantment> enchantmentRegistryKey) {
		return dynamicRegistryManager.getOptional(RegistryKeys.ENCHANTMENT).get().getEntry(enchantment).
		matchesKey(enchantmentRegistryKey);
	}
}