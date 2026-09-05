package com.example.docs.datagen;

import com.example.docs.ExampleMod;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ExampleModPaintingVariantProvider extends FabricDynamicRegistryProvider {

	public static final ResourceKey<PaintingVariant> miroma = ResourceKey.create(
			Registries.PAINTING_VARIANT,
			Identifier.fromNamespaceAndPath(ExampleMod.MOD_ID, "miroma")
	);

	public ExampleModPaintingVariantProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(HolderLookup.Provider registries, FabricDynamicRegistryProvider.Entries entries) {
		entries.add(miroma, new PaintingVariant(
				1,
				1,
				Identifier.fromNamespaceAndPath(ExampleMod.MOD_ID, "miroma"),
				Optional.of(Component.literal("A Wise Man").withColor(TextColor.YELLOW)),
				Optional.of(Component.literal("NotNightSky").withColor(TextColor.GRAY))
		));
	}

	@Override
	public String getName() {
		return "Painting Variants Provider";
	}
}
