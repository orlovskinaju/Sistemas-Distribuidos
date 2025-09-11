import socket
import threading

HOST = "127.0.0.1"  
PORT = 5000

cliente = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
cliente.connect((HOST, PORT))

def receber():
    while True:
        try:
            data = cliente.recv(1024)
            if data:
                print(data.decode())
        except:
            break


thread = threading.Thread(target=receber)
thread.start()


while True:
    msg = input()
    cliente.sendall(msg.encode())
