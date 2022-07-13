#pragma once

#include <iostream>

using namespace std;

namespace Elixor::Server::Linux {
class LinuxSocket {
private:
public:
  LinuxSocket(const string host, const int port);
  ~LinuxSocket();

  void OnConnect();
  void OnMessage();
};
} // namespace Elixor::Server::Linux
