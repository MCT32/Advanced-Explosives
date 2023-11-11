package com.mct32.advanced_explosives.item;

import com.mct32.advanced_explosives.block.RemoteTntBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

import static com.mct32.advanced_explosives.AdvancedExplosives.REMOTE_TNT;

public class RemoteDetonator extends Item {

    public RemoteDetonator(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState blockState = world.getBlockState(pos);

        ItemStack itemStack = context.getStack();

        if (blockState.isOf(REMOTE_TNT)) {
            NbtCompound nbt = itemStack.getOrCreateNbt();
            nbt.putIntArray("position", new int[]{pos.getX(), pos.getY(), pos.getZ()});
            itemStack.setNbt(nbt);

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        NbtCompound nbt = itemStack.getNbt();

        if (nbt == null) return TypedActionResult.fail(itemStack);

        if (user.isSneaking()) {
            nbt.remove("position");
            itemStack.setNbt(nbt);
            return TypedActionResult.success(itemStack);
        }

        if (!nbt.contains("position")) return TypedActionResult.fail(itemStack);

        int[] posArray = nbt.getIntArray("position");
        BlockPos pos = new BlockPos(posArray[0], posArray[1], posArray[2]);
        BlockState blockState = world.getBlockState(pos);

        if (blockState.isOf(REMOTE_TNT)) {
            RemoteTntBlock block = (RemoteTntBlock) blockState.getBlock();
            block.primeTnt(world, pos, user);
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
        }

        nbt.remove("position");
        itemStack.setNbt(nbt);

        return TypedActionResult.success(itemStack);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound nbt = itemStack.getNbt();

        if (nbt == null) return;

        int[] pos = nbt.getIntArray("position");
        if (pos == null || !nbt.contains("position")) return;

        tooltip.add(Text.translatable("item.advanced_explosives.remote_detonator.setting", pos[0], pos[1], pos[2]).formatted(Formatting.GRAY));
    }
}
