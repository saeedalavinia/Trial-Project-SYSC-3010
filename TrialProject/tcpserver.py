#!/usr/bin/env python

import socket

TCP_IP = '10.0.0.53'
TCP_PORT = 5005
BUFFER_SIZE = 1024

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((TCP_IP, TCP_PORT))
s.listen(1)

conn, addr = s.accept()
print 'Connection address:', addr
while True:
    data = conn.recv(BUFFER_SIZE)
    if data: print data
    #conn.send(data)
    if data == "CTRL+C\n":
        break
    if data == "Error occured\n":
        break
