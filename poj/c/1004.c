#include <stdio.h>

double round(double value) {
  if (value > 0) {
    long val =  (long)(value * 100 + 0.5);
    return val / 100.0; 
  } else if (value < 0) {
    long long val = (long)(value * 100 - 0.5);
    return val / 100.0;
  } else {
    return value;
  }
}

int main(int argc, char* argv[]) {
  double balance = 0;
  double total = 0;
  double avg = 0;
  int i = 0;
  for (i = 0; i < 12; ++i) {
    scanf("%lf", &balance);
    total += balance;
  }
  avg = total / 12;
  printf("$%.2f\n", round(avg));
  return 0;
}
