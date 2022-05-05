package net.skylix.elixor.desktop.animation;

import java.util.function.BiConsumer;

public class AnimationInteger {
    private float current;
    private boolean done;
    private Thread thread;
    private float target;
    private float clockDelay;
    private float jumpSize;
    private final BiConsumer<AnimationInteger, Integer> onUpdate;

    public AnimationInteger(float current, BiConsumer<AnimationInteger, Integer> onUpdate) {
        this.current = current;
        this.target = current;
        this.done = true;
        this.onUpdate = onUpdate;
    }

    private void threadCallback() {
        while (!done) {
            try {
                Thread.sleep((long) clockDelay);
            } catch (InterruptedException e) {
                // Ignore
            }

            if (this.current < target) {
                this.current += jumpSize;

                if (this.current > target)
                    this.current = target;
            } else if (this.current > target) {
                this.current -= jumpSize;

                if (this.current < target)
                    this.current = target;
            } else {
                done = true;
            }

            onUpdate.accept(this, (int) this.current);
        }
    }

    public final void transitionTo(float newInt, float jumpSize, float clockDelay) {
        target = newInt;
        this.clockDelay = clockDelay;
        this.jumpSize = jumpSize;
        done = false;

        if (thread == null || !thread.isAlive()) {
            this.thread = new Thread(this::threadCallback);
            thread.start();
        }
    }

    public final void halt() {
        if (thread != null && thread.isAlive())
            thread.interrupt();

        done = true;
    }

    public final float testTargetDistance(float newInt, float jumpSize) {
        float dist = (float) Math.ceil((newInt - current) / jumpSize);

        if (dist < 0)
            dist *= -1;

        return dist;
    }
}
