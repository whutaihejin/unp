#include <stdio.h>
#include <stdlib.h>
#include <getopt.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
int MAXLINE = 10;

void usage() {
  printf("usage:\n");
  printf("  -h display this help message\n");
  printf("  -h host ip\n");
  printf("  -p port\n");
}

int main(int argc, char* argv[]) {
  int opt;
  char* ip;
  uint32_t port;
  while ((opt = getopt(argc, argv, "?h:p:")) != -1) {
    switch (opt) {
      case '?':
        usage();
        exit(0);
      case 'h':
        ip = optarg;
        break;
      case 'p':
        port = atoi(optarg);
        break;
      default:
        usage();
        exit(1);
    }
  }

  int sockfd, n;
  char recvline[MAXLINE + 1];
  struct sockaddr_in servaddr;
  
  if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
    printf("socket error\n");
  }
  memset(&servaddr, 0, sizeof(servaddr));
  servaddr.sin_family = AF_INET;
  servaddr.sin_port = htons(port);
  if (inet_pton(AF_INET, ip, &servaddr.sin_addr) <= 0) {
    printf("inet_pton error for %s\n", ip);
    exit(1);
  }
  if (connect(sockfd, (struct sockaddr*) &servaddr, sizeof(servaddr)) < 0) {
    printf("connect error\n");
    return 0;
  }
  while ((n = read(sockfd, recvline, MAXLINE)) > 0) {
    recvline[n] = 0;
    if (fputs(recvline, stdout) == EOF) {
      printf("fputs error\n");
    }
  }
  if (n < 0) {
    printf("read error\n");
  }
  return 0;
}
