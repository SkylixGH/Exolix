#pragma once

#include <string>
#include <vector>

namespace exolix::net::gateway {
    class Message {
    private:
        std::string pipe;

    protected:
        void setKeyType(std::string key, KeyType type);

    public:
        Message(std::string &pipe);

        void write(std::string key, std::string value);
        void write(std::string key, bool value);
        void write(std::string key, int value);
        void write(std::string key, std::vector<std::string> value);
        void write(std::string key, std::vector<bool> value);
        void write(std::string key, std::vector<int> value);

        virtual void configure() = 0;
    };
}
