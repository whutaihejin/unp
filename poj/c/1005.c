#include <stdio.h>
#include <stdlib.h>
#include <math.h>

double pi = 3.1415926;

int main(int argc, char* argv[]) {
  int N, i;
  double x, y, z;
  long r = 0;
  scanf("%d", &N);
  for (i = 1; i <= N; ++i) {
    scanf("%lf %lf", &x, &y);
    z = pi * (x * x + y * y) / 100;
    r = (long)ceil(z);
    printf("Property %d: This property will begin eroding in year %ld.\n", i, r);
  }
  printf("END OF OUTPUT.\n");
  return 0;
}
