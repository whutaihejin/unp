#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include "util.h"

#define MAX_SIZE  80

void echo(int);

int main(int argc, char* argv[]) {
  int listenfd, connfd;
  struct sockaddr_in servaddr, cliaddr;
  socklen_t len;
  pid_t pid;
  listenfd = socket(AF_INET, SOCK_STREAM, 0);
  if (listenfd < 0) {
    exit_print("socket error.");
  }
  memset(&servaddr, 0, sizeof(servaddr));
  servaddr.sin_family = AF_INET;
  servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
  servaddr.sin_port = htons(9999);
  bind(listenfd, (struct sockaddr*)&servaddr, sizeof(servaddr));
  listen(listenfd, 10);
  for (; ;) {
    len = sizeof(cliaddr);
    connfd = accept(listenfd, (struct sockaddr*)&cliaddr, &len);
    if ((pid = fork()) == 0) {
      close(listenfd);
      echo(connfd);
      exit(0);
    }
    close(connfd);
  }
  return 0;
}

void echo(int sockfd) {
  char buf[MAX_SIZE];
  ssize_t n;
  while ((n = read(sockfd, buf, sizeof(buf))) > 0) {
    write(sockfd, buf, n);
  }
}
