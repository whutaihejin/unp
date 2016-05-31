#include <stdio.h>
int main(int argc, char* argv[]) {
  int p, e, i, d, x, counter;
  for (counter = 0;;) {
    scanf("%d %d %d %d", &p, &e, &i, &d);
    if (p == -1 && e == -1 && i == -1 && d == -1) {
      break;
    }
    counter++;
    for (x = d + 1; x <= d + 21252; x++) {
      if ((x - p) % 23 == 0 && (x - e) % 28 == 0 && (x - i) % 33 == 0) {
        break;
      }
    }
    printf("Case %d: the next triple peak occurs in %d days.\n", counter, x - d);
  }
  return 0;
}
