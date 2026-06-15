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
import org.jspecify.annotations.Nullable;

import static net.minecraft.client.data.models.BlockModelGenerators.createSimpleBlock;
import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;

public final class ModBlockModelGenerators {
    public static void createAnchor(BlockModelGenerators blockModels, Block anchorBlock){ // Vanilla respawn anchor method is hardcoded for the vanilla anchor, hence the custom method (which allows any block to be passed in as a parameter.)
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
        Material bottom = borrowedBottomTexture != null
                ? TextureMapping.getBlockTexture(borrowedBottomTexture) // If borrowedBottomTexture isn't null, borrow the specified block's texture
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
}
