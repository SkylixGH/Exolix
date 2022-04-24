#include <iostream>
#include "jni_Tools.h"

JNIEXPORT jstring JNICALL Java_net_skylix_elixor_elixorFX_(JNIEnv *env, jobject thisObject)
{
  std::string res("bar");
  return env->NewStringUTF(res.c_str());
}
