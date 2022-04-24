package net.skylix.elixor.elixorFX.jni;

public class WindowsJNI {
    static {
        System.loadLibrary("windowsApplyHWND");
    }

    public native String bar();
}
