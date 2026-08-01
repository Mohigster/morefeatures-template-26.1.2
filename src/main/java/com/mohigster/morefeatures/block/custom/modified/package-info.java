/**
 * Provides the mod version of vanilla blocks when vanilla classes don't cut it. For example: Signs need Block Entities.
 * If a block tries to create a block entity that it is not a part of in newBlockEntity, the game will crash. Since I can
 * Not add a custom sign to the vanilla Sign block entity, the MFStandingSignBlock and MFWallSignBlock extend their vanilla
 * equivalents so that they can override newBlockEntity to use the custom Sign block entity instead
 */
package com.mohigster.morefeatures.block.custom.modified;