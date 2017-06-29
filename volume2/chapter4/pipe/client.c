#include <stdio.h>
#include <unistd.h>
#include <string.h>

#define MAXLINE 1024
// client
void client(int read_fd, int write_fd) {
  size_t len;
  ssize_t n;
  char buff[MAXLINE];
 
  // read pathname
  fgets(buff, MAXLINE, stdin);
  len = strlen(buff);
  if (buff[len -1] == '\n') {
    len--;
  }

  // write pathname to IPC channel
  write(write_fd, buff, len);

  // read from IPC, write to standard output
  while ((n = read(read_fd, buff, MAXLINE)) > 0) {
    write(STDOUT_FILENO, buff, n);
  }
}
