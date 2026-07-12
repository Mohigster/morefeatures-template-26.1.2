package com.mohigster.morefeatures.block.custom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.mojang.serialization.MapCodec;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction.Plane;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayer.RespawnConfig;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.LevelData.RespawnData;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
public class VoidAnchorBlock extends Block {
    public static final MapCodec<VoidAnchorBlock> CODEC = simpleCodec(VoidAnchorBlock::new);

    public static final int MIN_CHARGES = 0;
    public static final int MAX_CHARGES = 4;

    // Reuses the same block state property as the vanilla Respawn Anchor.
    // If you want a separate property key, declare a new IntegerProperty here instead.
    public static final IntegerProperty CHARGE = BlockStateProperties.RESPAWN_ANCHOR_CHARGES;

    // The dimension this anchor is valid in. Change to your own ResourceKey if needed.
    public static final net.minecraft.resources.ResourceKey<net.minecraft.world.level.dimension.DimensionType>
            VALID_DIMENSION_TYPE = net.minecraft.world.level.dimension.BuiltinDimensionTypes.END;

    private static final ImmutableList<Vec3i> RESPAWN_HORIZONTAL_OFFSETS = ImmutableList.of(
            new Vec3i(0, 0, -1),
            new Vec3i(-1, 0, 0),
            new Vec3i(0, 0, 1),
            new Vec3i(1, 0, 0),
            new Vec3i(-1, 0, -1),
            new Vec3i(1, 0, -1),
            new Vec3i(-1, 0, 1),
            new Vec3i(1, 0, 1)
    );

    private static final ImmutableList<Vec3i> RESPAWN_OFFSETS = new ImmutableList.Builder<Vec3i>()
            .addAll(RESPAWN_HORIZONTAL_OFFSETS)
            .addAll(RESPAWN_HORIZONTAL_OFFSETS.stream().map(Vec3i::below).iterator())
            .addAll(RESPAWN_HORIZONTAL_OFFSETS.stream().map(Vec3i::above).iterator())
            .add(new Vec3i(0, 1, 0))
            .build();

    @Override
    public MapCodec<VoidAnchorBlock> codec() {
        return CODEC;
    }

