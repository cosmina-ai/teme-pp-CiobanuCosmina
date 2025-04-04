import sys
import zmq
from PyQt5.QtWidgets import QApplication, QWidget, QVBoxLayout, QPushButton, QFileDialog, QTextEdit, QLineEdit

class FileConverter(QWidget):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle("Text HTML converter")
        layout = QVBoxLayout()

        self.file_path_input = QLineEdit(self)
        layout.addWidget(self.file_path_input)

        browse_button = QPushButton("Browse")
        browse_button.clicked.connect(self.browse_file)
        layout.addWidget(browse_button)

        convert_button = QPushButton("Convert && Send")
        convert_button.clicked.connect(self.convert_and_send)
        layout.addWidget(convert_button)

        self.output_text = QTextEdit(self)
        layout.addWidget(self.output_text)

        self.setLayout(layout)

    def browse_file(self):
        options = QFileDialog.Options()
        file_path, _ = QFileDialog.getOpenFileName(self, "Select Text File", "", "Text Files (*.txt);;All Files (*)", options=options)
        if file_path:
            self.file_path_input.setText(file_path)


    def convert_and_send(self):
        file_path = self.file_path_input.text()
        if not file_path:
            self.output_text.setText("Select a file")
            return

        try:
            with open(file_path, "r", encoding="utf-8") as f:
                content = f.readlines()

            html_content = "<html><body>\n"
            html_content += f"<h1>{content[0].strip()}</h1>\n"
            for line in content[1:]:
                html_content += f"<p>{line.strip()}</p>\n"
            html_content += "</body></html>"

            self.output_text.setText(html_content)


            # trimit catre procesor

            context = zmq.Context()
            socket = context.socket(zmq.PUSH)
            socket.connect("tcp://localhost:5555")
            socket.send_string(html_content)

        except Exception as e:
            self.output_text.setText(f"Error: {str(e)}")


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window=FileConverter()
    window.show()
    sys.exit(app.exec_())


