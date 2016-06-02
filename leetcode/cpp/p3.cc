#include <iostream>
#include <ctype.h>
#include <assert.h>
#include <string.h>
class Solution {
  public:
    int lengthOfLongestSubstring(std::string s) {
      int length = s.size();
      if (length <= 1) return length;
      unsigned char flag[256];
      memset(flag, 0, sizeof(flag));
      int i = 0, j = 0, maxLength = 1;
      for (; i < length; i++) {
        for (j = i; j < length; j++) {
          char ch = s[j];
          if (flag[ch] == 0) {
            flag[ch] = 1;
          } else {
            maxLength = j - i > maxLength ? j - i : maxLength;
            memset(flag, 0, sizeof(flag));
            break;
          }
        }
        if (j == length) break;
      }
      return j - i > maxLength ? j - i : maxLength;
    }
    
};

void test2() {
  Solution solution;
  std::string str0("xbawtvebluuagttbeqbihnlucpmg");
  assert(solution.lengthOfLongestSubstring(str0) == 12);
  std::string str("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~ ");
  std::cout << solution.lengthOfLongestSubstring(str) << " " << str.size();
  std::string str1("abcabcbb");
  assert(solution.lengthOfLongestSubstring(str1) == 3);
  std::string str2("bbbbb");
  assert(solution.lengthOfLongestSubstring(str2) == 1);
  std::string str3("pwwkew");
  assert(solution.lengthOfLongestSubstring(str3) == 3);
  std::string str4;
  assert(solution.lengthOfLongestSubstring(str4) == 0);
}

int main(int argc, char* argv[]) {
  test2();
  std::string str;
  std::cin >> str;
  Solution solution;
  std::cout << solution.lengthOfLongestSubstring(str);
  return 0;
}
