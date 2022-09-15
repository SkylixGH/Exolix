#include <iostream>

using namespace std;

template <typename E>
class Err {
public:
    Err(E error) : error(error) {}
    E error;
};

template <typename T>
class Ok {
public:
    Ok(T value) : value(value) {}
    T value;
};

template <typename E, typename T>
class Result {
public:
    Result(Err<E> v) {
        err = v;
        hErr = true;
    }

    Result(Ok<T> v) {
        ok = v;
        hErr = false;
    }

    T get() {
        if (hErr)
            throw runtime_error("Result has error");

        return ok.value;
    }

    union {
        Err<E> err;
        Ok<T> ok;
    };

    bool hErr;
};

int main() {
    Result<int, int> b = Ok(5);
    Result<int, int> a = Err(50);

    if (a.hErr) {
        cout << "[a] Has error: " << a.err.error << endl;
    } else {
        cout << "[a] Has value: " << a.ok.value << endl;
    }

    if (b.hErr) {
        cout << "[b] Has error: " << b.err.error << endl;
    } else {
        cout << "[b] Has value: " << b.ok.value << endl;
    }

    auto gvalA = b.get();

    cout << "gvalA = " << gvalA << endl;

    return 0;
}
