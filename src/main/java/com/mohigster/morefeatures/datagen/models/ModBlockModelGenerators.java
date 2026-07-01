package com.mohigster.morefeatures.datagen.models;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.block.custom.VoidAnchorBlock;
import com.mohigster.morefeatures.block.custom.verticalslab.VerticalSlabBlock;
import com.mohigster.morefeatures.block.custom.verticalslab.VerticalSlabType;
import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.StairsShape;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

import static net.minecraft.client.data.models.BlockModelGenerators.createSimpleBlock;
import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;

public final class ModBlockModelGenerators {
    private static final TextureSlot ALL_SLOT = TextureSlot.create("all");

    private static final ModelTemplate VERTICAL_SLAB_STRAIGHT = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "block/template_vertical_slab")),
            Optional.of("_straight"),
            ALL_SLOT, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_OUTER = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "block/template_vertical_slab_outer")),
            Optional.of("_outer"),
            ALL_SLOT, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_INNER = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "block/template_vertical_slab_inner")),
            Optional.of("_inner"),
            ALL_SLOT, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_STRAIGHT_COLUMN = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "block/template_vertical_slab_column")),
            Optional.of("_straight"),
            TextureSlot.SIDE, TextureSlot.END, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_OUTER_COLUMN = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "block/template_vertical_slab_outer_column")),
            Optional.of("_outer"),
            TextureSlot.SIDE, TextureSlot.END, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_INNER_COLUMN = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "block/template_vertical_slab_inner_column")),
            Optional.of("_inner"),
            TextureSlot.SIDE, TextureSlot.END, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_DOUBLE_COLUMN = new ModelTemplate(
            Optional.of(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "block/template_vertical_slab_double_column")),
            Optional.of("_double"),
            TextureSlot.SIDE, TextureSlot.END, TextureSlot.PARTICLE);

    @SuppressWarnings("deprecation")
    public static void createVerticalSlab(BlockModelGenerators blockModels, Block verticalSlab, Block textureSource) {
        if (textureSource == null){
            throw new IllegalArgumentException("Cannot create a model for " + verticalSlab + ": textureSource must not be null");
        }

        TextureMapping mapping = new TextureMapping()
                .put(ALL_SLOT, TextureMapping.getBlockTexture(textureSource))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(textureSource));

        // Generate the three shaped model files.
        Identifier straightModel = VERTICAL_SLAB_STRAIGHT.create(verticalSlab, mapping, blockModels.modelOutput);
        Identifier outerModel    = VERTICAL_SLAB_OUTER.create(verticalSlab, mapping, blockModels.modelOutput);
        Identifier innerModel    = VERTICAL_SLAB_INNER.create(verticalSlab, mapping, blockModels.modelOutput);

        // DOUBLE = full block; reuse the texture-source block's own model so
        // we don't generate a redundant identical file.
        Identifier fullModel = ModelLocationUtils.getModelLocation(textureSource);

        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(verticalSlab)
                        .with(PropertyDispatch.initial(VerticalSlabBlock.TYPE, VerticalSlabBlock.SHAPE)
                                .generate((type, shape) -> {
                                    // DOUBLE ignores SHAPE entirely.
                                    if (type == VerticalSlabType.DOUBLE) {
                                        return plainVariant(fullModel);
                                    }

                                    Identifier model = switch (shape) {
                                        case STRAIGHT                       -> straightModel;
                                        case OUTER_LEFT, OUTER_RIGHT        -> outerModel;
                                        case INNER_LEFT, INNER_RIGHT        -> innerModel;
                                    };
                                    int yRot = yRotationDegrees(type, shape);

                                    MultiVariant variant = plainVariant(model).with(VariantMutator.UV_LOCK.withValue(true));
                                    if (yRot != 0) {
                                        return variant = variant.with(VariantMutator.Y_ROT.withValue(Quadrant.parseJson(yRot)));
                                    }

                                    return variant ;
                                }))
        );

        blockModels.registerSimpleItemModel(verticalSlab, straightModel);
    }

    @SuppressWarnings("deprecation")
    public static void createSmoothStoneVerticalSlab(BlockModelGenerators blockModels) {
        Block verticalSlab = ModBlocks.SMOOTH_STONE_VERTICAL_SLAB.get();

        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.SIDE,     TextureMapping.getBlockTexture(Blocks.SMOOTH_STONE_SLAB, "_side"))
                .put(TextureSlot.END,      TextureMapping.getBlockTexture(Blocks.SMOOTH_STONE))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(Blocks.SMOOTH_STONE_SLAB, "_side"));

        Identifier straightModel = VERTICAL_SLAB_STRAIGHT_COLUMN.create(verticalSlab, mapping, blockModels.modelOutput);
        Identifier outerModel    = VERTICAL_SLAB_OUTER_COLUMN.create(verticalSlab, mapping, blockModels.modelOutput);
        Identifier innerModel    = VERTICAL_SLAB_INNER_COLUMN.create(verticalSlab, mapping, blockModels.modelOutput);

        Identifier fullModel = VERTICAL_SLAB_DOUBLE_COLUMN.create(verticalSlab, mapping, blockModels.modelOutput);

        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(verticalSlab)
                        .with(PropertyDispatch.initial(VerticalSlabBlock.TYPE, VerticalSlabBlock.SHAPE)
                                .generate((type, shape) -> {
                                    if (type == VerticalSlabType.DOUBLE) {
                                        return plainVariant(fullModel);
                                    }

                                    Identifier model = switch (shape) {
                                        case STRAIGHT                -> straightModel;
                                        case OUTER_LEFT, OUTER_RIGHT -> outerModel;
                                        case INNER_LEFT, INNER_RIGHT -> innerModel;
                                    };

                                    int yRot = yRotationDegrees(type, shape);
                                    MultiVariant variant = plainVariant(model).with(VariantMutator.UV_LOCK.withValue(true));
                                    if (yRot != 0) {
                                        return variant = variant.with(VariantMutator.Y_ROT.withValue(Quadrant.parseJson(yRot)));
                                    }
                                    return variant;
                                })
                        )
        );

        blockModels.registerSimpleItemModel(verticalSlab, straightModel);
    }

    private static int yRotationDegrees(VerticalSlabType type, StairsShape shape) {
        boolean isRight = shape == StairsShape.OUTER_RIGHT || shape == StairsShape.INNER_RIGHT;

        int base = switch (type) {
            case NORTH -> 0;
            case EAST  -> 90;
            case SOUTH -> 180;
            case WEST  -> 270;
            case DOUBLE -> throw new IllegalStateException("DOUBLE has no rotation");
        };

        return isRight ? Math.floorMod(base - 90, 360) : base;
    }

    public static void createAnchor(BlockModelGenerators blockModels, Block anchorBlock){ // Vanilla respawn anchor method is hardcoded for the vanilla anchor, hence the custom method (which allows any block to be passed in as a parameter)
        Material bottom = TextureMapping.getBlockTexture(anchorBlock, "_bottom");
        Material topOff = TextureMapping.getBlockTexture(anchorBlock, "_top_off");
        Material topOn = TextureMapping.getBlockTexture(anchorBlock, "_top");
        Identifier[] chargeLevelModels = new Identifier[5];

        for (int i = 0; i < 5; ++i) {
            TextureMapping mapping = new TextureMapping()
                    .put(TextureSlot.BOTTOM, bottom)
                    .put(TextureSlot.TOP, i == 0 ? topOff : topOn)
                    .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(anchorBlock, "_side" + i));
            chargeLevelModels[i] = ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(anchorBlock, "_" + i, mapping, blockModels.modelOutput);
        }

        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(anchorBlock)
                .with(PropertyDispatch.initial(VoidAnchorBlock.CHARGE)
                        .generate(i -> plainVariant(chargeLevelModels[i]))));
        blockModels.registerSimpleItemModel(anchorBlock, chargeLevelModels[0]);
    }

    // vanilla createNyliumBlock's bottom block texture is hard coded as Netherrack. My Nullium Blocks need an End Stone bottom, thus needing a custom method
    public static void createNyliumLikeBlock(BlockModelGenerators blockModels, Block block, @Nullable Block borrowedBottomTexture){ // Whilst I only ever use this for end stone, passing the bottom block as a parameter is better practice
        // Note that BlockModelGenerators is passed in as a parameter. This instantiates it in a static context which is necessary to call blockStateOutput and modelOutput.
        Material bottom = borrowedBottomTexture != null
                ? TextureMapping.getBlockTexture(borrowedBottomTexture)   // If borrowedBottomTexture isn't null, borrow the specified block's texture to use on the bottom of the block
                : TextureMapping.getBlockTexture(block, "_bottom"); // Otherwise set it to a custom texture

        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, bottom)
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));

        blockModels.blockStateOutput.accept(
                createSimpleBlock(block,
                        plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(
                                block, mapping, blockModels.modelOutput)))
        );
    }

    public static void createAge3Block(BlockModelGenerators blockModels, Block block, boolean useFrostedIceTexture) { // Vanilla's createFrostedIce is hardcoded for the Frosted Ice block *sigh*
        Block textureSource = useFrostedIceTexture ? Blocks.FROSTED_ICE : block;

        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block).with(PropertyDispatch.initial(BlockStateProperties.AGE_3)
                        .select(0, plainVariant(blockModels.createSuffixedVariant(textureSource, "_0", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                        .select(1, plainVariant(blockModels.createSuffixedVariant(textureSource, "_1", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                        .select(2, plainVariant(blockModels.createSuffixedVariant(textureSource, "_2", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                        .select(3, plainVariant(blockModels.createSuffixedVariant(textureSource, "_3", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                )
        );
    }
}
