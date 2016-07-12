.PHONY : ALL
ALL : daytime_server daytime_client server client

daytime_server:
	gcc -g -o daytime_server t1.5.c

daytime_client:
	gcc -g -o daytime_client t1.2.c

server:
	gcc -g -Wall -o server server.c

client:
	gcc -g -Wall -o client client.c

.PHONY : clean
clean:
	rm -rf daytime_server daytime_client server client
