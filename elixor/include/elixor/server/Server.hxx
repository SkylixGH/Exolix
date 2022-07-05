#pragma once

namespace Elixor {
    namespace Server {
        class Server {
        public:
            Server();
            ~Server();

            void Start();
            void Stop();
        };
    }
}
