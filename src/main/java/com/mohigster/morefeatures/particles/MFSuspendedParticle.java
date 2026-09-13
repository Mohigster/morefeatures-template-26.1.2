package com.mohigster.morefeatures.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.SuspendedParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jspecify.annotations.NullMarked;

public class MFSuspendedParticle extends SuspendedParticle {
    public MFSuspendedParticle(
            ClientLevel level,
            double x,
            double y,
            double z,
            double xd,
            double yd,
            double zd,
            TextureAtlasSprite sprite
    ) {
        super(level, x, y, z, xd, yd, zd, sprite);
    }

    public static class CharredSporeProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public CharredSporeProvider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @NullMarked
        @Override
        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            double horizontalExponent = 1.0E-6D;

            double xa = random.nextGaussian() * horizontalExponent;
            double ya = random.nextGaussian() * 1.0E-4D;
            double za = random.nextGaussian() * horizontalExponent;
            MFSuspendedParticle particle = new MFSuspendedParticle(level, x, y, z, xa, ya, za, this.sprite.get(random));
            particle.setColor(0.13F, 0.1F, 0.1F);
            return particle;
        }
    }

    public static class DecrepitSporeProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public DecrepitSporeProvider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @NullMarked
        @Override
        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            return createEndParticle(level, x, y, z, random, this.sprite, 0.4F, 0.1F, 0.5F);
        }
    }

    public static class PallidSporeProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public PallidSporeProvider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @NullMarked
        @Override
        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            return createEndParticle(level, x, y, z, random, this.sprite, 0.2F, 0.4F, 0.2F);
        }
    }

    public static Particle createEndParticle(
            ClientLevel level,
            double x, double y, double z,
            RandomSource random,
            SpriteSet sprite,
            float r, float g, float b
    ) {
        double horizontalExponent = 1.0E-2D;
        double xa = random.nextGaussian() * horizontalExponent;
        double ya = random.nextGaussian() * 1.0E-3D;
        double za = random.nextGaussian() * horizontalExponent;
        MFSuspendedParticle particle = new MFSuspendedParticle(level, x, y, z, xa, ya, za, sprite.get(random));
        particle.setColor(r, g, b);
        return particle;
    }
}
