#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <sys/select.h>
#include "util.h"

#define MAX_SIZE 80

void str_cli(FILE* fp, int sockfd);

int main(int argc, char* argv[]) {
  struct sockaddr_in servaddr;
  int sockfd;
  if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
    exit_print("socket error.\n");
  }
  memset((void*)&servaddr, 0, sizeof(servaddr));
  servaddr.sin_family = AF_INET;
  servaddr.sin_port = htons(9999);
  inet_aton("127.0.0.1", &servaddr.sin_addr);

  connect(sockfd, (const struct sockaddr*)&servaddr, sizeof(servaddr));
  str_cli(stdin, sockfd);
  return 0;
}

void str_cli(FILE* fp, int sockfd) {
  char sendbuf[MAX_SIZE];
  char recvbuf[MAX_SIZE];
  ssize_t len;
  int maxfdp1;
  fd_set rset;
  FD_ZERO(&rset);
  for (;;) {
    FD_SET(fileno(fp), &rset);
    FD_SET(sockfd, &rset);
    maxfdp1 = (fileno(fp) > sockfd ? fileno(fp) : sockfd) + 1;
    select(maxfdp1, &rset, NULL, NULL, NULL);
    // socket is readable
    if (FD_ISSET(sockfd, &rset)) {
      if ((len = read(sockfd, recvbuf, sizeof(recvbuf))) <= 0) {
        exit_print("client read error: server terminated prematurely.\n");
      }
      recvbuf[len] = '\0';
      fputs(recvbuf, stdout);
    }
    // input is readable
    if (FD_ISSET(fileno(fp), &rset)) {
      if (fgets(sendbuf, sizeof(sendbuf), fp) == NULL) {
        return;
      }
      if (write(sockfd, sendbuf, strlen(sendbuf)) < 0) {
          exit_print("client write error.\n");
      }
    }
  }
}