    public VoidAnchorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(CHARGE, 0));
    }

    // -------------------------------------------------------------------------
    // Charging — right-click with an Ender Pearl
    // -------------------------------------------------------------------------

    @Override
    protected InteractionResult useItemOn(
            ItemStack itemStack,
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hitResult
    ) {
        if (isRespawnFuel(itemStack) && canBeCharged(state)) {
            charge(player, level, pos, state);
            itemStack.consume(1, player);
            return InteractionResult.SUCCESS;
        } else {
            // If the main hand doesn't hold fuel but the off-hand does, let the
            // engine retry with the off-hand rather than consuming the interaction.
            return (hand == InteractionHand.MAIN_HAND
                    && isRespawnFuel(player.getItemInHand(InteractionHand.OFF_HAND))
                    && canBeCharged(state))
                    ? InteractionResult.PASS
                    : InteractionResult.TRY_WITH_EMPTY_HAND;
        }
    }

    // -------------------------------------------------------------------------
    // Setting the spawn point — right-click with empty hand
    // -------------------------------------------------------------------------

    @Override
    protected InteractionResult useWithoutItem(
            BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult
    ) {
        if (state.getValue(CHARGE) == 0) {
            return InteractionResult.PASS;
        }

        if (!(level instanceof ServerLevel serverLevel)) {
            // Client side: consume so the hand animation plays.
            return InteractionResult.CONSUME;
        }

        if (!canSetSpawn(serverLevel)) {
            this.explode(state, serverLevel, pos);
            return InteractionResult.SUCCESS_SERVER;
        }

        if (player instanceof ServerPlayer serverPlayer) {
            RespawnConfig newRespawnConfig = new RespawnConfig(
                    RespawnData.of(serverLevel.dimension(), pos, 0.0F, 0.0F), false
            );
            RespawnConfig existing = serverPlayer.getRespawnConfig();

            if (existing == null || !existing.isSamePosition(newRespawnConfig)) {
                serverPlayer.setRespawnPosition(newRespawnConfig, true);
                serverLevel.playSound(
                        null,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        SoundEvents.RESPAWN_ANCHOR_SET_SPAWN,
                        SoundSource.BLOCKS,
                        1.0F, 1.0F
                );
                return InteractionResult.SUCCESS_SERVER;
            }
        }

        return InteractionResult.CONSUME;
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /* Returns true when the item can be used to charge the Void Anchor. */
    private static boolean isRespawnFuel(ItemStack stack) {
        return stack.is(Items.ENDER_PEARL);
    }

    private static boolean canBeCharged(BlockState state) {
        return state.getValue(CHARGE) < MAX_CHARGES;
    }

    /*
     * The Void Anchor only works in the End.
     * Unlike the vanilla Respawn Anchor, this does NOT delegate to an
     * environment attribute — it checks the dimension directly so there is
     * no need to register a custom attribute.
     */
    public static boolean canSetSpawn(ServerLevel level) {
        return level.dimension().equals(Level.END);
    }

    // -------------------------------------------------------------------------
    // Charging (called from useItemOn and can be called externally, e.g. hoppers)
    // -------------------------------------------------------------------------

    public static void charge(@Nullable Entity sourceEntity, Level level, BlockPos pos, BlockState state) {
        BlockState newState = state.setValue(CHARGE, state.getValue(CHARGE) + 1);
        level.setBlock(pos, newState, 3);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(sourceEntity, newState));
        // Reuse the vanilla charge sound — swap for your own SoundEvent if desired.
        level.playSound(
                null,
                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                SoundEvents.RESPAWN_ANCHOR_CHARGE,
                SoundSource.BLOCKS,
                1.0F, 1.0F
        );
    }

    // -------------------------------------------------------------------------
    // Explosion — triggered when used outside the End
    // -------------------------------------------------------------------------

    private void explode(BlockState state, ServerLevel level, BlockPos pos) {
        level.removeBlock(pos, false);

        boolean anyWaterNeighbors = Plane.HORIZONTAL.stream()
                .map(pos::relative)
                .anyMatch(neighborPos -> isWaterThatWouldFlow(neighborPos, level));
        final boolean inWater = anyWaterNeighbors || level.getFluidState(pos.above()).is(FluidTags.WATER);

        ExplosionDamageCalculator damageCalculator = new ExplosionDamageCalculator() {
            @Override
            public Optional<Float> getBlockExplosionResistance(
                    Explosion explosion, BlockGetter levelx, BlockPos testPos,
                    BlockState block, FluidState fluid
            ) {
                return testPos.equals(pos) && inWater
                        ? Optional.of(net.minecraft.world.level.block.Blocks.WATER.getExplosionResistance())
                        : super.getBlockExplosionResistance(explosion, levelx, testPos, block, fluid);
            }
        };

        Vec3 boomPos = Vec3.atCenterOf(pos);
        level.explode(
                null,
                level.damageSources().badRespawnPointExplosion(boomPos),
                damageCalculator,
                boomPos,
                5.0F,
                true,
                ExplosionInteraction.BLOCK
        );
    }

    private static boolean isWaterThatWouldFlow(BlockPos pos, Level level) {
        FluidState fluid = level.getFluidState(pos);
        if (!fluid.is(FluidTags.WATER)) return false;
        if (fluid.isSource()) return true;
        float amount = fluid.getAmount();
        if (amount < 2.0F) return false;
        return !level.getFluidState(pos.below()).is(FluidTags.WATER);
    }

    // -------------------------------------------------------------------------
    // Particles & ambient sound
    // -------------------------------------------------------------------------

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(CHARGE) == 0) return;

        if (random.nextInt(100) == 0) {
            level.playLocalSound(pos, SoundEvents.RESPAWN_ANCHOR_AMBIENT, SoundSource.BLOCKS, 1.0F, 1.0F, false);
        }

        double x = pos.getX() + 0.5 + (0.5 - random.nextDouble());
        double y = pos.getY() + 1.0;
        double z = pos.getZ() + 0.5 + (0.5 - random.nextDouble());
        double ya = random.nextFloat() * 0.04;
        // REVERSE_PORTAL gives that purple "End portal" swirl, fitting for the End.
        level.addParticle(ParticleTypes.REVERSE_PORTAL, x, y, z, 0.0, ya, 0.0);
    }

    // -------------------------------------------------------------------------
    // Block state, comparator output, pathfinding
    // -------------------------------------------------------------------------

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(CHARGE);
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    /* Scales the charge level (0–4) to the given maximum, e.g. 15 for a comparator. */
    public static int getScaledChargeLevel(BlockState state, int maximum) {
        return Mth.floor((state.getValue(CHARGE) - MIN_CHARGES) / (float) MAX_CHARGES * maximum);
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
        return getScaledChargeLevel(state, 15);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return false;
    }

    // -------------------------------------------------------------------------
    // Respawn position finding (used by the server when the player actually dies)
    // -------------------------------------------------------------------------

    public static Optional<Vec3> findStandUpPosition(EntityType<?> type, CollisionGetter level, BlockPos pos) {
        Optional<Vec3> safe = findStandUpPosition(type, level, pos, true);
        return safe.isPresent() ? safe : findStandUpPosition(type, level, pos, false);
    }

    private static Optional<Vec3> findStandUpPosition(
            EntityType<?> type, CollisionGetter level, BlockPos pos, boolean checkDangerous
    ) {
        MutableBlockPos mutable = new MutableBlockPos();
        UnmodifiableIterator<Vec3i> it = RESPAWN_OFFSETS.iterator();
        while (it.hasNext()) {
            mutable.set(pos).move(it.next());
            Vec3 position = DismountHelper.findSafeDismountLocation(type, level, mutable, checkDangerous);
            if (position != null) return Optional.of(position);
        }
        return Optional.empty();
    }
}
