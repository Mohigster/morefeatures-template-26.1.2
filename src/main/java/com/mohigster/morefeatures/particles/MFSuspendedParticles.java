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

public class MFSuspendedParticles extends SuspendedParticle {
    public MFSuspendedParticles(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
    }

    public static class CharredSporeProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public CharredSporeProvider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        @NullMarked
        @Override
        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            double xa = random.nextGaussian() * 1.0E-6D;
            double ya = random.nextGaussian() * 1.0E-4D;
            double za = random.nextGaussian() * 1.0E-6D;
            SuspendedParticle particle = new SuspendedParticle(level, x, y, z, xa, ya, za, this.sprite.get(random));
            particle.setColor(0.9F, 0.4F, 0.5F);
            return particle;
        }
    }
}
