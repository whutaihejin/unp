#include <iostream>
#include <vector>
#include <algorithm>

int kth(std::vector<int>& vec1, int s1, int len1, std::vector<int>& vec2, int s2, int len2, int k) {
  if (len1 > len2) {
    return kth(vec2, s2, len2, vec1, s1, len1, k);
  }
  if (len1 == 0) {
    return vec2[s2 + k - 1];
  }
  if (k == 1) {
    return std::min<int>(vec1[s1], vec2[s2]);
  }
  if (k == 2 && len1 == 1 && len2 == 1) {
    return std::max<int>(vec1[s1], vec2[s2]);
  }
  int i = std::min<int>(k / 2, len1);
  int j = std::min<int>(k / 2, len2);
  if (vec1[s1 + i - 1] > vec2[s2 + j - 1]) {
    return kth(vec1, s1, len1, vec2, s2 + j, len2 - j, k - j);
  } else {
    return kth(vec1, s1 + i, len1 - i, vec2, s2, len2, k - i);
  }
}

double median(std::vector<int>& vec1, std::vector<int>& vec2) {
  double value = 0;
  int len1 = vec1.size();
  int len2 = vec2.size();
  int pivot = (len1 + len2) / 2;
  if (((vec1.size() + vec2.size()) & 0x01) == 1) {
    value = kth(vec1, 0, len1, vec2, 0, len2, (len1 + len2 + 1) / 2);
  } else {
    value = (kth(vec1, 0, len1, vec2, 0, len2, pivot) 
           + kth(vec1, 0, len1, vec2, 0, len2, pivot + 1)) / 2.0;
  }
  return value;
}

int main(int argc, char* argv[]) {
  std::vector<int> vec1;
  std::vector<int> vec2;
  vec2.push_back(2);
  vec2.push_back(3);
  std::cout << "median=" << median(vec1, vec2) << std::endl;
  return 0;
}
