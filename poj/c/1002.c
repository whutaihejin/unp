#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char map[26] = {'2', '2', '2', '3', '3', '3', '4', '4', '4', '5', '5', '5', '6', '6', '6', '7', '0', '7', '7', '8', '8', '8', '9', '9', '9', '0'};
              // A    B    C    D    E    F    G    H    I    G    K    L    M    N    O    P    Q    R    S    T    U    V    W    X    Y    Z

int compare(const void* x, const void* y) {
  return strcmp(x, y);
}


int main(int argc, char* argv[]) {
  char telephone[100000][9];
  char buf[1024];
  int mount, i, j, x, flag;
  memset(telephone, 0, sizeof(telephone));
  scanf("%d", &mount);
  for (i = 0; i < mount; ++i) {
    scanf("%s", buf);
    x = 0;
    for (j = 0; buf[j]; ++j) {
      if (buf[j] == '-') {
        continue;
      }
      if (buf[j] >= 'A' && buf[j] <= 'Z') {
        buf[j] = map[buf[j] - 'A'];
      }
      telephone[i][x++] = buf[j];
      if (x == 3) {
        telephone[i][x++] = '-';
      }
    }
  }
  qsort(telephone, mount, 9, compare);
  x = 1, flag = 0;
  for (i = 1; i < mount; ++i) {
    if (strcmp(telephone[i - 1], telephone[i])) {
      if (x > 1) {
        printf("%s %d\n", telephone[i - 1], x);
        flag = 1;
      }
      x = 1;
    } else {
      x++;
    }
  }
  if (x > 1) {
    printf("%s %d\n", telephone[i - 1], x);
    flag = 1;
  }
  if (!flag) {
    printf("No duplicates.\n");
  }
  return 0;
}
