from handlers.base_handler import Handler

class PythonHandler(Handler):
    def handle(self, content: str):
        if 'def ' in content or 'import ' in content or '__name__' in content:
            return 'python'
        return super().handle(content)
