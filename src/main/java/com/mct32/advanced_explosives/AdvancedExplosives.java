package com.mct32.advanced_explosives;

import com.mct32.advanced_explosives.block.InstantTntBlock;
import com.mct32.advanced_explosives.block.RemoteTntBlock;
import com.mct32.advanced_explosives.entity.InstantTntEntity;
import com.mct32.advanced_explosives.entity.RemoteTntEntity;
import com.mct32.advanced_explosives.item.RemoteDetonator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AdvancedExplosives implements ModInitializer {
    public static final EntityType<InstantTntEntity> INSTANT_TNT_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("advanced_explosives", "instant_tnt"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, InstantTntEntity::new).dimensions(EntityDimensions.fixed(0.98f, 0.98f)).build()
    );

    public static final EntityType<RemoteTntEntity> REMOTE_TNT_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("advanced_explosives", "remote_tnt"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, RemoteTntEntity::new).dimensions(EntityDimensions.fixed(0.98f, 0.98f)).build()
    );

    public static final Block INSTANT_TNT = new InstantTntBlock(AbstractBlock.Settings.copy(Blocks.TNT));
    public static final Block REMOTE_TNT = new RemoteTntBlock(AbstractBlock.Settings.copy(Blocks.TNT));

    public static final Item REMOTE_DETONATOR = new RemoteDetonator(new FabricItemSettings().maxCount(1));

    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK, new Identifier("advanced_explosives", "instant_tnt"), INSTANT_TNT);
        Registry.register(Registries.ITEM, new Identifier("advanced_explosives", "instant_tnt"), new BlockItem(INSTANT_TNT, new FabricItemSettings()));

        Registry.register(Registries.BLOCK, new Identifier("advanced_explosives", "remote_tnt"), REMOTE_TNT);
        Registry.register(Registries.ITEM, new Identifier("advanced_explosives", "remote_tnt"), new BlockItem(REMOTE_TNT, new FabricItemSettings()));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            content.addAfter(Items.TNT, INSTANT_TNT, REMOTE_TNT, REMOTE_DETONATOR);
        });

        Registry.register(Registries.ITEM, new Identifier("advanced_explosives", "remote_detonator"), REMOTE_DETONATOR);
    }
}
