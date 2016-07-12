#include <time.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <netinet/in.h>
#include <sys/socket.h>

#define MAXLINE 80

int main(int argc, char* argv[]) {
  int listenfd, connfd;
  struct sockaddr_in servaddr;
  char buf[MAXLINE];
  time_t tick;

  listenfd = socket(AF_INET, SOCK_STREAM, 0);
  memset(&servaddr, 0, sizeof(servaddr));
  servaddr.sin_family = AF_INET;
  servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
  servaddr.sin_port = htons(9999);
  bind(listenfd, (const struct sockaddr*)&servaddr, sizeof(servaddr));
  listen(listenfd, 10);
  for (; ;) {
    connfd = accept(listenfd, (struct sockaddr*)NULL, NULL);
    tick = time(NULL);
    snprintf(buf, sizeof(buf), "%.24s\n", ctime(&tick));
    write(connfd, buf, strlen(buf));
    close(connfd);
  }

  return 0;
}
