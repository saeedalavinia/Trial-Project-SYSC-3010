Preperation for Hardware
------------------------

1.Mount Piface on the raspberry pi that will run piface scripts

2.Mount the Gertboard on the raspberry pi that will run the gertboard scripts

3.For the Gertboard:

	i.	Connect GP25-GP23 pins to B1-B3 using Female-to-Female GPIO cable connector

	ii.	Connect the jumper for pins B1,B2,B3;out. 

	iii.	Connect the jumper to the top 2 pin header of J7 for 3.3V

Preparation for the connection
------------------------------

1. Make sure the ports mentioned in the server and raspberry pies are not already occupied

2.Make sure the the pc (server) can ping eachother

Running the Server and Clients
------------------------------

3. Start the  TCP java server on a pc. the server will wait for clients to join it.

4. Start the client code on the raspberry pie connected to the Gretboard

5. Make sure the clients connects to the TCP server successfully

6. Start the client code on the raspberry pie connected to the pi face

7. Make sure the clients connects to the TCP server successfully
