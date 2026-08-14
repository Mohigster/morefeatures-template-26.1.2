package com.mohigster.morefeatures.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.NullMarked;


public class MFFallingLeavesParticle extends SingleQuadParticle {

    private static final float ACCELERATION_SCALE = 0.0025F;
    private static final float SCALING_OF_RANDOM_FLOW_VALUE = 60.0F;
    private static final float DIVISION_FACTOR_FOR_ROT_SPEED_AND_ROLL = 20.0F;
    private static final int INITIAL_LIFETIME = 300;

    private float rotSpeed = (float) Math.toRadians(this.random.nextBoolean() ? -30.0 : 30.0);
    private final float spinAcceleration = (float) Math.toRadians(this.random.nextBoolean() ? -5.0 : 5.0);

    private final float windBig;
    private final boolean swirl;
    private final boolean flowAway;
    private final double xaFlowScale;
    private final double zaFlowScale;
    private final double swirlPeriod;

    protected MFFallingLeavesParticle(
            ClientLevel level,
            double x, double y, double z,
            TextureAtlasSprite sprite,
            float fallAcceleration,
            float sideAcceleration,
            boolean swirl,
            boolean flowAway,
            float scale,
            float startVelocity
    ) {
        super(level, x, y, z, sprite);

        this.windBig = sideAcceleration;
        this.swirl = swirl;
        this.flowAway = flowAway;

        this.lifetime = INITIAL_LIFETIME;
        this.gravity = fallAcceleration * 1.2F * ACCELERATION_SCALE;

        float size = scale * (this.random.nextBoolean() ? 0.05F : 0.075F);
        this.quadSize = size;
        this.setSize(size, size);

        this.friction = 1.0F;
        this.yd = -startVelocity;

        float particleRandom = this.random.nextFloat();
        this.xaFlowScale = Math.cos(Math.toRadians(particleRandom * SCALING_OF_RANDOM_FLOW_VALUE)) * this.windBig;
        this.zaFlowScale = Math.sin(Math.toRadians(particleRandom * SCALING_OF_RANDOM_FLOW_VALUE)) * this.windBig;
        this.swirlPeriod = Math.toRadians(1000.0F + particleRandom * 3000.0F);
    }

    @NullMarked
    @Override
    public SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.lifetime-- <= 0) {
            this.remove();
            return;
        }

        float aliveTicks = INITIAL_LIFETIME - this.lifetime;
        float relativeAge = Math.min(aliveTicks / (float) INITIAL_LIFETIME, 1.0F);

        double xa = 0.0;
        double za = 0.0;

        if (this.flowAway) {
            xa += this.xaFlowScale * Math.pow(relativeAge, 1.25);
            za += this.zaFlowScale * Math.pow(relativeAge, 1.25);
        }

        if (this.swirl) {
            xa += relativeAge * Math.cos(relativeAge * this.swirlPeriod) * this.windBig;
            za += relativeAge * Math.sin(relativeAge * this.swirlPeriod) * this.windBig;
        }

        this.xd += xa * ACCELERATION_SCALE;
        this.zd += za * ACCELERATION_SCALE;
        this.yd -= this.gravity;

        this.rotSpeed += this.spinAcceleration / DIVISION_FACTOR_FOR_ROT_SPEED_AND_ROLL;
        this.oRoll = this.roll;
        this.roll += this.rotSpeed / DIVISION_FACTOR_FOR_ROT_SPEED_AND_ROLL;

        this.move(this.xd, this.yd, this.zd);

        if (this.onGround || (this.lifetime < (INITIAL_LIFETIME - 1) && (this.xd == 0.0 || this.zd == 0.0))) {
            this.remove();
            return;
        }

        this.xd *= this.friction;
        this.yd *= this.friction;
        this.zd *= this.friction;
    }

    // ————————————————————————————————PROVIDERS————————————————————————————————————


    public static class PalmProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public PalmProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @NullMarked
        @Override
        public Particle createParticle(
                SimpleParticleType type,
                ClientLevel level,
                double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed,
                RandomSource random
        ) {
            return new MFFallingLeavesParticle(
                    level, x, y, z,
                    this.getSprite(random),
                    0.12F,
                    8.0F,
                    true,
                    false,
                    2.8F,
                    0.018F
            );
        }

        protected TextureAtlasSprite getSprite(RandomSource random) {
            return this.sprites.get(random);
        }
    }

    public static class EvilLeafProvider implements ParticleProvider<SimpleParticleType> { // Bloodwood and tainted leaves both use this
        private final SpriteSet sprites;

        public EvilLeafProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @NullMarked
        @Override
        public Particle createParticle(
                SimpleParticleType type,
                ClientLevel level,
                double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed,
                RandomSource random
        ) {
            return new MFFallingLeavesParticle(
                    level, x, y, z,
                    this.getSprite(random),
                    0.16F,
                    8.0F,
                    true,
                    false,
                    1.4F,
                    0.018F
            );
        }

        protected TextureAtlasSprite getSprite(RandomSource random) {
            return this.sprites.get(random);
        }
    }

    public static class EndLeafProvider implements ParticleProvider<SimpleParticleType> { // Pallid and decrepit leaves both use this
        private final SpriteSet sprites;

        public EndLeafProvider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @NullMarked
        @Override
        public Particle createParticle(
                SimpleParticleType type,
                ClientLevel level,
                double x, double y, double z,
                double xSpeed, double ySpeed, double zSpeed,
                RandomSource random
        ) {
            return new MFFallingLeavesParticle(
                    level, x, y, z,
                    this.getSprite(random),
                    0.13F,
                    7.5F,
                    false,
                    false,
                    2.2F,
                    0.015F
            );
        }

        protected TextureAtlasSprite getSprite(RandomSource random) {
            return this.sprites.get(random);
        }
    }
}
