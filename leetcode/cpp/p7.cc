#include <stdio.h>
#include <assert.h>
#include <limits.h>
int doReverse(long x) {
  assert(x >= 0);
  int base = 10;
  long value = 0;
  for (; x != 0;) {
    value *= base;
    value += (x % base);
    x /= base;
  }
  return (int)(value > INT_MAX ? 0 : value);
}

int reverse(int x) {
  long val = (long)x;
  if (val >= 0) {
    return doReverse(val);
  } else {
    return -doReverse(-val);
  }
}

int main(int argc, char* argv[]) {
  int x = 123;
  printf("expect=%d, actual=%d\n", 321, reverse(x));
  x = 0;
  printf("expect=%d, actual=%d\n", 0, reverse(x));
  x = -123;
  printf("expect=%d, actual=%d\n", -321, reverse(x));
  x = -2147483648;
  printf("expect=%d, actual=%d\n", 0, reverse(x));
  
  return 0;
}
