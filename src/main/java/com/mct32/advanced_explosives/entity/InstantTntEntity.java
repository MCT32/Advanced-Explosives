package com.mct32.advanced_explosives.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class InstantTntEntity extends AdvancedTntEntity {
    public InstantTntEntity(EntityType<? extends InstantTntEntity> entityType, World world) {
        super(entityType, world);
        this.setFuse(0);
    }
}
