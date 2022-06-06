#include <skylix/elixor/strings/Strings.hxx>
#include <iostream>
#include <vector>

using namespace Skylix::Elixor;
using namespace std;

bool Strings::Contains(const string full, const string query) {
    return full.find(query) != string::npos;
}

vector<string> Strings::Split(string full, const string delimeter) {
    if (full.empty() || !Strings::Contains(full, delimeter)) {
        return vector<string>();
    }

    if  (delimeter.empty()) {
        auto result = vector<string>();

        for (auto& c : full) {
            result.push_back(string(1, c));
        }

        return result;
    }

    vector<string> segments;
    size_t pos = 0;
    string token;

    while ((pos = full.find(delimeter)) != string::npos) {
        token = full.substr(0, pos);
        segments.push_back(token);
        full.erase(0, pos + delimeter.length());
    }

    segments.push_back(full);
    return segments;
}
