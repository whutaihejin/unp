#include <iostream>
#include <vector>
class Solution {
  public:
    std::vector<int> twoSum(std::vector<int>& nums, int target) {
      int i, j;
      bool found = false;
      for (i = 0; i < nums.size() - 1 && !found; i++) {
        for (j = i + 1; j < nums.size(); j++) {
          if (nums[i] + nums[j] == target) {
            found = true;
            break;
          }
        }
      }
      std::vector<int> result;
      result.push_back(i - 1);
      result.push_back(j);
      return result;
    }
};
int main(int argc, char* argv[]) {
  Solution solution;
  std::vector<int> nums;
  nums.push_back(15);
  nums.push_back(7);
  nums.push_back(11);
  nums.push_back(2);
  std::vector<int> result = solution.twoSum(nums, 9);
  for (int i = 0; i < result.size(); i++) {
    std::cout << result[i] << " ";
  }
  return 0;
}
