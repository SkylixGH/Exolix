# Class: `SocketServer`
- **Include**: `<exolix>`
- **Location**: `lib/net/tcpip.h`
- **Namespace**: `exolix`

This class can be used for creating bidirectional TCP/IP connections between two
different parties. It can be used for creating a server host.

# Constructor
Create a new socket server instance.

**Arguments**
 - `address` The location address for where the server will be listening.
   - [`NetAddress`](NetAddress.md) from `<exolix>`
 - `backlog` The amount of connections that can be queued up before the server will start rejecting connections.
   - `int`