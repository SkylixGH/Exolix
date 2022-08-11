#pragma once

#include <string>

namespace exolix::net::server::system {
    class BindErrors {
    public:
        static std::string COULD_NOT_CREATE_SOCKET;
        static std::string COULD_NOT_SET_OPTIONS;
        static std::string COULD_NOT_BIND_SOCKET;
        static std::string COULD_NOT_LISTEN_SOCKET;
    };
}
