package com.mohigster.morefeatures.data.generators;

import com.mohigster.morefeatures.particles.MFParticleTypes;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.data.ParticleDescriptionProvider;

public class MFParticleDescriptionProvider extends ParticleDescriptionProvider {
    public MFParticleDescriptionProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addDescriptions() {
        this.spriteSet(MFParticleTypes.BLOODWOOD_LEAVES.get(), MFIdentifier.withMfNamespace("bloodwood"), 4, false);
        this.spriteSet(MFParticleTypes.TAINTED_LEAVES.get(), MFIdentifier.withMfNamespace("tainted"), 4, false);
        this.spriteSet(MFParticleTypes.PALM_LEAVES.get(), MFIdentifier.withMfNamespace("palm"), 4, false);
        this.spriteSet(MFParticleTypes.CHARRED_SPORE.get(), Identifier.withDefaultNamespace("generic"), 1, false);
        this.spriteSet(MFParticleTypes.DECREPIT_LEAVES.get(), MFIdentifier.withMfNamespace("decrepit"), 4, false);
        this.spriteSet(MFParticleTypes.PALLID_LEAVES.get(), MFIdentifier.withMfNamespace("pallid"), 4, false);
    }
}
