#!/usr/bin/env python
import socket
HOST = "localhost"
PORT = 8989
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect((HOST, PORT))
#sock.sendall("gaRblEDYGoop\n")
#sock.sendall("UNKNOWN\n")
sock.sendall("COLUMBIAN\n")
#sock.sendall("DONUT_SHOP\n")
#sock.sendall("FLAVORED_GOODNESS\n")
data = sock.recv(1024)
sock.close()
