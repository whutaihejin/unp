#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <dirent.h>
#include <string.h>
#include <sys/stat.h>
#include "suffix.h"

static const char* option_string = "d:h";

int main(int argc, char* argv[]) {
  struct option_arg_t option;
  char path[1024] = {'.'};
  option.directory = path;
  int opt = 0;
  while ((opt = getopt(argc, argv, option_string)) != -1) {
    switch (opt) {
      case 'd':
        option.directory = optarg;
        break;
      case 'h':
      default:
        usage();
        exit(-1);
    }
  }
  walk(option.directory); 
  return 0;
}

int suffix(const char* name) {
  int n = 0;
  if ((n = strlen(name)) < 4) {
    return -1;
  }
  if (strcmp(name + n - sf_len, sf) != 0) {
    char* new_name = (char*)malloc(n + sf_len + 1);
    strncpy(new_name, name, n + 1);
    strncat(new_name, sf, sf_len);
    rename(name, new_name);
    printf("%s -> %s\n", name, new_name);
    free(new_name);
  }
  return 0;
}

int walk(char* path) {
  struct stat statbuf;
  struct dirent* dirp;
  DIR* dir;
  int n;
  if (lstat(path, &statbuf) < 0) {
    return -1;
  }
  if (S_ISDIR(statbuf.st_mode) == 0) {
    return suffix(path);
  }
  n = strlen(path);
  path[n++] = '/';
  path[n] = 0;
  if ((dir = opendir(path)) == NULL) {
    return -1;
  }
  while ((dirp = readdir(dir)) != NULL) {
    if (strcmp(dirp->d_name, ".") == 0 || strcmp(dirp->d_name, "..") == 0) {
      continue;
    }
    strcpy(&path[n], dirp->d_name);
    if (walk(path) != 0) {
      break;
    }
  }
  path[n-1] = 0;
  if (closedir(dir) < 0) {
    printf("can't close directory %s\n", path);
    return -1;
  }
  return 0;
}
