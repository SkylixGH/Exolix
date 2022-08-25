#pragma once

#include <exolix/net.h>
#include <functional>

namespace exolix::http {
    enum class HttpProtocolVersion {
        HTTP_1_0,
        HTTP_1_1
    };

    class HttpSocketManager : public net::SocketAbstractManager {
    private:
        HttpProtocolVersion version;

    public:
        HttpSocketManager(HttpProtocolVersion protocolVersion);
        void handleConnect(exolix::net::Socket &socket) override;
    };
}
