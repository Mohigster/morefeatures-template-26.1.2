package com.mohigster.morefeatures.sound;

import net.neoforged.neoforge.common.util.DeferredSoundType;

public class MFSoundTypes {
    public static final DeferredSoundType MAGIC_BLOCK_SOUNDS = new DeferredSoundType(
            1F, 1F,
            MFSoundEvents.MAGIC_BLOCK_BREAK,
            MFSoundEvents.MAGIC_BLOCK_STEP,
            MFSoundEvents.MAGIC_BLOCK_PLACE,
            MFSoundEvents.MAGIC_BLOCK_HIT,
            MFSoundEvents.MAGIC_BLOCK_FALL
    );

    public static final DeferredSoundType EVIL_PORTAL_SOUNDS = new DeferredSoundType(
            0.9F, 0.6F,
            MFSoundEvents.EVIL_PORTAL_BREAK,
            MFSoundEvents.EVIL_PORTAL_STEP,
            MFSoundEvents.EVIL_PORTAL_PLACE,
            MFSoundEvents.EVIL_PORTAL_HIT,
            MFSoundEvents.EVIL_PORTAL_FALL
    );
}
