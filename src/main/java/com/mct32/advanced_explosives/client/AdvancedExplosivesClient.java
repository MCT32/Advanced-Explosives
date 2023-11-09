package com.mct32.advanced_explosives.client;

import com.mct32.advanced_explosives.AdvancedExplosives;
import com.mct32.advanced_explosives.client.render.entity.AdvancedTntEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import static com.mct32.advanced_explosives.AdvancedExplosives.*;

@Environment(EnvType.CLIENT)
public class AdvancedExplosivesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(INSTANT_TNT_ENTITY_TYPE, (context) -> new AdvancedTntEntityRenderer(context, INSTANT_TNT));
        EntityRendererRegistry.register(REMOTE_TNT_ENTITY_TYPE, (context) -> new AdvancedTntEntityRenderer(context, REMOTE_TNT));

        ModelPredicateProviderRegistry.register(REMOTE_DETONATOR, new Identifier("armed"), ((stack, world, entity, seed) -> {
            NbtCompound nbt = stack.getNbt();

            return nbt == null || !nbt.contains("position") ? 0.0f : 1.0f;
        }));
    }
}
