package humanResources;

//todo УБЕРИ СРАНЫЙ pred!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class Project implements EmployeeGroup{

    //todo вынеси повторяющиеся действия с нодами в приватные методы
    //private add(node), removeNode(index i)
    //
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
        addNode(node);
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
    public Employee[] getEmployees(JobTitlesEnum jobTitle) {
        int peopleByJob = 0;
        Node node = head;
        while (node != null) {
            if (node.value.getJobTitle().equals(jobTitle)) {
               peopleByJob++;
            }
            node = node.getNext();
        }
        Employee[] getEmployees = new Employee[peopleByJob];

        int k = 0;

        for (Employee x: getEmployees()) {
            if (x.getJobTitle() == jobTitle) {
                getEmployees[k++] = x;
            }
        }
        return getEmployees;
    }

    @Override
    public boolean remove(String firstName, String secondName) {
        boolean remove = false;
        Node previous = null;
        Node current;

        for (current = head; current != null; current = current.next){
            if (current.value.getFirstName().equals(firstName) & current.value.getSecondName().equals(secondName)) {
                removeNode(previous, current);
            } else {
                previous = current;
            }
        }
        tail = previous;
        return remove;
    }

    @Override
    public boolean remove(Employee employee) {
        boolean remove = false;
        Node previous = null;
        Node current;

        for (current = head; current != null; current = current.next){
            if (current.value.equals(employee)) {
                removeNode(previous, current);
            } else {
                previous = current;
            }
        }
        tail = previous;
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

    @Override
    public boolean hasEmployee(String firstName, String secondName) {
        Node node = head;
        while (node != null) {
            if (node.value.getFirstName().equals(firstName) &
                    node.value.getSecondName().equals(secondName)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    private void addNode(Node node){
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    private boolean removeNode(Node previous, Node current){
        if (previous != null) {
            previous.setNext(current.getNext());
        }
        size--;
        return true;
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
        return getString().toString();
    }

    public StringBuilder getString(){
        StringBuilder line = new StringBuilder();
        line.append("Project ").append(name).append(": ").append(size).append("\n");
        Node employee = head;
        while (employee != null){
            line.append(employee.value.toString()).append("\n");
            employee = employee.getNext();
        }
        return line;
    }

    @Override
    public boolean equals(Object obj)  {
        if (this == obj)
            return true;
        if(obj == null || !(this.getClass() == obj.getClass()))
            return false;

        Project equalsProject = (Project) obj;
        if (!this.name.equals(equalsProject.name))
            return false;
        if (this.size != equalsProject.size){
            return false;
        }
        Node equalsEmployee = head;
        while (equalsEmployee != null) {
            if (this.head.value != equalsEmployee.value) {
                return false;
            }
            this.head = head.getNext();
            equalsEmployee = equalsEmployee.getNext();
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        Node projects = head;
        while (projects != null) {
            hash ^= projects.value.hashCode();
            projects = projects.getNext();
        }
        return name.hashCode() ^ size ^ hash;
    }
}
