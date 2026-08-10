/**
 * Provides the mod version of vanilla blocks when vanilla classes don't cut it.
 * <p>For example: Signs need Block Entities.
 * If a block tries to create a block entity that it is not a part of the BE returned in {@code newBlockEntity}, the game will crash. Since I can
 * not add a custom sign to the vanilla {@link BlockEntityTypes#SIGN}, the {@link MFStandingSignBlock} and {@link MFWallSignBlock} extend their vanilla
 * equivalents so that they can override {@code newBlockEntity} to use {@link MFBlockEntities#MF_SIGN_BE} instead
 */
@NullMarked
package com.mohigster.morefeatures.block.custom.modified;

import com.mohigster.morefeatures.block.custom.modified.sign.MFStandingSignBlock;
import com.mohigster.morefeatures.block.custom.modified.sign.MFWallSignBlock;
import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import org.jspecify.annotations.NullMarked;