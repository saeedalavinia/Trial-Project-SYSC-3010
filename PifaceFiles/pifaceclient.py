#!/usr/bin/env python
# By Riyadh Alsegier ; The client side that will receiving from the server  (connecting to Piface to flash leds)
# Eddited by Vlad
import socket 
import converter
import sys

TCP_IP = '134.117.58.42' 
TCP_PORT = 4001   #  this client will be listening on this port
BUFFER_SIZE = 1024 

try:
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)  # initializing and connecting this socket
    print "Connecting...\n"
    s.connect((TCP_IP, TCP_PORT))
except socket.error, (value,message):
    if s:
        s.close()
    print "Could not open socket: " + message
    sys.exit(1)

sum = 0

while True:								#forever loop to accept data from the the server and extract it and pass it to  converter function
    
    try:
        data= s.recv(1024)
        if data:
            print data
            #if data == "[0, 0, 0]\n": print "Button was released\n"
            #else: print "Button was pressed\n"
            sum = converter.dataParser(data,sum)
    except:
        print "\nError :(.\n"
       
        s.close()
        break

