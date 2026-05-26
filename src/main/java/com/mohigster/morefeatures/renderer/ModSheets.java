package com.mohigster.morefeatures.renderer;

import net.minecraft.client.renderer.SpriteMapper;
import net.minecraft.client.resources.model.sprite.SpriteId;

import static net.minecraft.client.renderer.Sheets.SHIELD_SHEET;


public class ModSheets {
    public static final SpriteMapper CARBON_SHIELD_MAPPER = new SpriteMapper(SHIELD_SHEET, "entity/shield");
    public static final SpriteId CARBON_SHIELD_BASE = CARBON_SHIELD_MAPPER.defaultNamespaceApply("carbon_shield_base");
    public static final SpriteId CARBON_SHIELD_BASE_NO_PATTERN = CARBON_SHIELD_MAPPER.defaultNamespaceApply("carbon_shield_base_nopattern");
}
