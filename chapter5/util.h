#ifndef UTIL_H__
#define UTIL_H__

#include <stdlib.h>

inline void exit_print(const char* prompt) {
  printf("%s", prompt);
  exit(1);
}

#endif
