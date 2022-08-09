#include <exolix.h>

namespace exolix::net::gateway {
    Message::Message(std::string &pipe) {
        this->pipe = pipe;
    }

    void Message::write(std::string key, std::string value) {

    }

    void Message::write(std::string key, bool value) {

    }

    void Message::write(std::string key, int value) {

    }

    void Message::write(std::string key, std::vector<std::string> value) {

    }

    void Message::write(std::string key, std::vector<bool> value) {

    }

    void Message::write(std::string key, std::vector<int> value) {

    }

    void Message::setKeyType(std::string key, exolix::net::gateway::KeyType type) {

    }
}