#!/usr/bin/env python2.7
# Python 2.7 version by Alex Eames of http://RasPi.TV 
# functionally equivalent to the Gertboard buttons test by 
# Gert Jan van Loo & Myra VanInwegen
# Use at your own risk - I'm pretty sure the code is harmless, 
# but check it yourself.

#Eddited button_press.py by Volodymyr Sharovar
#Reads the GPIO pins 23-25. If the state was different from the previous
#state of the pins that means buttons were pressed. Returns that the button was
#pressed and the state of the buttons 
import RPi.GPIO as GPIO
import time
import sys

def button_press():
    GPIO.setmode(GPIO.BCM)                  # initialise RPi.GPIO
    for i in range(23,26):                  # set up ports 23-25 
        GPIO.setup(i, GPIO.IN, pull_up_down=GPIO.PUD_UP)  # as inputs pull-ups high      

    print "The buttons are ready.\n"
    
    previous_state = [GPIO.input(25),GPIO.input(24),GPIO.input(23)]
    while True:
        try:
            current_state = [GPIO.input(25),GPIO.input(24),GPIO.input(23)]
            if current_state != previous_state:
                print "Button was pressed.\n"
                GPIO.cleanup
                return True, current_state

    	except KeyboardInterrupt:          # trap a CTRL+C keyboard interrupt
        	print "\n@@@@@@@@@@@@@@@@@@@@@@@@@@\nCTRL+C was pressed......\n"
        	GPIO.cleanup()                 # resets all GPIO ports used by this program
                return False

        except:
                print "Unexpected error.\n"
                GPIO.cleanup()
                return False
