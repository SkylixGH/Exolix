package net.skylix.elixor.desktop.animation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class AnimationPercent {
    private float current;
    private boolean done;
    private final Thread thread;
    private float target;
    private float clockDelay;
    private float jumpSize;

    public AnimationPercent(float current, BiConsumer<AnimationPercent, Integer> onUpdate) {
        this.current = current;
        this.target = current;
        this.done = true;

        this.thread = new Thread(() -> {
            while (!done) {
                try {
                    Thread.sleep((long) clockDelay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (this.current < target) {
                    this.current += jumpSize;
                } else if (this.current > target) {
                    this.current -= jumpSize;
                } else {
                    done = true;
                }

                onUpdate.accept(this, (int) this.current);
            }
        });
    }

    public final void transitionTo(float newInt, float jumpSize, float clockDelay) {
        target = newInt;
        this.clockDelay = clockDelay;
        this.jumpSize = jumpSize;
        done = false;

        if (!thread.isAlive()) {
            thread.start();
        }
    }

    public final void halt() {
        done = true;
        thread.interrupt();
    }
}
