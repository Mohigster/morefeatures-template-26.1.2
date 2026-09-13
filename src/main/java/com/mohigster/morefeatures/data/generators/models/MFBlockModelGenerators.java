package com.mohigster.morefeatures.data.generators.models;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.custom.VoidAnchorBlock;
import com.mohigster.morefeatures.block.custom.pillar.PillarBlock;
import com.mohigster.morefeatures.block.custom.temporaldilator.TemporalDilatorBlock;
import com.mohigster.morefeatures.block.custom.verticalslab.VerticalSlabBlock;
import com.mohigster.morefeatures.block.custom.verticalslab.VerticalSlabType;
import com.mojang.math.Quadrant;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.StairsShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.mohigster.morefeatures.data.resources.MFIdentifier.withMfNamespace;
import static net.minecraft.client.data.models.BlockModelGenerators.*;

public final class MFBlockModelGenerators {
    private final BlockModelGenerators blockModels;
    private final BiConsumer<Identifier, ModelInstance> modelOutput;
    private final Consumer<BlockModelDefinitionGenerator> blockStateOutput;

    private MFBlockModelGenerators(BlockModelGenerators blockModels, BiConsumer<Identifier, ModelInstance> modelOutput, Consumer<BlockModelDefinitionGenerator> blockStateOutput) {
        this.blockModels = blockModels;
        this.modelOutput = modelOutput;
        this.blockStateOutput = blockStateOutput;
    }

    public static MFBlockModelGenerators create(@NonNull BlockModelGenerators blockModels) {
        return new MFBlockModelGenerators(blockModels, blockModels.modelOutput, blockModels.blockStateOutput);
    }

    private static final TextureSlot ALL_SLOT = TextureSlot.create("all");
    private static final TextureSlot CONNECTOR_SLOT = TextureSlot.create("connector");

