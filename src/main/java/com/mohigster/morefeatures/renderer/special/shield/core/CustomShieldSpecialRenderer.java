package com.mohigster.morefeatures.renderer.special.shield.core;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.object.equipment.ShieldModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

public class CustomShieldSpecialRenderer implements SpecialModelRenderer<DataComponentMap> {
    private final SpriteGetter sprites;
    private final ShieldModel model;
    private final Identifier baseTexture;
    private final Identifier baseNoPatternTexture;

    public CustomShieldSpecialRenderer(
            final SpriteGetter sprites,
            final ShieldModel model,
            String shieldName,
            String modId
    ) {
        this(sprites, model,
                Identifier.fromNamespaceAndPath(modId, getPath(false, shieldName)),
                Identifier.fromNamespaceAndPath(modId, getPath(true, shieldName))
        );
    }

    public CustomShieldSpecialRenderer(
            final SpriteGetter sprites,
            final ShieldModel model,
            Identifier baseTexture,
            Identifier baseNoPatternTexture
    ) {
        this.sprites = sprites;
        this.model = model;
        this.baseTexture = baseTexture;
        this.baseNoPatternTexture = baseNoPatternTexture;
    }

    public final SpriteId customShieldBase() {
        return new SpriteId(Identifier.withDefaultNamespace("textures/atlas/shield_patterns.png"),
                this.getTexture(false));
    }

    public final SpriteId customShieldBaseNoPattern() {
        return new SpriteId(Identifier.withDefaultNamespace("textures/atlas/shield_patterns.png"),
                this.getTexture(true));
    }

    protected final SpriteId customShieldTexture(boolean hasPattern) {
        return hasPattern ? this.customShieldBase() : this.customShieldBaseNoPattern();
    }

    @Nullable
    @Override
    public DataComponentMap extractArgument(final ItemStack stack) {
        return stack.immutableComponents();
    }

    @Override
    public void submit(
            final @Nullable DataComponentMap components,
            final PoseStack poseStack,
            final SubmitNodeCollector submitNodeCollector,
            final int lightCoords,
            final int overlayCoords,
            boolean hasFoil, int outlineColor) {
        BannerPatternLayers patterns = components != null
                ? components.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                : BannerPatternLayers.EMPTY;
        DyeColor baseColor = components != null ? components.get(DataComponents.BASE_COLOR) : null;
        boolean hasPatterns = !patterns.layers().isEmpty() || baseColor != null;

        SpriteId base = this.customShieldTexture(hasPatterns);

        submitNodeCollector.submitModel(this.getModel(), Unit.INSTANCE, poseStack, lightCoords, overlayCoords, -1, base, this.getSprites(), outlineColor, null);

        if (hasPatterns) {
            BannerRenderer.submitPatterns(
                    this.getSprites(),
                    poseStack,
                    submitNodeCollector,
                    lightCoords,
                    overlayCoords,
                    this.getModel(),
                    Unit.INSTANCE,
                    false,
                    Objects.requireNonNullElse(baseColor, DyeColor.WHITE),
                    patterns,
                    null
            );
        }
        if (hasFoil) {
            submitNodeCollector.submitModel(
                    this.getModel(), Unit.INSTANCE, poseStack, RenderTypes.entityGlint(), lightCoords, overlayCoords, -1, this.getSprites().get(base), 0, null
            );
        }
    }

    @Override
    public void getExtents (Consumer<Vector3fc> output) {
        PoseStack poseStack = new PoseStack();
        this.model.root().getExtentsForGui(poseStack, output);
    }

    protected Identifier getBaseTexture(){
        return this.baseTexture;
    }

    protected Identifier getBaseNoPatternTexture(){
        return this.baseNoPatternTexture;
    }

    protected Identifier getTexture(boolean noPattern) {
        return noPattern ? this.getBaseNoPatternTexture() : this.getBaseTexture();
    }

    protected SpriteGetter getSprites() {
        return this.sprites;
    }

    protected ShieldModel getModel() {
        return this.model;
    }

    private static String getPath(boolean noPattern, String shieldMaterial){
        if (noPattern){
            return "entity/shield/" + shieldMaterial + "_shield_base_nopattern";
        }

        else return "entity/shield/" + shieldMaterial + "_shield_base";
    }
}
