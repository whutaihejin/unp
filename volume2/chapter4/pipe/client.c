#include <stdio.h>
#include <unistd.h>
#include <string.h>

#define MAXLINE 1024
// client
void client(const char* pathname, int read_fd, int write_fd) {
  size_t len;
  ssize_t n;
  char buff[MAXLINE];
 
  // read pathname
  len = strlen(pathname);
  if (buff[len -1] == '\n') {
    len--;
  }

  // write pathname to IPC channel
  write(write_fd, pathname, len);

  // read from IPC, write to standard output
  while ((n = read(read_fd, buff, MAXLINE)) > 0) {
    write(STDOUT_FILENO, buff, n);
  }
}
