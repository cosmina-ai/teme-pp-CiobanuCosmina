import subprocess
import os

class JavaCommand:
    def execute(self, content: str) -> str:
        #numele clasei
        lines = content.splitlines()
        class_name = None
        for line in lines:
            if "class" in line:
                words = line.strip().split()
                if "class" in words:
                    idx = words.index("class")
                    if idx + 1 < len(words):
                        class_name = words[idx + 1].split("{")[0]
                        break

        if not class_name:
            return "Nu s-a putut detecta numele clasei Java."

        file_name = f"{class_name}.java"

        #fisierul cu numele clasei
        with open(file_name, "w") as f:
            f.write(content)

        # Căi absolute către javac și java din SDKMAN
        javac_path = "/home/cosmina/.sdkman/candidates/java/current/bin/javac"
        java_path = "/home/cosmina/.sdkman/candidates/java/current/bin/java"

        #compilarea
        compile_result = subprocess.run([javac_path, file_name], capture_output=True, text=True)
        if compile_result.returncode != 0:
            os.remove(file_name)
            return compile_result.stderr

        run_result = subprocess.run([java_path, class_name], capture_output=True, text=True)

        if os.path.exists(f"{class_name}.class"):
            os.remove(f"{class_name}.class")
        os.remove(file_name)

        return run_result.stdout if run_result.returncode == 0 else run_result.stderr
