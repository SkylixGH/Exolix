#include <elixor.hxx>
#include <iostream>
#include <fstream>
#include <bitset>

using namespace Elixor::Internal;
using namespace std;

int main() {
    ofstream stream(".enx", ios::out | ios::binary);

    // write raw byte array containing FF AB CD EF
    bitset<8> b1(0xFF);
    bitset<8> b2(0xAB);
    bitset<8> b3(0xCD);
    bitset<8> b4(0xEF);

    stream.write(reinterpret_cast<char*>(&b1), sizeof(b1));
    stream.write(reinterpret_cast<char*>(&b2), sizeof(b2));
    stream.write(reinterpret_cast<char*>(&b3), sizeof(b3));
    stream.write(reinterpret_cast<char*>(&b4), sizeof(b4));

    return 0;
}
