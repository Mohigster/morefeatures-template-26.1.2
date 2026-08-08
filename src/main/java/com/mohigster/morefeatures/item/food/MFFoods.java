package com.mohigster.morefeatures.item.food;

import net.minecraft.world.food.FoodProperties;

public class MFFoods {
    public static final FoodProperties BLUE_BERRY = createFood(2, 0.4F);

    @SuppressWarnings("SameParameterValue")
    private static FoodProperties createFood(int nutrition, float saturationModifier) {
        return new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(saturationModifier)
                .build();
    }
}
