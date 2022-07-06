#include <elixor/server/Server.hxx>
#include <iostream>

using namespace std;

Elixor::Server::Server::Server() {
    std::cout << "Server::Server()" << std::endl;
}

Elixor::Server::Server::~Server() {
    std::cout << "Server::~Server()" << std::endl;
}

void Elixor::Server::Server::Start() {
    std::cout << "Server::Start()" << std::endl;
}

void Elixor::Server::Server::Stop() {
    std::cout << "Server::Stop()" << std::endl;
}
