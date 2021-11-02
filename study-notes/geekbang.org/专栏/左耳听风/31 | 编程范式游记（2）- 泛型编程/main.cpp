#include <iostream>
#include <vector>

using namespace std;

template<typename T, typename Iter>
Iter search(Iter pStart, Iter pEnd, T target) {
    for (Iter p = pStart; p != pEnd; p++) {
        if (*p == target)
            return p;
    }
}

template<typename T, typename Iter>
T sum(Iter pStart, Iter pEnd) {
    T result = 0;
    for (Iter p = pStart; p != pEnd; p++) {
        result += *p;
    }
    return result;
}

template<typename T, class Iter>
typename Iter::value_type
sum(Iter start, Iter end, T init) {
    typename Iter::value_type result = init;
    while (start != end) {
        result = result + *start;
        start++;
    }
    return result;
}

struct Employee {
    string name;
    string id;
    int vacation;
    double salary;
};

template<class Iter, class T, class Op>
T reduce(Iter start, Iter end, T init, Op op) {
    T result = init;
    while (start != end) {
        result = op(result, *start);
        start++;
    }
    return result;
}

template<class InputIt, class T>
T accumulate(InputIt first, InputIt last, T init) {
    for (; first != last; ++first) {
        init = init + *first;
    }
    return init;
}

template<class InputIt, class T, class BinaryOperation>
T accumulate(InputIt first, InputIt last, T init, BinaryOperation op) {
    for (; first != last; ++first) {
        init = op(init, *first);
    }
    return init;
}

template<class T, class Cond>
struct counter {
    size_t operator()(size_t c, T t) const {
        return c + (Cond(t) ? 1 : 0);
    }
};

template<class Iter, class Cond>
size_t count_if(Iter begin, Iter end, Cond c) {
    return reduce(begin, end, 0,
                  counter<typename Iter::value_type, Cond>(c));
}

int main() {

    string s = "hello";
    char target = 'e';
    printf("ret = %d\n", *search(s.begin(), s.end(), target));

    vector<int> list = {1, 2, 3, 4};
    printf("sum = %d\n", sum<int>(list.begin(), list.end()));

    vector<int> c = {5, 6, 7, 8};
    vector<int>::iterator it = c.begin();
    printf("sum = %d\n", sum(c.begin(), c.end(), 0));

    vector<Employee> staff = {
            Employee{"one", "1", 1, 10000},
            Employee{"two", "2", 2, 20000},
            Employee{"three", "3", 3, 30000},
            Employee{"four", "4", 4, 40000},
    };
//    // total salary or total vacation days?
//    sum(staff.begin(), staff.end(), 0);

    double sum_salaries =
            reduce(staff.begin(), staff.end(), 0.0,
                   [](double s, Employee e) { return s + e.salary; });
    printf("sum_salaries = %f\n", sum_salaries);

    double max_salary =
            reduce(staff.begin(), staff.end(), 0.0,
                   [](double s, Employee e) { return s > e.salary ? s : e.salary; });
    printf("max_salary = %f\n", max_salary);

    size_t cnt = std::count_if(staff.begin(), staff.end(),
                           [](Employee e) { return e.salary > 10000; });
    printf("cnt = %ld", cnt);

    return 0;
}
