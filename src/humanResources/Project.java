package humanResources;

public class Project implements EmployeeGroup{
    private String name;
    private int size;
    private Node head;
    private Node tail;

    /*
    Конструкторы:
    - принимающий название (для списка создается только головной элемент).
    - принимающий название и массив сотрудников (список инициализируется элементами из массива).
     */

    public Project(String name) {
        this.name = name;
        size = 0;
    }

    public Project(String name, Employee[] employees) {
        this.name = name;
        for (Employee x: employees) {
            add(x);
            size++;
        }
    }

    @Override
    public void add(Employee employee) {
        Node node = new Node(employee);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    @Override
    public Employee[] sortedEmployees() {
        Employee[] sortedEmployees = getEmployees();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (sortedEmployees[j].getSalary() < sortedEmployees[j + 1].getSalary()) {
                    Employee tmp = sortedEmployees[j];
                    sortedEmployees[j] = sortedEmployees[j + 1];
                    sortedEmployees[j + 1] = tmp;
                }
            }
        }
        return sortedEmployees;
    }

    @Override
    public Employee[] getEmployees() {
        Employee[] getEmployees = new Employee[size];
        Node node = head;
        for (int i = 0; i < size; i++){
            getEmployees[i] = node.value;
            node = node.getNext();
        }
        return getEmployees;
    }

    @Override
    public int employeeQuantity() {
        return size;
    }

    @Override
    public boolean remove(String firstName, String secondName) {
        boolean remove = false;
        Node pred = null;
        Node current;

        for (current = head; current != null; current = current.next){
            if (current.value.getFirstName().equals(firstName) & current.value.getSecondName().equals(secondName)) {
                size--;
                remove = true;
                if (pred != null) {
                    pred.setNext(current.getNext());
                }
            } else {
                pred = current;
            }
        }
        tail = pred;
        return remove;
    }

    @Override
    public boolean remove(Employee employee) {
        boolean remove = false;
        Node pred = null;
        Node current;

        for (current = head; current != null; current = current.next){
            if (current.value.equals(employee)) {
                size--;
                remove = true;
                if (pred != null) {
                    pred.setNext(current.getNext());
                }
            } else {
                pred = current;
            }
        }
        tail = pred;
        return remove;
    }

    @Override
    public Employee getEmployee(String firstName, String secondName) {
        Employee getEmployee = null;
        Node current = head;
        while (current != null) {
            if (current.value.getFirstName().equals(firstName) & current.value.getSecondName().equals(secondName)) {
                getEmployee = current.value;
                return getEmployee;
            }
            current = current.getNext();
        }
        return getEmployee;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Employee mostValuableEmployee() {
        Node current = head;
        Employee mostValuableEmployee = current.value;
        while (current != null) {
            if (mostValuableEmployee.getSalary() < current.value.getSalary()) {
                mostValuableEmployee = current.value;
            }
            current = current.getNext();
        }
        return mostValuableEmployee;
    }

    private class Node {
        private Node next;
        private Employee value;

        Node(Employee value) {
            this.value = value;
        }

        public void setValue(Employee value) {
            this.value = value;
        }

        public Employee getValue() {
            return value;
        }

        void setNext(Node next) {
            this.next = next;
        }

        Node getNext() {
            return next;
        }
    }

    @Override
    public String toString() {
        /*
        “Project <name>: <size>
        <строковое представление employee 1>
        <строковое представление employee 2>
        …
        <строковое представление employee N>”
        */

        StringBuilder line = new StringBuilder("Project " + name + ": " + size + "\n");

        Node current = head;
        while (current != null){
            line.append(current.value.toString()).append("\n");
            current = current.getNext();
        }
        return line.toString();
    }

    @Override
    public boolean equals(Object obj)  {
        if (this == obj)
            return true;
        if(obj == null && !this.getClass().equals(obj.getClass()))
            return false;

        Project equalsProject = (Project) obj;
        if (!equalsProject.name.equals(name))
            return false;
        Node current = head;
        while(current != null) {
            if (this.getEmployee(current.value.getFirstName(), current.value.getSecondName()) != equalsProject.getEmployee(current.value.getFirstName(), current.value.getSecondName()))
                return false;
            current = current.getNext();
        }
        return (size == equalsProject.size);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        Node current = head;
        while (current!=null) {
            hash ^= current.value.hashCode();
            current=current.getNext();
        }
        return Integer.hashCode(size) ^ head.hashCode() ^ hash;
    }
}
