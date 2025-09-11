import socket
import threading

HOST = "127.0.0.1"      
PORT = 5000

clientes = []  
def atender_cliente(conn, addr):
    print(f"Novo cliente conectado: {addr}")
    while True:
        try:
            data = conn.recv(1024)
            if not data:
                break
            mensagem = data.decode()
            print(f"{addr} disse: {mensagem}")

            for cliente in clientes:
                if cliente != conn:
                    cliente.sendall(f"{addr} disse: {mensagem}".encode())
        except:
            break

    print(f"Cliente {addr} desconectou.")
    clientes.remove(conn)
    conn.close()

# Configuração do servidor
servidor = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
servidor.bind((HOST, PORT))
servidor.listen()

print(f"Servidor rodando em {HOST}:{PORT}...")

while True:
    conn, addr = servidor.accept()
    clientes.append(conn)
    thread = threading.Thread(target=atender_cliente, args=(conn, addr))
    thread.start()
