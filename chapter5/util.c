#include "util.h"

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
