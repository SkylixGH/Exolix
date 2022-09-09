# Class: `Thread`
 - **Include**: `<exolix>` 
 - **Location**: `lib/thread/thread.h`

A class used for making execution of code asynchronous. It can be used to speed up
computation of a certain operation or split up heavy workloads and more!

# Example
In this example, we have two counters that count up to 5.
When running this code, you will notice that both counters are counting
at the exact same time synchronously.

```cpp
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
```

# Constructor: `Thread`
Create a new thread for running code asynchronously.
Threads can be used for running multiple operations at the same time.

**Arguments**
 - `threadMain` The thread's main source of execution, this is where the code will be executed.
   - `std::function<void()>` from `<functional>`

# Method: `isActive`
This method will let you check if the current thread is active and running code.

**Return**
 - `bool` Whether the thread is active or not.

# Method: `block`
This will block the current outside thread at the point where this function was called.
When blocking, the block will stay active for as long as the thread is running.

**Return**
 - [`ThreadErrors`](./ThreadErrors.md) The return codes of the function.

# Static Method: `wait`
This method is used for time delays in threads. You can use this to block
the parent thread of where this method was called for a certain period of time.

**Arguments**
 - `value` The value of the time delay.
   - [`u64`](../number/u64.md) from `<exolix>`
 - `type` The unit of time to pair with the value, for example, value could be 1 and type seconds.
   - [`TimeUnit`](./TimeUnit.md) from `<exolix>`
