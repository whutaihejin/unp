#include <iostream>

bool doMatch(const std::string& s, int i, int ls,const std::string& p, int j, int lp) {
  if (j == lp) return i == ls;
  if (j + 1 == lp || p[j + 1] != '*') {
    return (s[i] == p[j] || p[j] == '.') && doMatch(s, i + 1, ls, p, j + 1, lp);
  }
  while (i < ls && (s[i] == p[j] || p[j] == '.')) {
    if (doMatch(s, i, ls, p, j + 2, lp)) return true;
    i++;
  }
  return doMatch(s, i, ls, p, j + 2, lp);
}

bool match(const std::string& s,const std::string& p) {
  return doMatch(s, 0, s.size(), p, 0, p.size());
}

void doTest(const std::string& s, const std::string& p, bool expect) {
  bool result = match(s, p);
  std::cout << (expect == match(s, p)) << ", expect=" << expect << ", result=" << result << ", s=" << s << ", p=" << p << std::endl;
}

int main(int argc, char* argv[]) {
  doTest("a", "ab*", true);
  doTest("abbbc", "ab*c", true);
  doTest("ac", "ab*c", true);
  doTest("abbc", "ab*bbc", true);
  doTest("abcbcd", "a.*c.*d", true);
  doTest("", "", true);
  doTest("", "a", false);
  doTest("aaa", "a", false);
  doTest("aa", "aa", true);
  doTest("aaa", "aa", false);
  doTest("aa", ".*", true);
  doTest("ab", ".*", true);
  doTest("", ".*", true);
  return 0;
}
