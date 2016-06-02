#!/bin/bash

# if Makefile already exists, just rename it
if [ -f Makefile ]; then
  echo 'INFO: find Makefile in current directory, rename it to Makefile.origin instead'
  mv Makefile Makefile.origin
fi

# generate make file body
source_file_list=$(ls *.cc)
for file in $source_file_list ;do
  target_file_list="${target_file_list} ${file/.cc/}"
  target_list="${target_list}${file/.cc/}:\n\tg++ -g -o ${file/.cc/} $file\n"
done

echo -e ".PHONY : ALL" >> Makefile
echo -e "ALL : $target_file_list\n" >> Makefile
echo -e $target_list >> Makefile
echo -e ".PHONY : clean" >> Makefile
echo -e "clean : \n\t rm -rf ${target_file_list}" >> Makefile

# tip for user
echo -e "INFO: done sucessfully"
