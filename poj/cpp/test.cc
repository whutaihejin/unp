#include <iostream>
#include <string>
#include <stdlib.h>

int Standard(std::string& number) {
  for (; !number.empty(); ) {
    if (*(number.begin()) != '0') break;
    else number.erase(number.begin());
  }
  for (int i = number.size() - 1; i >= 0; --i) {
    if (number[i] == '.') {
      number.erase(i, 1);
      break;
    } else if (number[i] != '0') {
      break;
    } else {
      number.erase(i, 1);
    }
  }
  if (number.empty()) {
    number = "0";
  }
  int mantissa = 0;
  bool flag = false;
  std::string::iterator limit = number.end();
  for (std::string::iterator iter = number.begin(); iter != limit; iter++) {
    if (flag) mantissa++;
    if (*iter == '.') {
      number.erase(iter);
      flag = true;
    }
  }
  return mantissa;
}

std::string DoMul(std::string& x, std::string y) {
  std::string sum;
  // *
  for (int i = 0; i < x.size(); i++) {
    int carry = 0;
    sum += '0';
    std::string shift(y.size(), '0');
    for (int j = y.size() - 1; j >= 0; j--) {
      int product = (x[i] - '0') * (y[j] - '0') + carry;
      shift[j] = product % 10 + '0';
      carry = product / 10;
    }
    if (carry) shift.insert(0, 1, carry + '0');
    // + 
    int k, p;
    carry = 0;
    for (k = sum.size() - 1, p = shift.size() - 1; k >= 0 && p >= 0; --k, --p) {
       int product = sum[k] - '0' + shift[p] - '0' + carry;
       sum[k] = product % 10 + '0';
       carry = product / 10;
    }
    for (; k >= 0; --k) {
       int product = sum[k] - '0' + carry;
       sum[k] = product % 10 + '0';
       carry = product / 10;
    }
    for (; p >= 0; --p) {
       int product = shift[p] - '0' + carry;
       sum.insert(0, 1, product % 10 + '0');
       carry = product / 10;
    }
    if (carry) sum.insert(0, 1, carry + '0');
  }
  return sum;
}

std::string Mul(std::string& x, std::string& y) {
  int mantissa = Standard(x);
  mantissa += Standard(y);
  std::string product = DoMul(x, y);
  product.insert(product.size() - mantissa, 1, '.');
  Standard(product);
  return product;
}

std::string exp(std::string& x, int r) {
  if (r == 1) return x;
  std::string product = exp(x, r / 2);
  product = Mul(product, product);
  return r & 0x01 ? Mul(product, x) : product;
}

int main(int argc, char* argv[]) {
  std::string x = "151.80";
  int r = 0;
  if (argc == 3) {
    x = argv[1];
    r = atoi(argv[2]);
  }
  std::string result;
  if (r == 0) result = "1";
  else result = exp(x, r);
  std::cout << result << std::endl;
  return 0;
}
