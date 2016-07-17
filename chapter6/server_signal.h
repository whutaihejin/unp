#ifndef SERVER_SIGNAL_H__
#define SERVER_SIGNAL_H__

typedef void sig_func_t(int);

sig_func_t* signal(int signo, sig_func_t* func);

void sig_chld(int);
#endif
