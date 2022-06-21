#include <nan.h>
#include <iostream>
#include <thread>

namespace Native {
    using v8::FunctionCallbackInfo;
    using v8::Isolate;
    using v8::Local;
    using v8::Object;
    using v8::String;
    using v8::Value;
    using v8::Number;

    void GetProcessorThreads(const FunctionCallbackInfo<Value>& args) {
        const int threads = std::thread::hardware_concurrency();
        args.GetReturnValue().Set(Number::New(args.GetIsolate(), threads));
    }

    void GetProcessorUtilization(const FunctionCallbackInfo<Value>& args) {
        const int utilization = std::thread::hardware_concurrency();
        args.GetReturnValue().Set(Number::New(args.GetIsolate(), utilization));
    }

    void Initialize(Local<Object> exports) {
        NODE_SET_METHOD(exports, "getProcessorThreads", GetProcessorThreads);
        NODE_SET_METHOD(exports, "getProcessorUtilization", GetProcessorUtilization);
    }

    NODE_MODULE(NODE_GYP_MODULE_NAME, Initialize)
}