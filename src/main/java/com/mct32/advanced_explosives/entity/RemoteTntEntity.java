package com.mct32.advanced_explosives.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class RemoteTntEntity extends AdvancedTntEntity {
    public RemoteTntEntity(EntityType<? extends RemoteTntEntity> entityType, World world) {
        super(entityType, world);
    }
}
