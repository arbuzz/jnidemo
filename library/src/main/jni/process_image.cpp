//
// Created by Konstantin Olshanikov on 18.08.16.
//

#include <string.h>
#include <jni.h>
extern "C" {
JNIEXPORT jbyteArray JNICALL
Java_com_demolib_JNICaller_processImage(JNIEnv *env,
                                        jobject thiz,
                                        jbyteArray data) {
    int len = env->GetArrayLength(data);
    unsigned char *buf = new unsigned char[len];
    env->GetByteArrayRegion(data, 0, len, reinterpret_cast<jbyte *>(buf));
    //do what you want with buf

    return data;
}
}

