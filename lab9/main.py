from handlers.python_handler import PythonHandler
from handlers.kotlin_handler import KotlinHandler
from handlers.java_handler import JavaHandler

from commands.python_command import PythonCommand
from commands.kotlin_command import KotlinCommand
from commands.java_command import JavaCommand

def build_chain():
    return PythonHandler(
        KotlinHandler(
            JavaHandler()
        )
    )

def get_command(lang: str):
    return {
        'python': PythonCommand(),
        'kotlin': KotlinCommand(),
        'java': JavaCommand(),
    }.get(lang)

def main():
    filename = input("Introdu numele fisierului: ")
    try:
        with open(filename, 'r') as f:
            content = f.read()
    except FileNotFoundError:
        print("Fisierul nu exista.")
        return

    chain = build_chain()
    lang = chain.handle(content)

    if not lang:
        print("Limbaj necunoscut.")
        return

    print(f"Limbaj detectat: {lang}")
    command = get_command(lang)
    output = command.execute(content)
    print("Rezultat executie:\n", output)

if __name__ == "__main__":
    main()