    private static final ModelTemplate PILLAR_FULL = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_pillar")),
            Optional.empty(),
            ALL_SLOT, TextureSlot.PARTICLE);

    private static final ModelTemplate PILLAR_BOTTOM = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_pillar_bottom")),
            Optional.of("_bottom"),
            ALL_SLOT, TextureSlot.PARTICLE);

    private static final ModelTemplate PILLAR_TOP = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_pillar_top")),
            Optional.of("_top"),
            ALL_SLOT, TextureSlot.PARTICLE);

    private static final ModelTemplate PILLAR_MIDDLE = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_pillar_middle")),
            Optional.of("_middle"),
            ALL_SLOT, TextureSlot.PARTICLE);

    private static final ModelTemplate TEMPORAL_DILATOR_UP = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_temporal_dilator_up")),
            Optional.of("_up"));

    private static final ModelTemplate TEMPORAL_DILATOR_DOWN = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_temporal_dilator_down")),
            Optional.of("_down"));

    private static final ModelTemplate VERTICAL_SLAB_STRAIGHT = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_vertical_slab")),
            Optional.of("_straight"),
            ALL_SLOT, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_OUTER = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_vertical_slab_outer")),
            Optional.of("_outer"),
            ALL_SLOT, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_INNER = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_vertical_slab_inner")),
            Optional.of("_inner"),
            ALL_SLOT, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_SANDSTONE_STRAIGHT = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_vertical_sandstone_slab")),
            Optional.of("_straight"),
            TextureSlot.SIDE, TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_SANDSTONE_OUTER = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_vertical_sandstone_slab_outer")),
            Optional.of("_outer"),
            TextureSlot.SIDE, TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_SANDSTONE_INNER = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_vertical_sandstone_slab_inner")),
            Optional.of("_inner"),
            TextureSlot.SIDE, TextureSlot.BOTTOM, TextureSlot.TOP, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_STRAIGHT_COLUMN = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_vertical_slab_column")),
            Optional.of("_straight"),
            TextureSlot.SIDE, TextureSlot.END, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_OUTER_COLUMN = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_vertical_slab_outer_column")),
            Optional.of("_outer"),
            TextureSlot.SIDE, TextureSlot.END, CONNECTOR_SLOT, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_INNER_COLUMN = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_vertical_slab_inner_column")),
            Optional.of("_inner"),
            TextureSlot.SIDE, TextureSlot.END, CONNECTOR_SLOT, TextureSlot.PARTICLE);

    private static final ModelTemplate VERTICAL_SLAB_DOUBLE_COLUMN = new ModelTemplate(
            Optional.of(withMfNamespace("block/template_vertical_slab_double_column")),
            Optional.of("_double"),
            TextureSlot.SIDE, TextureSlot.END, TextureSlot.PARTICLE);

    @SuppressWarnings("deprecation")
    public void createVerticalSlab(Block verticalSlab, Block textureSource) {
        if (textureSource == null){
            throw new IllegalArgumentException("Cannot create a model for " + verticalSlab + ": textureSource must not be null");
        }

        TextureMapping mapping = new TextureMapping();

        Identifier straightModel;
        Identifier outerModel;
        Identifier innerModel;

        String suffix = textureSource == Blocks.QUARTZ_BLOCK ? "_side" : textureSource == Blocks.SMOOTH_QUARTZ ? "_bottom" : "";

        if (textureSource == Blocks.SMOOTH_SANDSTONE || textureSource == Blocks.SMOOTH_RED_SANDSTONE){
            Block smoothSource = textureSource == Blocks.SMOOTH_SANDSTONE ? Blocks.SANDSTONE : Blocks.RED_SANDSTONE;
            mapping.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(smoothSource, "_top"));
        } else if (!(suffix.isEmpty())){
            mapping.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(Blocks.QUARTZ_BLOCK, suffix));
        } else{
            mapping.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(textureSource));
        }

        if (textureSource == Blocks.SANDSTONE || textureSource == Blocks.CUT_SANDSTONE ||
                textureSource == Blocks.SMOOTH_SANDSTONE || textureSource == Blocks.RED_SANDSTONE ||
        textureSource == Blocks.CUT_RED_SANDSTONE || textureSource == Blocks.SMOOTH_RED_SANDSTONE){
            Block sourceBlock = textureSource == Blocks.SANDSTONE || textureSource == Blocks.CUT_SANDSTONE
                    ? Blocks.SANDSTONE
                    : textureSource == Blocks.RED_SANDSTONE || textureSource == Blocks.CUT_RED_SANDSTONE
                    ? Blocks.RED_SANDSTONE
                    : textureSource;

            if (textureSource == Blocks.SMOOTH_SANDSTONE || textureSource == Blocks.SMOOTH_RED_SANDSTONE){
                Block smoothSource = textureSource == Blocks.SMOOTH_SANDSTONE ? Blocks.SANDSTONE : Blocks.RED_SANDSTONE;
                mapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(smoothSource, "_top"));
                mapping.put(TextureSlot.TOP, TextureMapping.getBlockTexture(smoothSource, "_top"));
                mapping.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(smoothSource, "_top"));
            } else {
                mapping.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(textureSource));
                mapping.put(TextureSlot.TOP, TextureMapping.getBlockTexture(sourceBlock, "_top"));

                if (!(textureSource == Blocks.SANDSTONE || textureSource == Blocks.RED_SANDSTONE)) {
                    mapping.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(sourceBlock, "_top"));
                } else {
                    mapping.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(sourceBlock, "_bottom"));
                }
            }
            straightModel = VERTICAL_SLAB_SANDSTONE_STRAIGHT.create(verticalSlab, mapping, this.modelOutput);
            outerModel    = VERTICAL_SLAB_SANDSTONE_OUTER.create(verticalSlab, mapping, this.modelOutput);
            innerModel    = VERTICAL_SLAB_SANDSTONE_INNER.create(verticalSlab, mapping, this.modelOutput);

        } else if (!(suffix.isEmpty())){
            mapping.put(ALL_SLOT, TextureMapping.getBlockTexture(Blocks.QUARTZ_BLOCK, suffix));
            straightModel = VERTICAL_SLAB_STRAIGHT.create(verticalSlab, mapping, this.modelOutput);
            outerModel = VERTICAL_SLAB_OUTER.create(verticalSlab, mapping, this.modelOutput);
            innerModel = VERTICAL_SLAB_INNER.create(verticalSlab, mapping, this.modelOutput);
        } else {
            mapping.put(ALL_SLOT, TextureMapping.getBlockTexture(textureSource));
            straightModel = VERTICAL_SLAB_STRAIGHT.create(verticalSlab, mapping, this.modelOutput);
            outerModel    = VERTICAL_SLAB_OUTER.create(verticalSlab, mapping, this.modelOutput);
            innerModel    = VERTICAL_SLAB_INNER.create(verticalSlab, mapping, this.modelOutput);
        }

        // DOUBLE = full block; reuse the texture-source block's own model so
        // we don't generate a redundant identical file.
        Identifier fullModel = ModelLocationUtils.getModelLocation(textureSource);

        this.blockStateOutput.accept(
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
                                        return variant.with(VariantMutator.Y_ROT.withValue(Quadrant.parseJson(yRot)));
                                    }

                                    return variant;
                                }))
        );

        this.blockModels().registerSimpleItemModel(verticalSlab, straightModel);
    }

    public void createPillar(Block pillar, Block textureSource) {
        if (textureSource == null){
            throw new IllegalArgumentException("Cannot create a model for " + pillar + ": textureSource must not be null");
        }

        TextureMapping mapping = new TextureMapping();

        Identifier fullModel;
        Identifier middleModel;
        Identifier topModel;
        Identifier bottomModel;

        mapping.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(textureSource));
        mapping.put(ALL_SLOT, TextureMapping.getBlockTexture(textureSource));
        fullModel      = PILLAR_FULL.create(pillar, mapping, this.modelOutput);
        middleModel    = PILLAR_MIDDLE.create(pillar, mapping, this.modelOutput);
        bottomModel    = PILLAR_BOTTOM.create(pillar, mapping, this.modelOutput);
        topModel       = PILLAR_TOP.create(pillar, mapping, this.modelOutput);

        this.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(pillar)
                        .with(PropertyDispatch.initial(PillarBlock.SHAPE)
                                .generate((shape) -> {

                                    Identifier model = switch (shape) {
                                        case FULL   -> fullModel;
                                        case MIDDLE -> middleModel;
                                        case BOTTOM -> bottomModel;
                                        case TOP    -> topModel;
                                    };

                                    return plainVariant(model).with(VariantMutator.UV_LOCK.withValue(true));
                                })
                        )
        );

        this.blockModels().registerSimpleItemModel(pillar, fullModel);
    }

    public void createTemporalDilator(Block temporalDilator){
        TextureMapping mapping = new TextureMapping();

        Identifier upModel;
        Identifier downModel;

        upModel   = TEMPORAL_DILATOR_UP.create(temporalDilator, mapping, this.modelOutput);
        downModel = TEMPORAL_DILATOR_DOWN.create(temporalDilator, mapping, this.modelOutput);

        this.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(temporalDilator)
                        .with(PropertyDispatch.initial(TemporalDilatorBlock.DIRECTION)
                                .generate(direction -> {
                                    Identifier model = switch (direction) {
                                        case UP   -> upModel;
                                        case DOWN -> downModel;
                                    };

                                    return plainVariant(model);
                                })
                        )
        );

        this.blockModels().registerSimpleItemModel(temporalDilator, upModel);
    }

    @SuppressWarnings("deprecation")
    public void createSmoothStoneVerticalSlab() {
        Block verticalSlab = MFBlocks.SMOOTH_STONE_VERTICAL_SLAB.get();

        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.SIDE,     TextureMapping.getBlockTexture(Blocks.SMOOTH_STONE_SLAB, "_side"))
                .put(TextureSlot.END,      TextureMapping.getBlockTexture(Blocks.SMOOTH_STONE))
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(Blocks.SMOOTH_STONE_SLAB, "_side"));

        TextureMapping outerMapping = mapping.put(CONNECTOR_SLOT, TextureMapping.getBlockTexture(verticalSlab, "_connector_outer")); // Needs connectors because of the rotation
        TextureMapping innerMapping = mapping.put(CONNECTOR_SLOT, TextureMapping.getBlockTexture(verticalSlab, "_connector_inner")); // And the connectors are separate due to separate texture

        Identifier straightModel = VERTICAL_SLAB_STRAIGHT_COLUMN.create(verticalSlab, mapping, this.modelOutput);
        Identifier outerModel    = VERTICAL_SLAB_OUTER_COLUMN.create(verticalSlab, outerMapping, this.modelOutput);
        Identifier innerModel    = VERTICAL_SLAB_INNER_COLUMN.create(verticalSlab, innerMapping, this.modelOutput);

        Identifier fullModel = VERTICAL_SLAB_DOUBLE_COLUMN.create(verticalSlab, mapping, this.modelOutput); // Can't borrow the double smooth stone slab model because it will be rotated incorrectly. Needs its own model

        this.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(verticalSlab)
                        .with(PropertyDispatch.initial(VerticalSlabBlock.TYPE, VerticalSlabBlock.FACING, VerticalSlabBlock.SHAPE)
                                .generate((type, facing, shape) -> {
                                    if (type == VerticalSlabType.DOUBLE) {
                                        int yRot = yRotationForDouble(facing);

                                        MultiVariant variant = plainVariant(fullModel);// No UV_LOCK — stripe must rotate with the block. Unlike above which has one
                                        if (yRot != 0) variant = variant.with(VariantMutator.Y_ROT.withValue(Quadrant.parseJson(yRot)));
                                        return variant;
                                    }

                                    Identifier model = switch (shape) {
                                        case STRAIGHT                -> straightModel;
                                        case OUTER_LEFT, OUTER_RIGHT -> outerModel;
                                        case INNER_LEFT, INNER_RIGHT -> innerModel;
                                    };

                                    int yRot = yRotationDegrees(type, shape);
                                    // No UV_LOCK — stripe must rotate with the block
                                    MultiVariant variant = plainVariant(model);
                                    if (yRot != 0) variant = variant.with(VariantMutator.Y_ROT.withValue(Quadrant.parseJson(yRot)));
                                    return variant;
                                })
                        )
        );

        this.blockModels().registerSimpleItemModel(verticalSlab, straightModel);
    }

    private static int yRotationForDouble(Direction facing) {
        return switch (facing) {
            case NORTH -> 0;
            case EAST  -> 90;
            case SOUTH -> 180;
            case WEST  -> 270;
            default -> throw new IllegalStateException("Unexpected value: " + facing);
        };
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

    public void createAnchor(Block anchorBlock){ // Vanilla respawn anchor method is hardcoded for the vanilla anchor, hence the custom method (which allows any block to be passed in as a parameter)
        Material bottom = TextureMapping.getBlockTexture(anchorBlock, "_bottom");
        Material topOff = TextureMapping.getBlockTexture(anchorBlock, "_top_off");
        Material topOn = TextureMapping.getBlockTexture(anchorBlock, "_top");
        Identifier[] chargeLevelModels = new Identifier[5];

        for (int i = 0; i < 5; ++i) {
            TextureMapping mapping = new TextureMapping()
                    .put(TextureSlot.BOTTOM, bottom)
                    .put(TextureSlot.TOP, i == 0 ? topOff : topOn)
                    .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(anchorBlock, "_side" + i));
            chargeLevelModels[i] = ModelTemplates.CUBE_BOTTOM_TOP.createWithSuffix(anchorBlock, "_" + i, mapping, this.modelOutput);
        }

        this.blockStateOutput.accept(MultiVariantGenerator.dispatch(anchorBlock)
                .with(PropertyDispatch.initial(VoidAnchorBlock.CHARGE)
                        .generate(i -> plainVariant(chargeLevelModels[i]))));
        this.blockModels().registerSimpleItemModel(anchorBlock, chargeLevelModels[0]);
    }

    public void createBerryBush(Block berry, Item berryItem) {
        this.blockModels().registerSimpleFlatItemModel(berryItem);
        this.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(berry).with(PropertyDispatch.initial(
                        BlockStateProperties.AGE_3).generate((age) -> plainVariant(
                                this.blockModels().createSuffixedVariant(berry, "_stage" + age,
                                        ModelTemplates.CROSS, TextureMapping::cross)))));
    }

    /**
     * This method is derived from vanilla's createNyliumBlock method. The only difference is that this method allows you to pass in any block as the texture to use for the bottom block.
     * You can also use a custom texture by passing in null for the borrowedBottomTexture. Keep in mind that the custom texture must be names block_bottom.png
     * e.g. pallid_nullium_bottom.png would be the name for the texture png file for Pallid Nullium's bottom texture.
     */
    public void createNyliumLikeBlock(Block block, @Nullable Block borrowedBottomTexture){ // Whilst I only ever use this for end stone, passing the bottom block as a parameter is better practice
        Material bottom = borrowedBottomTexture != null
                ? TextureMapping.getBlockTexture(borrowedBottomTexture)   // If borrowedBottomTexture isn't null, borrow the specified block's texture to use on the bottom of the block
                : TextureMapping.getBlockTexture(block, "_bottom"); // Otherwise set it to a custom texture

        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.BOTTOM, bottom)
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));

        this.blockStateOutput.accept(
                createSimpleBlock(block,
                        plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(
                                block, mapping, this.modelOutput)))
        );
    }

    public void createAge3CubeBlock(Block block, boolean useFrostedIceTexture) { // Vanilla's createFrostedIce is hardcoded for the Frosted Ice block *sigh*
        Block textureSource = useFrostedIceTexture ? Blocks.FROSTED_ICE : block;

        this.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block).with(PropertyDispatch.initial(BlockStateProperties.AGE_3)
                        .select(0, plainVariant(this.blockModels.createSuffixedVariant(textureSource, "_0", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                        .select(1, plainVariant(this.blockModels.createSuffixedVariant(textureSource, "_1", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                        .select(2, plainVariant(this.blockModels.createSuffixedVariant(textureSource, "_2", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                        .select(3, plainVariant(this.blockModels.createSuffixedVariant(textureSource, "_3", ModelTemplates.CUBE_ALL, TextureMapping::cube)))
                )
        );
    }

    public void createPortal(Block portal) {
        this.blockModels().blockStateOutput.accept(
                MultiVariantGenerator.dispatch(portal).with(PropertyDispatch.initial(
                        BlockStateProperties.HORIZONTAL_AXIS).select(Direction.Axis.X, plainVariant(
                                ModelLocationUtils.getModelLocation(portal, "_ns")))
                        .select(Direction.Axis.Z, plainVariant(
                                ModelLocationUtils.getModelLocation(portal, "_ew"))
                        )
                ));
    }

    public BlockModelGenerators blockModels() {
        return this.blockModels;
    }
}
