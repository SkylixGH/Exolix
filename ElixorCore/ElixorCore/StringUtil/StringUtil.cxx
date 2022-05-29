#include "StringUtil.h"
#include <iostream>
#include <vector>

using namespace std;
using namespace ElixorFramework::ElixorCore;

bool StringUtil::Contains(string line, string substring) {
    return line.find(substring) != string::npos;
}

vector<string> StringUtil::Split(string line, string delimiter) {
    if (delimiter.empty()) {
        vector<string> * segments = new vector<string>();
        for (char c : line) {
            segments->push_back(string(1, c));
        }
        
        return * segments;
    } else if (!StringUtil::Contains(line, delimiter)) {
        return vector<string>();
    }

    vector<string> * segments = new vector<string>();

    size_t pos = 0;
    string token;

    while ((pos = line.find(delimiter)) != string::npos) {
        token = line.substr(0, pos);
        segments->push_back(token);
        line.erase(0, pos + delimiter.length());
    }

    segments->push_back(line);
    return * segments;
}
