import subprocess

def executa_pipeline(comanda):
    comenzi = [cmd.strip().split() for cmd in comanda.split('|')]

    proc_anterior = None

    for i, cmd in enumerate(comenzi):
        if i == 0:
            proc = subprocess.Popen(cmd, stdout=subprocess.PIPE)
        else:
            proc = subprocess.Popen(cmd, stdin=proc_anterior.stdout, stdout=subprocess.PIPE)
            proc_anterior.stdout.close()
        proc_anterior = proc

    rezultat_final, _ = proc.communicate()
    print("Rezultat:\n", rezultat_final.decode())


if __name__ == '__main__':
    try:
        comanda = input("Introduceți comanda (de exemplu: ip a | grep inet | wc -l):\n> ")
        executa_pipeline(comanda)
    except Exception as e:
        print("Eroare:", e)
