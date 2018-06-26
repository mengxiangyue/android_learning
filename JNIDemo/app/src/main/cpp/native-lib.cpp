//
// Created by xiangyue on 2018/6/5.
//

/* https://developer.android.com/studio/projects/add-native-code
 * 不用生成头文件也可以
 *
 */

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_io_mshare_jnidemo_MainActivity_sayHello(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++ 123";
    return env->NewStringUTF(hello.c_str());
}