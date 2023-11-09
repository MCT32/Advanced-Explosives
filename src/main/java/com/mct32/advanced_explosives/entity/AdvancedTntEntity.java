package com.mct32.advanced_explosives.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.world.World;

public class AdvancedTntEntity extends TntEntity {

    public AdvancedTntEntity(EntityType<? extends AdvancedTntEntity> entityType, World world) {
        super(entityType, world);
    }
}
