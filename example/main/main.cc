#include <exolix>
#include <iostream>

using namespace exolix;
using namespace std;

int store1 = 0;
int store2 = 0;

void count(int storeId) {
    if (storeId == 1) store1++;
    else store2++;

    cout << "Store " << storeId << ": The value is " << (storeId == 1 ? store1 : store2) << endl;
}

int main() {
    Thread t1([] () {
        cout << "Hello from thread 1!" << endl;

        int i = 0;

        while (i < 5) {
            count(1);

            Thread::wait(500, TimeUnit::Millisecond);
            i++;
        }
    });

    Thread t2([] () {
        cout << "Hello from thread 2!" << endl;

        int i = 0;

        while (i < 5) {
            count(2);

            Thread::wait(500, TimeUnit::Millisecond);
            i++;
        }
    });

    return 0;
}
