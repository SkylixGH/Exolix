#include <exolix>
#include <iostream>

using namespace exolix;

void info(std::string t) {
    std::cout << ("[INFO] " + t + "\n");
}

void ok(std::string t) {
    std::cout << ("[OK] " + t + "\n");
}

void error(std::string t) {
    std::cout << ("[ERROR] " + t + "\n");
}

std::string srvErrToString(SocketServerErrors error) {
    switch (error) {
        case SocketServerErrors::Ok:
            return "Ok";

        case SocketServerErrors::ServerDangerousActionWhileOnline:
            return "ServerDangerousActionWhileOnline";

        case SocketServerErrors::ServerNotReadyForAction:
            return "ServerNotReadyForAction";

        case SocketServerErrors::CannotBlockServerAfterPreviouslyBlockedWithoutRestart:
            return "CannotBlockServerAfterPreviouslyBlockedWithoutRestart";

        case SocketServerErrors::TlsNotEnabled:
            return "TlsNotEnabled";

        case SocketServerErrors::FaultyAddressPort:
            return "FaultyAddressPort";

        case SocketServerErrors::FaultyAddressHostname:
            return "FaultyAddressHostname";

        case SocketServerErrors::AddressError:
            return "AddressError";

        case SocketServerErrors::CouldNotCreateServerSocketInstance:
            return "CouldNotCreateServerSocketInstance";

        case SocketServerErrors::PermissionFaulty:
            return "PermissionFaulty";

        case SocketServerErrors::IpVersionNotSupported:
            return "IpVersionNotSupported";

        case SocketServerErrors::TooManyDescriptorsOpen:
            return "TooManyDescriptorsOpen";

        case SocketServerErrors::FaultyMemoryAccess:
            return "FaultyMemoryAccess";

        case SocketServerErrors::CouldNotSetSocketOption:
            return "CouldNotSetSocketOption";

        case SocketServerErrors::CouldNotResolveHostname:
            return "CouldNotResolveHostname";

        default:
            return "Unknown";
    }
}

void initHandlers(SocketServer &srv) {
    srv.setOnSocketListener([&srv] (SocketServerAdapter &socket) {
        info("New connection from " + socket.getIp());

        socket.setOnMessageListener([&socket, &srv] (SocketServerAdapterMessage &msg) {
            info("Message received: " + msg.toString());

            srv.emit(">: " + msg.toString());
        });

        socket.block();
        info("Connection closed from " + socket.getIp());
    });
}

int main() {
    info("Creating server");

    SocketServerErrors srvRes;
    NetAddress addr("::1", 8080);
    SocketServer srv(addr, 100);

    initHandlers(srv);

    ok("Created server");
    info("Starting server");

    if ((srvRes = srv.load()) != SocketServerErrors::Ok) {
        error("Could not start server: " + srvErrToString(srvRes));
        return 1;
    }

    ok("Started server, online at ::1:8080");
    ok("Executable at: " + Path::getExecutablePath());

    srv.block();

    return 0;
}