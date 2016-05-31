#include <iostream>
#include <map>
char map[256];

void InitMap() {
  // for digits
  for (int i = 0; i < 10; i++) {
    map[i + '0'] = i + '0';
  }
  // for letters
  map['A'] = '2'; map['B'] = '2'; map['C'] = '2';
  map['D'] = '3'; map['E'] = '3'; map['F'] = '3';
  map['G'] = '4'; map['H'] = '4'; map['I'] = '4';
  map['J'] = '5'; map['K'] = '5'; map['L'] = '5';
  map['M'] = '6'; map['N'] = '6'; map['O'] = '6';
  map['P'] = '7'; map['R'] = '7'; map['S'] = '7';
  map['T'] = '8'; map['U'] = '8'; map['V'] = '8';
  map['W'] = '9'; map['X'] = '9'; map['Y'] = '9';
}

int main(int argc, char* argv[]) {
  int lines;
  std::string telephone;
  std::map<std::string, int> counter;
  std::map<std::string, int>::iterator iter;
  InitMap();
  std::cin >> lines;
  for (int i = 0; i < lines; i++) {
    std::cin >> telephone;
    std::string standard;
    for (int i = 0; i < telephone.size(); i++) {
      if (telephone[i] != '-') {
        standard += map[telephone[i]];
      }
    }
    standard.insert(3, 1, '-');
    if ((iter = counter.find(standard)) == counter.end()) {
      counter.insert(std::make_pair<std::string, int>(standard, 1));
    } else {
      iter->second++;
    }
  }
  bool flag = true;
  for (iter = counter.begin(); iter != counter.end(); ++iter) {
    if (iter->second > 1) {
      std::cout << iter->first << " " << iter->second << std::endl;
      flag = false;
    }
  }
  if (flag) {
    std::cout << "No duplicates." << std::endl;
  }
  return 0;
}
