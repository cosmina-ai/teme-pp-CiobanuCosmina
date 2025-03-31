import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

class Calculator extends JFrame {
    JButton digits[] = {
            new JButton(" 0 "),
            new JButton(" 1 "),
            new JButton(" 2 "),
            new JButton(" 3 "),
            new JButton(" 4 "),
            new JButton(" 5 "),
            new JButton(" 6 "),
            new JButton(" 7 "),
            new JButton(" 8 "),
            new JButton(" 9 ")
    };

    JButton operators[] = {
            new JButton(" + "),
            new JButton(" - "),
            new JButton(" * "),
            new JButton(" / "),
            new JButton(" = "),
            new JButton(" C "),
            new JButton(" ( "),
            new JButton(" ) ")
    };

    String oper_values[] = {"+", "-", "*", "/", "=", "", "(", ")"};

    String value;
    char operator;

    JTextArea area = new JTextArea(3, 5);

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setSize(245, 275);
        calculator.setTitle(" Java-Calc, PP Lab1 ");
        calculator.setResizable(false);
        calculator.setVisible(true);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Calculator() {
        add(new JScrollPane(area), BorderLayout.NORTH);
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new FlowLayout());

        for (int i=0;i<10;i++)
            buttonpanel.add(digits[i]);

        for (int i=0;i<8;i++)
            buttonpanel.add(operators[i]);

        add(buttonpanel, BorderLayout.CENTER);
        area.setForeground(Color.BLACK);
        area.setBackground(Color.WHITE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);

        for (int i=0;i<10;i++) {
            int finalI = i;
            digits[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    area.append(Integer.toString(finalI));
                }
            });
        }

        for (int i=0;i<8;i++){
            int finalI = i;
            operators[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if (finalI == 5)
                        area.setText("");
                    else if (finalI == 4) {
                        try {
                            String expression = area.getText();
                            double result = evaluateExpression(expression);
                            area.append(" = " + result);
                        } catch (Exception e) {
                            area.setText(" !!!Probleme!!! ");
                        }
                    }
                    else {
                        area.append(oper_values[finalI]);
                        if (finalI < 4) {
                            operator = oper_values[finalI].charAt(0);
                        }
                    }
                }
            });
        }
    }

    private double evaluateExpression(String expression) {
        String postfix = infixToPostfix(expression);
        return evaluatePostfix(postfix);
    }

    private String infixToPostfix(String expression) {
        String output = "";
        char[] stack = new char[expression.length()];
        int top = -1;

        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch)) {
                output += ch;
            } else if (ch == '(') {
                stack[++top] = ch;
            } else if (ch == ')')//xtragem operatorii pana la (
            {
                while (top >= 0 && stack[top] != '(') {
                    output += " " + stack[top--];
                }
                top--;
            } else if (isOperator(ch)) {
                output += " ";
                while (top >= 0 && precedence(stack[top]) >= precedence(ch)) {
                    output += stack[top--] + " ";
                }
                stack[++top] = ch;
            }
        }

        while (top >= 0) {
            output += " " + stack[top--];
        }
        return output;
    }

    private double evaluatePostfix(String postfix) {
        double[] stack = new double[postfix.length()];
        int top = -1;
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {
            if (token.matches("\\d+")) //daca token este nr
            {
                stack[++top] = Double.parseDouble(token);
            } else if (isOperator(token.charAt(0))) //daca token este operator
            {
                double b = stack[top--];//extrag din stiva
                double a = stack[top--];//extrag din stiva
                switch (token.charAt(0)) {
                    case '+': stack[++top] = a + b; break;
                    case '-': stack[++top] = a - b; break;
                    case '*': stack[++top] = a * b; break;
                    case '/': stack[++top] = a / b; break;
                }
            }
        }
        return stack[top]; //rezultat expresie
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private int precedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }
}
