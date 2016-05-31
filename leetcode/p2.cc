#include <iostream>
#include <assert.h>

struct ListNode {
  int val;
  ListNode* next;
  ListNode(int x): val(x), next(NULL) {}
};

class Solution {
  public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
      int carry = 0;
      ListNode *head = NULL, *tail = NULL;
      for (; l1 != NULL && l2 != NULL; l1 = l1->next, l2 = l2->next) {
        int sum = l1->val + l2->val + carry;
        ListNode* p = new ListNode(sum % 10);
        carry = sum / 10;
        if (head == NULL) {
          head = p;
          tail = p;
        } else {
          tail->next = p;
          tail = p;
        }
      }
      for (; l1 != NULL; l1 = l1->next) {
        int sum = l1->val + carry;
        ListNode* p = new ListNode(sum % 10);
        carry = sum / 10;
        if (head == NULL) {
          head = p;
          tail = p;
        } else {
          tail->next = p;
          tail = p;
        }
      }
      for (; l2 != NULL; l2 = l2->next) {
        int sum = l2->val + carry;
        ListNode* p = new ListNode(sum % 10);
        carry = sum /10;
        if (head == NULL) {
          head = p;
          tail = p;
        } else {
          tail->next = p;
          tail = p;
        }
      }
      if (carry != 0) {
        ListNode* p = new ListNode(carry);
        tail->next = p;
        tail = p;
      }
      return head;
    }
};

void test1() {
  // list1
  ListNode* l1 = new ListNode(2);
  ListNode* e2 = new ListNode(4);
  l1->next = e2;
  ListNode* e3 = new ListNode(3);
  e2->next = e3;
  // list2
  ListNode* l2 = new ListNode(5);
  ListNode* l2e2 = new ListNode(6);
  l2->next = l2e2;
  ListNode* l2e3 = new ListNode(4);
  l2e2->next = l2e3;
  Solution solution;
  ListNode* result = solution.addTwoNumbers(l1, l2);
  assert(result->val == 7);
  assert(result->next->val == 0);
  assert(result->next->next->val == 8);
}

void test2() {
  // list1
  ListNode* l1 = new ListNode(9);
  ListNode* l2 = new ListNode(1);
  Solution solution;
  ListNode* result = solution.addTwoNumbers(l1, l2);
  assert(result->val == 0);
  assert(result->next->val == 1);
}

int main(int argc, char* argv[]) {
  test1();
  test2();
  return 0;
}
