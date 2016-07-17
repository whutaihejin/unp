#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include "util.h"

#define MAX_SIZE 80

int main(int argc, char* argv[]) {
  struct sockaddr_in servaddr;
  int i, sockfd[1];
  for (i = 0; i < 1; i++) {
    if ((sockfd[i] = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
      exit_print("socket error.\n");
    }
    memset((void*)&servaddr, 0, sizeof(servaddr));
    servaddr.sin_family = AF_INET;
    servaddr.sin_port = htons(9999);
    inet_aton("127.0.0.1", &servaddr.sin_addr);

    connect(sockfd[i], (const struct sockaddr*)&servaddr, sizeof(servaddr));
  }
  
  char sendbuf[MAX_SIZE];
  char recvbuf[MAX_SIZE];
  ssize_t len;
  while (fgets(sendbuf, sizeof(sendbuf), stdin) != NULL) {
    if (write(sockfd[0], sendbuf, strlen(sendbuf)) < 0) {
      exit_print("client write error.\n");
    }
    sleep(1);
    if (write(sockfd[0], sendbuf + 1, strlen(sendbuf) - 1) < 0) {
      exit_print("client write error.\n");
    }
    if ((len = read(sockfd[0], recvbuf, sizeof(recvbuf))) <= 0) {
      exit_print("client read error: server terminated prematurely.\n");
    }
    recvbuf[len] = '\0';
    fputs("client: ", stdout);
    fputs(recvbuf, stdout);
    fflush(stdout);
  }
  return 0;
}
