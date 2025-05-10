from commands.command import Command
import subprocess
import tempfile
import os

class KotlinCommand(Command):
    def execute(self, content: str) -> str:
        with tempfile.NamedTemporaryFile(mode='w', suffix='.kt', delete=False) as f:
            f.write(content)
            f.flush()

            kotlinc_path = "/home/cosmina/.sdkman/candidates/kotlin/current/bin/kotlinc"
            java_path = "/home/cosmina/.sdkman/candidates/java/current/bin/java"
            java_home = "/home/cosmina/.sdkman/candidates/java/current"

            env = os.environ.copy()
            env["JAVA_HOME"] = java_home
            env["PATH"] = f"{java_home}/bin:" + env["PATH"]

            # compileaza fișierul Kotlin într-un .jar
            result = subprocess.run(
                [kotlinc_path, f.name, '-include-runtime', '-d', 'program.jar'],
                capture_output=True, text=True, env=env
            )
            if result.returncode != 0:
                os.remove(f.name)
                return result.stderr

            # Ruleaza
            result = subprocess.run([java_path, '-jar', 'program.jar'], capture_output=True, text=True)

            #curat fisiere
            os.remove(f.name)
            os.remove('program.jar')

            return result.stdout if result.returncode == 0 else result.stderr
