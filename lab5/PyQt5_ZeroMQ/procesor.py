from pyexpat.errors import messages

import zmq
import re

def validate_and_save(message):
    pattern = re.compile(r"<p>(.*?)</p>") #creaza un model regex
    matches = pattern.findall(message)

    with open("output.html", "w", encoding="utf-8") as f: #deschide fisierul output.html pentru scriere
        for match in matches:
            f.write(f"<p>{match}</p>\n")
        print("validated content saved in output.html")

def main():
    context = zmq.Context() #creaza un context pentru comunicare
    receiver = context.socket(zmq.PULL) #creaza un socket pentru a primi mesaje
    receiver.bind("tcp://*:5555")

    print("Waiting for messages")
    while True:
        message = receiver.recv_string()
        print("Received HTML: \n", message)
        validate_and_save(message)

if __name__ == "__main__":
    main()