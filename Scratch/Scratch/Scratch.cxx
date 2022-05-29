#include <iostream>
#include <ElixorCore/StringUtil/StringUtil.h>

using namespace std;
using namespace ElixorFramework::ElixorCore;

int main() {
    auto s = StringUtil::Split("Print('ElixorIsKewl');", "");
    
    cout << "Element 1 is: " << s[0] << endl;

    for (auto i : s) {
        cout << i << endl;
    }
    
    return 0;
}
