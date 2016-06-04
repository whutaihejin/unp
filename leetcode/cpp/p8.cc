#include <iostream>
#include <ctype.h>
#include <limits.h>

int myAtoi(std::string str) {
  // 1 empty
  if (str.empty()) return 0;
  // 2 whitespace
  int i, size = str.size();
  for (i = 0; i < size; i++) {
    if (!isspace(str[i])) break;
  }
  if (i == size) return 0;
  // 3.1 flag
  int flag = 1;
  if (str[i] == '+') {
    i++;
  } else if (str[i] == '-') {
    flag = -1;
    i++;
  }
  if (i == size) return 0;
  // 3.2 convert
  int base = 10;
  long value = 0;
  for (; i < size; i++) {
    if (!isdigit(str[i])) break;
    value *= base;
    value += (str[i] - '0');
    if (flag == 1 && value > INT_MAX) {
        value = INT_MAX;
        break;
    } else if (flag == -1 && -value < INT_MIN) {
        value = INT_MIN;
        value = -value;
        break;
    }
  }
  return (int)(flag * value);
}

void doTest(std::string str, int expect) {
  int value = myAtoi(str);
  std::cout << (value == expect) << ", value=" << value << ", expect=" << expect << ", str=" << str << std::endl;
}

void doInteger(int value) {
  std::cout << value << std::endl;
}

int main(int argc, char* argv[]) {
  doTest("", 0);
  doTest(" ", 0);
  doTest("     ", 0);
  doTest("+", 0);
  doTest("-", 0);
  doTest("1", 1);
  doTest("+1", 1);
  doTest("-1", -1);
  doTest("+0", 0);
  doTest("-0", 0);
  doTest("123", 123);
  doTest("100021", 100021);
  doTest("2147483647", 2147483647);
  doTest("2147483648", 2147483647);
  doTest("-2147483648", -2147483648);
  doTest("-2147483649", -2147483648);
  doTest("123dgdf", 123);
  doTest("123 es", 123);
  doTest("-123 es", -123);
  doTest("010", 10);
  return 0;
}
