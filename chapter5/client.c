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
  int sockfd;
  sockfd = socket(AF_INET, SOCK_STREAM, 0);
  if (sockfd < 0) {
    exit_print("socket error.\n");
  }
  memset((void*)&servaddr, 0, sizeof(servaddr));
  servaddr.sin_family = AF_INET;
  servaddr.sin_port = htons(9999);
  inet_aton("127.0.0.1", &servaddr.sin_addr);

  connect(sockfd, (const struct sockaddr*)&servaddr, sizeof(servaddr));
  
  char buf[MAX_SIZE];
  char wbuf[MAX_SIZE];
  while (scanf("%s", buf) != EOF) {
    snprintf(wbuf, sizeof(wbuf), "server: %s", buf);
    write(sockfd, wbuf, strlen(wbuf));
    ssize_t len = read(sockfd, buf, sizeof(buf));
    buf[len] = '\0';
    printf("%s\n", buf);
    fflush(stdout);
  }
  return 0;
}
