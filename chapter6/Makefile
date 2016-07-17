.PHONY : ALL
ALL :  client server

client:
	gcc -Wall -g -o client client.c
server:
	gcc -Wall -g -o server server.c server_signal.c

.PHONY : clean
clean : 
	 rm -rf  client server
