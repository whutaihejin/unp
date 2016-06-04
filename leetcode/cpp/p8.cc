#include <iostream>
#include <ctype.h>
#include <limits.h>
int convertBase16(std::string& str, int i, int flag) {
  long value = 0;
  int base = 16;
  int size = str.size();
  for (; i < size; i++) {
    char ch = toupper(str[i]);
    if (isdigit(ch) || (ch >= 'A' && ch <= 'F')) {
      value *= base;
      value += isdigit(ch) ? ch - '0' : ch - 'A' + 10;
      if (flag == 1 && value >= INT_MAX) {
        value = INT_MAX;
        break;
      } else if (flag == -1 && -value <= INT_MIN) {
        value = INT_MIN;
        value = -value;
        break;
      }
    } else {
      break;
    }
  }
  return value;
}

int convertBase8(std::string& str, int i, int flag) {
  long value = 0;
  int base = 8;
  int size = str.size();
  for (; i < size; i++) {
    char ch = toupper(str[i]);
    if (isdigit(ch) && ch <= '7') {
      value *= base;
      value += ch - '0';
      if (flag == 1 && value >= INT_MAX) {
        value = INT_MAX;
        break;
      } else if (flag == -1 && -value <= INT_MIN) {
        value = INT_MIN;
        value = -value;
        break;
      }
    } else {
      break;
    }
  }
  return value;
}

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
  if (str[i] == '0') {
    if (i + 1 == size) return 0;
    i += 1;
    if (str[i] == 'x' || str[i] == 'X') {
      i += 1;
      base = 16;
    } else {
      base = 8;
    }
  }
  long value = 0;
  if (base == 10) { 
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
  } else if (base == 8) {
    value = convertBase8(str, i, flag);
  } else if (base == 16) {
    value = convertBase16(str, i, flag);
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
  doTest(" 01", 1);
  doTest(" 08", 0);
  doTest(" 011", 9);
  doTest(" 0x11", 17);
  doTest(" -0x11", -17);
  doTest(" +0x11", 17);
  doTest(" +0xA", 10);
  doTest(" +0xF", 15);
  doTest(" +0xH", 0);
  doTest(" +0Xaa", 170);
  return 0;
}
