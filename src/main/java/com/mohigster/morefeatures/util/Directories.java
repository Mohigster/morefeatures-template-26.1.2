package com.mohigster.morefeatures.util;

import com.mohigster.morefeatures.block.custom.magicblock.MagicBlockTransmutations;
import com.mohigster.morefeatures.block.custom.portal.key.PortalKeyType;
import com.mohigster.morefeatures.core.MFRegistries;
import com.mohigster.morefeatures.events.data.ElytraSpeedBoosts;
import net.minecraft.resources.Identifier;

/**
 * This class contains several String constants that indicate where several data-driven directories are stored.
 * Examples of string constants include the path for {@link MagicBlockTransmutations}, {@link ElytraSpeedBoosts}, etc.
 */
public class Directories {
    public static final String METAL_DETECTOR_PATH = "detector_durability_costs";
    public static final String MAGIC_BLOCK_PATH = "magic_block_transmutations";
    public static final String ELYTRA_PATH = "elytra_speed_boosts";
    public static final String PORTAL_PATH = "portal_destinations";
}
