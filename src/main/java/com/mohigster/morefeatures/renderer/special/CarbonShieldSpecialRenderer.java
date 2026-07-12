package com.mohigster.morefeatures.renderer.special;

import com.mohigster.morefeatures.references.MFIdentifier;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Transformation;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.geom.ModelLayers;
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
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

public class CarbonShieldSpecialRenderer implements SpecialModelRenderer<DataComponentMap> {
    public static final Transformation DEFAULT_TRANSFORMATION = new Transformation(null, null, new Vector3f(1.0F, -1.0F, -1.0F), null);

    public static final SpriteId CARBON_SHIELD_BASE = new SpriteId(Identifier.withDefaultNamespace("textures/atlas/shield_patterns.png"), MFIdentifier.withMfNamespace("entity/shield/carbon_shield_base"));
    public static final SpriteId CARBON_SHIELD_BASE_NO_PATTERN = new SpriteId(Identifier.withDefaultNamespace("textures/atlas/shield_patterns.png"), MFIdentifier.withMfNamespace("entity/shield/carbon_shield_base_nopattern"));

    private final SpriteGetter sprites;
    private final ShieldModel model;

    public CarbonShieldSpecialRenderer(final SpriteGetter sprites, final ShieldModel model) {
        this.sprites = sprites;
        this.model = model;
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

        SpriteId base = hasPatterns ? CARBON_SHIELD_BASE : CARBON_SHIELD_BASE_NO_PATTERN;

        submitNodeCollector.submitModel(this.model, Unit.INSTANCE, poseStack, lightCoords, overlayCoords, -1, base, this.sprites, outlineColor, null);

        if (hasPatterns) {
            BannerRenderer.submitPatterns(
                    this.sprites,
                    poseStack,
                    submitNodeCollector,
                    lightCoords,
                    overlayCoords,
                    this.model,
                    Unit.INSTANCE,
                    false,
                    Objects.requireNonNullElse(baseColor, DyeColor.WHITE),
                    patterns,
                    null
            );
        }

        if (hasFoil) {
            submitNodeCollector.submitModel(
                    this.model, Unit.INSTANCE, poseStack, RenderTypes.entityGlint(), lightCoords, overlayCoords, -1, this.sprites.get(base), 0, null
            );

        }
    }
    @Override
    public void getExtents (Consumer < Vector3fc > output) {
        PoseStack poseStack = new PoseStack();
        this.model.root().getExtentsForGui(poseStack, output);
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked<DataComponentMap> {
        public static final CarbonShieldSpecialRenderer.Unbaked INSTANCE = new CarbonShieldSpecialRenderer.Unbaked();
        public static final MapCodec<CarbonShieldSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

        @Override
        public MapCodec<CarbonShieldSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        @Override
        public CarbonShieldSpecialRenderer bake(final BakingContext context) {
            // Reuses vanilla ShieldModel geometry but assigns your custom bake layers if needed
            return new CarbonShieldSpecialRenderer(
                    context.sprites(),
                    new ShieldModel(context.entityModelSet().bakeLayer(ModelLayers.SHIELD))
            );
        }
    }
}
