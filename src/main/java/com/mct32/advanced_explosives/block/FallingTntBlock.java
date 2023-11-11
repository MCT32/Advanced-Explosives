package com.mct32.advanced_explosives.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.mct32.advanced_explosives.AdvancedExplosives.REMOTE_TNT_ENTITY_TYPE;

public class FallingTntBlock extends FallingBlock {
    public FallingTntBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if(fallingBlockEntity.timeFalling >= 15) {
            world.createExplosion(fallingBlockEntity, fallingBlockEntity.getX(), fallingBlockEntity.getY(), fallingBlockEntity.getZ(), 4.0F, World.ExplosionSourceType.TNT);
        }
    }

    @Override
    public void onDestroyedOnLanding(World world, BlockPos pos, FallingBlockEntity fallingBlockEntity) {
        world.createExplosion(fallingBlockEntity, fallingBlockEntity.getX(), fallingBlockEntity.getY(), fallingBlockEntity.getZ(), 4.0F, World.ExplosionSourceType.TNT);
    }
}
