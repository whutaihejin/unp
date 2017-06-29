#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

void client(int, int);
void server(int, int);

int main(int argc, char* argv[]) {
  int pipe1[2];
  int pipe2[2];
  pid_t child_pid;
  // create pipe1
  if (pipe(pipe1) != 0) {
    printf("create pipe1 error\n");
    exit(1);
  }
  // create pipe2 
  if (pipe(pipe1) != 0) {
    printf("create pipe1 error\n");
    exit(1);
  }
 
  // child
  if ((child_pid = fork()) == 0) {
    close(pipe1[1]);
    close(pipe2[0]);
    
    sleep(6);
    server(pipe1[0], pipe2[1]);
    exit(0);
  }

  // parent
  close(pipe1[0]);
  close(pipe2[1]);

  client(pipe2[0], pipe1[1]);

  // wait for child to terminate
  waitpid(child_pid, NULL, 0);
  return 0;
}
