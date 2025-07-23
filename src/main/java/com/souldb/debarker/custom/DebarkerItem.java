package com.souldb.debarker.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Map;

public class DebarkerItem extends Item {
    private static final Map<Block, Block> LOG_CONVERSIONS =
            Map.ofEntries(
                    Map.entry(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG),
                    Map.entry(Blocks.STRIPPED_OAK_LOG, Blocks.OAK_LOG),
                    Map.entry(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD),
                    Map.entry(Blocks.STRIPPED_OAK_WOOD, Blocks.OAK_WOOD),

                    // Spruce
                    Map.entry(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG),
                    Map.entry(Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_LOG),
                    Map.entry(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD),
                    Map.entry(Blocks.STRIPPED_SPRUCE_WOOD, Blocks.SPRUCE_WOOD),

                    // Birch
                    Map.entry(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG),
                    Map.entry(Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_LOG),
                    Map.entry(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD),
                    Map.entry(Blocks.STRIPPED_BIRCH_WOOD, Blocks.BIRCH_WOOD),

                    // Jungle
                    Map.entry(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG),
                    Map.entry(Blocks.STRIPPED_JUNGLE_LOG, Blocks.JUNGLE_LOG),
                    Map.entry(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD),
                    Map.entry(Blocks.STRIPPED_JUNGLE_WOOD, Blocks.JUNGLE_WOOD),

                    // Acacia
                    Map.entry(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG),
                    Map.entry(Blocks.STRIPPED_ACACIA_LOG, Blocks.ACACIA_LOG),
                    Map.entry(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD),
                    Map.entry(Blocks.STRIPPED_ACACIA_WOOD, Blocks.ACACIA_WOOD),

                    // Dark Oak
                    Map.entry(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG),
                    Map.entry(Blocks.STRIPPED_DARK_OAK_LOG, Blocks.DARK_OAK_LOG),
                    Map.entry(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD),
                    Map.entry(Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.DARK_OAK_WOOD),

                    // Mangrove
                    Map.entry(Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG),
                    Map.entry(Blocks.STRIPPED_MANGROVE_LOG, Blocks.MANGROVE_LOG),
                    Map.entry(Blocks.MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_WOOD),
                    Map.entry(Blocks.STRIPPED_MANGROVE_WOOD, Blocks.MANGROVE_WOOD),

                    // Cherry
                    Map.entry(Blocks.CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG),
                    Map.entry(Blocks.STRIPPED_CHERRY_LOG, Blocks.CHERRY_LOG),
                    Map.entry(Blocks.CHERRY_WOOD, Blocks.STRIPPED_CHERRY_WOOD),
                    Map.entry(Blocks.STRIPPED_CHERRY_WOOD, Blocks.CHERRY_WOOD),

                    // Bamboo (special: blocks not logs)
                    Map.entry(Blocks.BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK),
                    Map.entry(Blocks.STRIPPED_BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK),

                    // Crimson
                    Map.entry(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM),
                    Map.entry(Blocks.STRIPPED_CRIMSON_STEM, Blocks.CRIMSON_STEM),
                    Map.entry(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE),
                    Map.entry(Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.CRIMSON_HYPHAE),

                    // Warped
                    Map.entry(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM),
                    Map.entry(Blocks.STRIPPED_WARPED_STEM, Blocks.WARPED_STEM),
                    Map.entry(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE),
                    Map.entry(Blocks.STRIPPED_WARPED_HYPHAE, Blocks.WARPED_HYPHAE)
            );
    public DebarkerItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if(LOG_CONVERSIONS.containsKey(clickedBlock)) {
            if(!world.isClient()) {
                world.setBlockState(context.getBlockPos(), LOG_CONVERSIONS.get(clickedBlock).getDefaultState());

                context.getStack().damage(1, ((ServerWorld) world), ((ServerPlayerEntity) context.getPlayer()),
                        item -> {
                            assert context.getPlayer() != null;
                            context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND);
                        });

                world.playSound(null, context.getBlockPos(), SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
