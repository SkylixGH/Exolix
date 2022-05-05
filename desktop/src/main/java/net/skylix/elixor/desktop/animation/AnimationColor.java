package net.skylix.elixor.desktop.animation;

import net.skylix.elixor.desktop.theme.ThemeColor;

import java.util.function.BiConsumer;

public class AnimationColor {
    private final AnimationInteger redAnime;
    private final AnimationInteger greenAnime;
    private final AnimationInteger blueAnime;
    private final AnimationInteger alphaAnime;
    private final ThemeColor currentColor;

    public AnimationColor(ThemeColor initial, BiConsumer<AnimationColor, ThemeColor> onUpdate) {
        this.currentColor = initial;
        final AnimationColor animationColor = this;

        redAnime = new AnimationInteger(initial.getRed(), (self, value) -> {
            this.currentColor.setRed(value);
            onUpdate.accept(animationColor, this.currentColor);
        });

        greenAnime = new AnimationInteger(initial.getGreen(), (self, value) -> {
            this.currentColor.setGreen(value);
            onUpdate.accept(animationColor, this.currentColor);
        });

        blueAnime = new AnimationInteger(initial.getBlue(), (self, value) -> {
            this.currentColor.setBlue(value);
            onUpdate.accept(animationColor, this.currentColor);
        });

        alphaAnime = new AnimationInteger(initial.getAlpha(), (self, value) -> {
            this.currentColor.setAlpha(value);
            onUpdate.accept(animationColor, this.currentColor);
        });
    }

    public final void moveTo(ThemeColor target, int duration) {
        final int jumpSize = 5;
        final float redTicksNeeded = redAnime.testTargetDistance(target.getRed(), jumpSize);
        final float greenTicksNeeded = greenAnime.testTargetDistance(target.getGreen(), jumpSize);
        final float blueTicksNeeded = blueAnime.testTargetDistance(target.getBlue(), jumpSize);
        final float alphaTicksNeeded = alphaAnime.testTargetDistance(target.getAlpha(), jumpSize);

        final float redCycleDelay = duration / redTicksNeeded;
        final float greenCycleDelay = duration / greenTicksNeeded;
        final float blueCycleDelay = duration / blueTicksNeeded;
        final float alphaCycleDelay = duration / alphaTicksNeeded;

        redAnime.transitionTo(target.getRed(), jumpSize, redCycleDelay);
        greenAnime.transitionTo(target.getGreen(), jumpSize, greenCycleDelay);
        blueAnime.transitionTo(target.getBlue(), jumpSize, blueCycleDelay);
        alphaAnime.transitionTo(target.getAlpha(), jumpSize, alphaCycleDelay);
    }
}
