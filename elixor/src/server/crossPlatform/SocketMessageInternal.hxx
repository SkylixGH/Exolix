#pragma once

#include <iostream>

using namespace std;

namespace Elixor::Server::CrossPlatform {
struct SocketMessageInternal {
  string connID;
  string messageData;
};
} // namespace Elixor::Server::CrossPlatform
