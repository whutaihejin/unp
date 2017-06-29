#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/stat.h>

#define MAXLINE 1024

// server
void server(int read_fd, int write_fd) {
  int fd;
  ssize_t n;
  char buff[MAXLINE + 1];

  // read pathname from IPC channel
  if ((n = read(read_fd, buff, MAXLINE)) == 0) {
    printf("end-of-file while reading pathname\n");
    exit(0);
  }
  buff[n] = '\0';

  if ((fd = open(buff, O_RDONLY)) < 0) {
    snprintf(buff + n, sizeof(buff) - n, ": can't open\n");
    n = strlen(buff);
    write(write_fd, buff, n);
  } else {
    // open succeeded: copy file to IPC channel
    while((n = read(fd, buff, MAXLINE)) > 0) {
      write(write_fd, buff, n);
    }
    close(fd);
  }
}
