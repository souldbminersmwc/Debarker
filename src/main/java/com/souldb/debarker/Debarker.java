package com.souldb.debarker;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Debarker implements ModInitializer {
    public static final String MOD_ID = "debarker";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        Debarker.LOGGER.info("Initializing Debarker...");
        ModItems.registerModItems();
    }
}
