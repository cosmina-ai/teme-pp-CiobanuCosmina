from handlers.base_handler import Handler

class JavaHandler(Handler):
    def handle(self, content: str):
        if 'public static void main' in content or 'import java.' in content:
            return 'java'
        return super().handle(content)
