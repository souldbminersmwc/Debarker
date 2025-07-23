package com.souldb.debarker;

import com.souldb.debarker.custom.DebarkerItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item DEBARKER_ITEM = registerItem(new DebarkerItem(new Item.Settings().maxDamage(512).maxCount(1)));

    private static Item registerItem(Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Debarker.MOD_ID, "debarker"), item);
    }
    public static void registerModItems() {
        Debarker.LOGGER.info("Registering items for Debarker...");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(fabricItemGroupEntries -> {
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(DEBARKER_ITEM);
        });

    }
}
