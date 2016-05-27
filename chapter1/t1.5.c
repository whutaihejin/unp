#include <stdio.h>
#include <stdio.h>
#include <stdio.h>
#include <stdlib.h>
#include <getopt.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <time.h>
#include <string.h>

#define MAXLINE 80

void usage() {
  printf("usage:\n");
  printf("  -h help message\n");
  printf("  -p port\n");
}

int main(int argc, char* argv[]) {
  int opt;
  uint32_t port = 8989;
  while ((opt = getopt(argc, argv, "hp:")) != -1) {
    switch (opt) {
      case 'h':
        usage();
        exit(0);
      case 'p':
        if (optarg != NULL) {
          port = atoi(optarg);
        }
        break;
      default:
        usage();
        exit(1);
    }
  }

  int listenfd, connfd;
  struct sockaddr_in servaddr;
  char buff[MAXLINE];
  time_t tick;
  
  listenfd = socket(AF_INET, SOCK_STREAM, 0);
  
  memset(&servaddr, 0, sizeof(servaddr));
  servaddr.sin_family = AF_INET;
  servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
  servaddr.sin_port = htons(port); /* daytime server */

  if (bind(listenfd, (struct sockaddr*) &servaddr, sizeof(servaddr)) < 0) {
    printf("bind error, abort\n");
    exit(1);
  }

  listen(listenfd, 10);

  for ( ; ; ) {
    connfd = accept(listenfd, (struct sockaddr*)NULL, NULL);
    tick = time(NULL);
    snprintf(buff, sizeof(buff), "%.24s\r\n", ctime(&tick));
    printf("time=%s\n", buff);
    fflush(stdout);
    write(connfd, buff, strlen(buff));
    close(connfd);
  }

  return 0;
}
