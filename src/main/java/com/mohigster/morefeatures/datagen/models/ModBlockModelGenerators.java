package com.mohigster.morefeatures.datagen.models;

import com.mohigster.morefeatures.block.custom.VoidAnchorBlock;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jspecify.annotations.Nullable;

import static net.minecraft.client.data.models.BlockModelGenerators.createSimpleBlock;
import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;

public final class ModBlockModelGenerators {
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
