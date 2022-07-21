#pragma once

namespace Elixor {
    namespace Server {
        namespace WebSockets {
            namespace Errors {
                /**
                 * An enumeration containing error codes for WebSocket backend
                 * API servers.
                 */
                enum ServerErrors {
                    /**
                     * This error code is thrown when settings such as the port
                     * were changed while the server was online
                     */
                    BINDING_RESOURCE_CHANGED_WHILE_ONLINE,

                    /**
                     * This error code is thrown when the server hostname is
                     * invalid.
                     */
                    CANNOT_BIND_INVALID_HOST,

                    /**
                     * This error code is thrown on UNIX based operating systems
                     * when assigning a port bellow 1024 without root
                     * permissions.
                     */
                    CANNOT_BIND_PORT_NO_ROOT_UNIX
                };
            } // namespace Errors
        }     // namespace WebSockets
    }         // namespace Server
} // namespace Elixor
