#include <stdio.h>
#include <signal.h>
#include <sys/wait.h>
#include "server_signal.h"

sig_func_t* signal(int signo, sig_func_t* func) {
 struct sigaction act, oact;
 act.sa_handler = func;
 sigemptyset(&act.sa_mask);
 act.sa_flags = 0;
 if (sigaction(signo, &act, &oact) < 0) {
  return SIG_ERR;
 }
 return oact.sa_handler;
}

void sig_chld(int signo) {
 pid_t pid;
 int stat;
 pid = wait(&stat);
 printf("child %d terminated\n", pid);
 return;
}
