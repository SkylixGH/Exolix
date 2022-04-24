package net.skylix.elixor.elixorFX.jni;

public class WindowsJNI {
    static {
        System.loadLibrary("windowsApplyHWND");
        System.loadLibrary("windowsApplyHWND_JNI");
    }

    public native String bar();
}
