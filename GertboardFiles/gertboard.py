#!/usr/bin/env python

import socket
import time
import sys
from button_press import button_press

TCP_IP = '134.117.58.33'
#TCP_IP = '10.0.0.53'
TCP_PORT = 4000
#TCP_PORT = 5005
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
        isPressed,state = button_press()
        if isPressed:
            print str(state)+"\n"
            s.sendall(str(state)+"\n")
        else:
            s.sendall('Error occured\n')
            s.close()
            break
    except socket.error, e:
        print "Error sending data: %s" % e
        sys.exit(1)
    except KeyboardInterrupt:
        print "\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@\nCTRL+C was pressed.\n"
        s.sendall('CTRL+C\n')
        s.close()
        break
    except:
        print "Error.\n"
        s.sendall('CTRL+C\n')
        s.close()
        break

