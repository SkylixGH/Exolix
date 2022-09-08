<div align="center">
    <br />
    <img src="readme/Logo.svg" alt="Exolix Logo" width="200" />
    <br />
</div>

# Exolix Framework

Exolix is a cross-platform framework written in C++ for developing all purpos apps such as games, tools, servers and
more!
<br />The framework supports a wide variety of languages such as [C++](https://isocpp.org/)
, [C](https://en.wikipedia.org/wiki/ANSI_C), [Java](https://www.java.com/en/), [Python](https://www.python.org)
, [Go](https://go.dev/) and **MORE**!

<img src="readme/Banner.svg" alt="Exolix Banner" width="100%" />

## Examples

###### TCP Socket Server

```cpp
#include <exolix.hxx>

using namespace exolix;

int main() {
    NetAddress address(8080); // Server will listen on port 8080 and host 127.0.0.1
    SocketServer server(address);
    
    server.onAccept([] (Socket &socket) {
        Logger::ok("Client connected! { IP = " + socket.getClientIp() + "; OSID = " + socket.getOsId() + " }");
        
        socket.onReceive([&socket] (SocketMessage &msg) {
            Logger::info("Received message from client! { IP = " + socket.getClientIp() + "; OSID = " + socket.getOsId() + "; Message = " + msg.getMessage() + " }");
            
            SocketMessage returnMsg("Hello from server! Your message was: " + msg.getMessage());
            socket.send(returnMsg);
        });
        
        socket.block();
        
        Logger::ok("Client successfully closed and cleaned up the connection! { IP = " + socket.getClientIp() + "; OSID = " + socket.getOsId() + " }");
    });
    
    Logger::info("Starting server on address: " + address.formatted);
    
    try {
        server.listen();
    } catch (SocketServerException &error) {
        Logger::error("Server failed to listen due to following error:");
        error.render();
        
        return 1;
    }
    
    Logger::ok("Server successfully listening on address: " + address.formatted);
    server.block();
    
    return 0;
}
```

## Links

- [Homepage](https://skylix.net/about/exolix)
- [Repository](https://devops.skylix.net/skylix/exolix)
- [Community](https://invite.skylix.net/skylix)
- [Documentation](https://devops.skylix.net/skylix/exolix/wiki)

## Licence [MIT](https://opensource.org/licenses/MIT)

The project __Exolix__ is governed under the MIT open source license.
<br />Visit the licence [here](License)
