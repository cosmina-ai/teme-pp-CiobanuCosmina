from handlers.base_handler import Handler

class KotlinHandler(Handler):
    def handle(self, content: str):
        if 'fun ' in content or 'val ' in content or 'when ' in content:
            return 'kotlin'
        return super().handle(content)
