#Code written by Raditya Budianto(100836539) and Vlad

import piface.pfio as pfio

def decimalToBinaryConverter(number):    

    pfio.init()
    x=list(bin(number))
    y= []
     
    for i in range(len(x)-1, 0, -1):
        if x[i] == 'b':
            break
       
        y.append(x[i])
       
    for k in range(len(y),8):
        y.append(0)
    
    for i in range(7,-1,-1):
        if y[i] == "1":
            pfio.digital_write(i,1)
        else:
            pfio.digital_write(i,0)        	

def dataParser(data, sum):

    if data == "[0, 0, 0]\n":
        pass
    elif data == "[1, 0, 0]\n":
        if sum == 255:
            print "\nMATH ERROR: Maximum number reached. Please either reset or subtract.\n"
            return sum
        sum += 1 
    elif data == "[0, 1, 0]\n":
        if sum == 0:
            print "\nMATH ERROR: Cannot subtract from 0.\n"
            return sum   
        sum -= 1
    elif data == "[0, 0, 1]\n":
        print "\nReset is pressed. Reseting back to 0...\n"
        sum = 0
    else:
        print "\nMultiple buttons pressed. Please press one of the three buttons.\n"
            
    decimalToBinaryConverter(sum)
    return sum
    
