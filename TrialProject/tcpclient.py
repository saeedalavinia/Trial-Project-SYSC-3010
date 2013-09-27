#!/usr/bin/env python

import socket
import sys
from button_press import button_press

TCP_IP = '10.0.0.53'
TCP_PORT = 5005
BUFFER_SIZE = 1024

try:
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    print "Connecting...\n"
    s.connect((TCP_IP, TCP_PORT))
except socket.error, (value,message):
    if s:
        s.close()
    print "Could not open socket: " + message
    sys.exit(1)

while True:
    
    try:
        if button_press():
            s.sendall('Button was pressed\n')
        else:
            s.sendall('Error occured\n')
    except:
        print "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@\nCTRL+C was pressed.\n"
        s.sendall('CTRL+C\n')
        s.close()
        break



