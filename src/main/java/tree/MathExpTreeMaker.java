package tree;

import calculator.Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathExpTreeMaker extends Calculator {

    public static class ExpressionTree {

        static class Node {
            private Object value;
            private Node parent;
            private Node left;
            private Node right;

            public Node(Object value) { this.value = value; }

            public Object getValue() { return value; }
            public Node getParent() { return parent; }
            public Node getLeft() { return left; }
            public Node getRight() { return right; }

            public boolean hasLeft() { return left != null; }
            public boolean hasRight() { return right != null; }

            public void setParent(Node parent) { this.parent = parent; }
            public void setLeft(Node left) { this.left = left; }
            public void setRight(Node right) { this.right = right; }
        }
        private final Node root;

        public ExpressionTree(Node root) { this.root = root; }

        public Node getRoot() { return root; }

        public String getPreorder() {
            Stack<Node> stack = new Stack<>();
            stack.add(root);
            StringBuilder preorder = new StringBuilder();

            preorder.append("[ ");

            while (!stack.isEmpty()) {
                Node p = stack.pop();
                preorder.append('(').append(p.getValue()).append(')');

                preorder.append(" => ");

                if (p.hasRight())
                    stack.push(p.getRight());
                if (p.hasLeft())
                    stack.push(p.getLeft());
            }

            preorder.delete(preorder.length()-4, preorder.length());
            preorder.append(" ]");

            return preorder.toString();
        }

        public String getPostorder() {
            Stack<Node> stack1 = new Stack<>();
            Stack<Node> stack2 = new Stack<>();
            stack1.push(root);
            StringBuilder postorder = new StringBuilder();

            postorder.append("[ ");

            while (!stack1.isEmpty()) {
                Node p = stack1.pop();
                stack2.push(p);

                if (p.hasLeft())
                    stack1.push(p.getLeft());
                if (p.hasRight())
                    stack1.push(p.getRight());

            }

            while (!stack2.isEmpty()) {
                postorder.append('(').append(stack2.pop().getValue()).append(')');
                postorder.append(" => ");
            }

            postorder.delete(postorder.length()-4, postorder.length());
            postorder.append(" ]");

            return postorder.toString();
        }

        public String getInorder() {
            Stack<Node> stack = new Stack<>();
            StringBuilder inorder = new StringBuilder();

            inorder.append("[ ");

            Node current = root;
            while (!stack.isEmpty() || current != null) {

                while (current != null) {
                    stack.push(current);
                    current = current.getLeft();
                }

                if (!stack.isEmpty()) {
                    Node popped = stack.pop();

                    inorder.append('(').append(popped.getValue()).append(')');
                    inorder.append(" => ");

                    current = popped.getRight();
                }
            }

            inorder.delete(inorder.length()-4, inorder.length());
            inorder.append(" ]");

            return inorder.toString();
        }

        public static Node createBound(Object parent, Object right, Object left) {
            Node p, l, r;
            if (parent instanceof Node)
                p = (Node) parent;
            else
                p = new Node(parent);

            if (left instanceof Node)
                l = (Node) left;
            else
                l = new Node(left);

            if (right instanceof Node)
                r = (Node) right;
            else
                r = new Node(right);

            p.setLeft(l);
            p.setRight(r);
            l.setParent(p);
            r.setParent(p);

            return p;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("-_-__Mathematical Expression Tree__-_-");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(".\n.\nto enter polish notation add [-p] flag to start of you input." +
                "\nto exit enter: exit" +
                "\n.\nSupported operators: + - * / ^ ( )" +
                "\n--------------------");

        String input;
        List<Object> postfixList = null;
        List<String> polishlist = null;
        ExpressionTree et;
        while (true) {
            System.out.print(">>> ");
            input = reader.readLine();

            if (input.equals("exit")) break;

            if (input.matches("^-p.+")) {
                polishlist = getPolishNotationList(input);

                if (!validatePolishNotation(polishlist)) {
                    System.out.println("Invalid Input!");
                    continue;
                }

                et = createTreeWithPolish(polishlist);

            } else {
                input = input.replaceAll(" ", "");
                if (!validateExpression(input)) {
                    System.out.println("Invalid Input!");
                    continue;
                }

                postfixList = shuntingYard(input);

                et = createTreeWithPostfix(postfixList);
            }


            System.out.println("Pre-Order Traversal:");
            System.out.println(et.getPreorder());

            System.out.println("Post-Order Traversal:");
            System.out.println(et.getPostorder());

            System.out.println("In-Order Traversal:");
            System.out.println(et.getInorder());

        }
    }

    private static ExpressionTree createTreeWithPostfix(List<Object> postfixList) {
        Stack<Object> stack = new Stack<>();

        while (!postfixList.isEmpty()) {
            stack.push(postfixList.remove(0));

            if (stack.peek() instanceof String) {
                stack.push(ExpressionTree.createBound(stack.pop(), stack.pop(), stack.pop()));
            }
        }

        return new ExpressionTree((ExpressionTree.Node) stack.pop());
    }

    private static List<String> getPolishNotationList(String input) {
        List<String> list = new LinkedList<>();
        input = input.replaceAll("-p", "").trim();

        String[] arr = input.split("\\s");
        for (String s : arr) {
            if (s.length() != 0)
                list.add(s);
        }

        return list;
    }

    private static boolean validatePolishNotation(List<String> list) {
        int container = 0;
        String item;

        for (int i = list.size() - 1; i >= 0; i--) {
            item = list.get(i);
            if (!item.matches("\\d+|[+\\-*/^]"))
                return false;

            if (item.matches("\\d+"))
                container++;
            else
                container--;

            if (container < 1)
                return false;
        }

        return true;
    }

    private static ExpressionTree createTreeWithPolish(List<String> prefixList) {

        ExpressionTree et = new ExpressionTree(new ExpressionTree.Node(prefixList.remove(0)));
        ExpressionTree.Node current = et.getRoot();
        ExpressionTree.Node left;
        ExpressionTree.Node right;
        while (!prefixList.isEmpty()) {

            if (current.getValue().toString().matches("\\d+")) {
                current = current.getParent();
            }

            if(!current.hasLeft()) {
                left = new ExpressionTree.Node(prefixList.remove(0));
                current.setLeft(left);
                left.setParent(current);
                current = current.getLeft();
            } else if (!current.hasRight()) {
                right = new ExpressionTree.Node(prefixList.remove(0));
                current.setRight(right);
                right.setParent(current);
                current = current.getRight();
            } else {
                current = current.getParent();
            }
        }

        return et;
    }


    private static final HashMap<String, Integer>oprPrecedence = new HashMap<>();
    static
    {
        // add and subtract
        oprPrecedence.put("+", 1);
        oprPrecedence.put("-", 1);

        // multiply and divide
        oprPrecedence.put("×", 2);
        oprPrecedence.put("*", 2);
        oprPrecedence.put("÷", 2);
        oprPrecedence.put("/", 2);

        // power
        oprPrecedence.put("^", 3);
    }

    protected static List<Object> shuntingYard(String input) {
        Queue<String> items = new LinkedList<>();
        Stack<String> operators = new Stack<>();
        LinkedList<Object> output = new LinkedList<>();

        input = "(" + input + ")";
//        input = input.replaceAll("-\\(", "-1*(");

        Matcher ext = Pattern.compile("\\d+|\\D").matcher(input);

        while (ext.find()) {
            items.add(ext.group());
        }

        boolean negative = false;
        while (!items.isEmpty()) {
            String element = items.poll();
            if (element.matches("\\D")) { // if element is a operator
                if (element.equals("(")) {
                    operators.push(element);
                    if (items.peek().equals("-")) {
                        negative = true;
                        items.poll();
                    }
                } else if (element.equals(")")) {
                    while (!operators.peek().equals("(")) {
                        output.add(operators.pop());
                    }
                    operators.pop();
                } else if (operators.isEmpty() || operators.peek().equals("(") || element.equals("^")
                        || oprPrecedence.get(element) > oprPrecedence.get(operators.peek())) {
                    operators.push(element);
                } else {
                    while (!operators.isEmpty() && !operators.peek().equals("(")
                            && oprPrecedence.get(element) <= oprPrecedence.get(operators.peek())) {
                        output.add(operators.pop());
                    }
                    operators.push(element);
                }
            } else { // if element is a number
                if (negative) {
                    output.add(new BigInteger("-" + element));
                    negative = false;
                } else
                    output.add(new BigInteger(element));
            }

        }

        return output;
    }

}
