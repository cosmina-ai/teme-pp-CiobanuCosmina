from commands.command import Command
import subprocess
import tempfile

class PythonCommand(Command):
    def execute(self, content: str) -> str:
        with tempfile.NamedTemporaryFile(mode='w', suffix='.py', delete=False) as f:
            f.write(content)
            f.flush()
            result = subprocess.run(['python3', f.name], capture_output=True, text=True)
            return result.stdout if result.returncode == 0 else result.stderr
