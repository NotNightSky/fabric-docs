package com.example.docs.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

import java.util.concurrent.CompletableFuture;

public class ExampleModPaintingTagProvider extends FabricTagsProvider<PaintingVariant> {

	public ExampleModPaintingTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, Registries.PAINTING_VARIANT, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider registries) {
		builder(PaintingVariantTags.PLACEABLE)
				.addOptional(ExampleModPaintingVariantProvider.miroma);
	}
}
