#include <iostream>

std::string format(const std::string& number, int tail);

std::string& reverse(std::string& number) {
  char temp;
  for (int i = 0, j = number.size() - 1; i < j; i++, j--) {
    temp = number[i];
    number[i] = number[j];
    number[j] = temp;
  }
  return number;
}

std::string mul(std::string number, char factor) {
  int x = factor - '0';
  int y = 0;
  int carry = 0;
  std::string result;
  for (int i = number.size() - 1; i >= 0; i--) {
    y = number[i] - '0';
    int product = x * y + carry;
    result += (product % 10 + '0');
    carry = product / 10;
  }
  if (carry) {
    result += carry + '0';
  }
  return reverse(result);
}

std::string inc(const std::string& x, const std::string& y) {
  std::string sum;
  int carry = 0;
  int k = 0;
  int i, j;
  for (i = x.size() - 1, j = y.size() - 1; i >= 0 && j >= 0; i--, j--) {
    k = x[i] - '0' + y[j] - '0' + carry;
    sum += k % 10 + '0';
    carry = k / 10;
  }
  for (; i >= 0; i--) {
    k = x[i] - '0' + carry;
    sum += k % 10 + '0';
    carry = k / 10;
  }
  for (; j >= 0; j--) {
    k = y[j] - '0' + carry;
    sum += k % 10 + '0';
    carry = k / 10;
  }
  if (carry) {
    sum += carry + '0';
  }
  return reverse(sum);
}

std::string multiply(const std::string& x, const std::string& y) {
  std::string product;
  for (int i = 0; i < y.size(); i++) {
    product += '0';
    product = inc(product, mul(x, y[i]));
  }
  return product;
}

std::string exp(const std::string& number, int n) {
  std::string result = number;
  for (int i = 2; i <= n; i++) {
    result = multiply(result, number);
  }
  return result;
}

std::string cal(const std::string& number, int n) {
  if (n == 0) {
    return "1";
  }
  std::string coped;
  int tail = 0;
  for (int i = 0; i < number.size(); i++) {
    tail++;
    if (number[i] != '.') {
      coped += number[i];
    } else {
      tail = 0;
    }
  }
  bool zero = true;
  for (int i = 0; i < coped.size(); i++) {
    if (coped[i] != '0'){
      zero = false;
    }
  }
  if (zero) {
    return "0";
  }
  std::string temp = exp(coped, n);
  return format(temp, tail * n);
}

std::string format(const std::string& number, int tail) {
  std::string temp;
  bool first = true;
  for (int k = 0, i = number.size() - 1; i >= 0; i--, k++) {
    if (first) {
      if (number[i] != '0' || tail == k) {
        temp += number[i];
        first = false;
      } else {
        // ignore zero of tail
      }
    } else {
      temp += number[i];
    }
    if (k == tail - 1 && !temp.empty()) {
      temp += '.';
    }
  }
  std::string result;
  first = true;
  for (int i = temp.size() - 1; i >= 0; i--) {
    if (first) {
      if (temp[i] != '0') {
        result += temp[i];
        first = false;
      }
    } else {
      result += temp[i];
    }
  }
  return result;
}

int main(int argc, char* argv[]) {
  std::string number;
  int n;
  while (std::cin >> number >> n) {
    std::cout << cal(number, n) << std::endl;
  }
  return 0;
}
