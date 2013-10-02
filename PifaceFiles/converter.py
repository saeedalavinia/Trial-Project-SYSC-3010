#Code written by Raditya Budianto(100836539) and Volodymyr Sharovar

import piface.pfio as pfio

#Takes a decimal number and converts it to a binary list. Depending on 
#the binary number turns on/off the LED on piface
def decimalToBinaryConverter(numberToConver):    

    pfio.init()
    binaryListOfNumberToConvert=list(bin(numberToConver))
    binaryListOfBinaryNumber = []
     
    for i in range(len(binaryListOfNumberToConvert)-1, 0, -1):
        if binaryListOfNumberToConvert[i] == 'b':
            break
       
        binaryListOfBinaryNumber.append(binaryListOfNumberToConvert[i])
       
    for i in range(len(binaryListOfBinaryNumber),8):
        binaryListOfBinaryNumber.append(0)
    
    for i in range(7,-1,-1):
        if binaryListOfBinaryNumber[i] == "1":
            pfio.digital_write(i,1)
        else:
            pfio.digital_write(i,0)        	

#Accepts a string of data and the sum. Depending on the string performs
#addition, subtraction or setting to zero on the sum. 
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
    
