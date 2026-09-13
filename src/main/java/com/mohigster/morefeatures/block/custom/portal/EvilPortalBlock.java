package com.mohigster.morefeatures.block.custom.portal;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.data.resources.references.dimension.MFDimensionIds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.EndPlatformFeature;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.Set;

public class EvilPortalBlock extends Block implements Portal {
    public EvilPortalBlock(Properties properties) {
        super(properties);
    }

    @NullMarked
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level instanceof ServerLevel server) {
            player.teleport(Objects.requireNonNull(this.getPortalDestination(server, player, pos)));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable TeleportTransition getPortalDestination(ServerLevel currentLevel, Entity entity, BlockPos portalEntryPos) {
        ResourceKey<Level> currentDimension = currentLevel.dimension();
        boolean isLeavingEvilDim = currentDimension == MFDimensionIds.EVILDIM;

        LevelData.RespawnData respawnData = currentLevel.getRespawnData();
        ResourceKey<Level> targetDim = isLeavingEvilDim ? respawnData.dimension() : MFDimensionIds.EVILDIM;
        BlockPos baseSpawnPos = isLeavingEvilDim ? respawnData.pos() : ServerLevel.END_SPAWN_POINT.above(14);

        ServerLevel targetLevel = currentLevel.getServer().getLevel(targetDim);

        if (targetLevel == null) {
            return null;
        }

        Vec3 exactSpawnPos = Vec3.atBottomCenterOf(baseSpawnPos);
        float yRot, xRot;
        Set<Relative> relatives;

        if(isLeavingEvilDim) {
            // Returning from Custom Dimension
            if(entity instanceof ServerPlayer serverPlayer) {
                return serverPlayer.findRespawnPositionAndUseSpawnBlock(false, TeleportTransition.DO_NOTHING);
            }

            exactSpawnPos = Vec3.atBottomCenterOf(entity.adjustSpawnLocation(targetLevel, baseSpawnPos));
            yRot = respawnData.yaw();
            xRot = respawnData.pitch();
            relatives = Relative.union(Relative.DELTA, Relative.ROTATION);
        } else {
            // Entering our custom Dimension
            BlockPos platformCenter = BlockPos.containing(exactSpawnPos).below();
            BlockPos portalPos = platformCenter.west(2);

            if(!targetLevel.getBlockState(portalPos.above()).is(MFBlocks.EVIL_PORTAL)) {
                EndPlatformFeature.createEndPlatform(targetLevel, platformCenter, false);

                targetLevel.setBlockAndUpdate(portalPos.south(1), MFBlocks.PLANKS.tainted().get().defaultBlockState());
                targetLevel.setBlockAndUpdate(portalPos.north(1), MFBlocks.PLANKS.bloodwood().get().defaultBlockState());
                targetLevel.setBlockAndUpdate(portalPos, Blocks.BASALT.defaultBlockState());
                targetLevel.setBlockAndUpdate(portalPos.above(), MFBlocks.EVIL_PORTAL.get().defaultBlockState());
            }

            yRot = Direction.WEST.toYRot();
            xRot = 0f;
            relatives = Relative.union(Relative.DELTA, Set.of(Relative.X_ROT));

            if(entity instanceof ServerPlayer) {
                exactSpawnPos = exactSpawnPos.subtract(0, 1, 0);
            }
        }

        return new TeleportTransition(
                targetLevel,
                exactSpawnPos,
                Vec3.ZERO,
                yRot,
                xRot,
                relatives,
                TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET)
        );
    }
}
