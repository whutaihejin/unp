#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <errno.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include "util.h"
#include "server_signal.h"

#define MAX_SIZE  80

void echo(int);

int main(int argc, char* argv[]) {
  int listenfd, connfd;
  struct sockaddr_in servaddr, cliaddr;
  socklen_t len;
  pid_t pid;
  listenfd = socket(AF_INET, SOCK_STREAM, 0);
  if (listenfd < 0) {
    exit_print("socket error.\n");
  }
  memset(&servaddr, 0, sizeof(servaddr));
  servaddr.sin_family = AF_INET;
  servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
  servaddr.sin_port = htons(9999);
  if (bind(listenfd, (struct sockaddr*)&servaddr, sizeof(servaddr)) != 0) {
    exit_print("bind error.\n");
  }
  if (listen(listenfd, 10) != 0) {
    exit_print("listen error.\n");
  }
  signal(SIGCHLD, sig_chld);
  for (; ;) {
    len = sizeof(cliaddr);
    if ((connfd = accept(listenfd, (struct sockaddr*)&cliaddr, &len)) < 0) {
      if (errno == EINTR) {
        continue;
      } else {
        exit_print("accept error.\n");
      }
    }
    if ((pid = fork()) == 0) {
      close(listenfd);
      echo(connfd);
      exit(0);
    } else if (pid < 0) {
      exit_print("fork error.\n");
    }
    close(connfd);
  }
  return 0;
}

void echo(int sockfd) {
  char buf[MAX_SIZE];
  ssize_t n;
  while ((n = read(sockfd, buf, sizeof(buf))) > 0) {
    buf[n] = '\0';
    printf("server: %s", buf);
    fflush(stdout);
    write(sockfd, buf, n);
  }
}
