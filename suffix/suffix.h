#ifndef SUFFIX_H__
#define SUFFIX_H__

static const char* sf = ".txt";
static const int sf_len = 4;

int walk(char* path);

struct option_arg_t {
  char* directory;
};

void usage() {
  printf("usage:\n");
  printf("  suffix -d <directory>\n");
  printf("  suffix -h display help\n");
}

#endif
