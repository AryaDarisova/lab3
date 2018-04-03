package humanResources;

//todo УБЕРИ СРАНЫЙ pred!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class ProjectManager implements GroupsManager{
    private ProjectsNode head;
    private ProjectsNode tail;
    private int size;

    /*
    Конструкторы:
    - по умолчанию (список групп пустой).
    - принимающий параметр – массив групп. Список инициализируется элементами из массива.
     */

    public ProjectManager() {
        size = 0;
    }

    public ProjectManager(EmployeeGroup[] group) {
        for (EmployeeGroup x: group) {
            add(x);
            size++;
        }
    }

    /*
    - добавления группы в конец списка. Принимает ссылку на экземпляр класса, реализующего интерфейс EmployeeGroup в качестве параметра.
     */

    @Override
    public void add(EmployeeGroup groupable) {
        ProjectsNode node = new ProjectsNode(groupable);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    /*
    - удаляющий группу. Принимает группу в качестве параметра. Возвращает число удаленных групп.
     */

    @Override
    public int remove(EmployeeGroup group) {
        int remove = 0;
        ProjectsNode pred = null;
        ProjectsNode current;

        for (current = head; current != null; current = current.getNext()){
            if (current.value.equals(group)) {
                size--;
                remove++;
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

    /*
    - удаляющий группу по его названию (принимает название в качестве параметра). Возвращает логическое значение.
     */

    @Override
    public boolean remove(String groupName) {
        boolean remove = false;
        ProjectsNode pred = null;
        ProjectsNode current;

        for (current = head; current != null; current = current.next){
            if (current.value.getName().equals(groupName)) {
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

    /*
    - возвращающий группу по ее названию (принимает название в качестве параметра).
     */

    @Override
    public EmployeeGroup getEmployeeGroup(String name) {
        EmployeeGroup group = null;
        ProjectsNode node = head;
        while (node != null) {
            if (node.value.getName().equals(name)) {
                group = node.value;
                return group;
            }
            node = node.getNext();
        }
        return group;
    }

    /*
    - возвращающий массив групп.
     */

    @Override
    public EmployeeGroup[] getEmployeeGroups() {
        EmployeeGroup[] getGroups = new EmployeeGroup[size];
        ProjectsNode node = head;
        for (int i = 0; i < size; i++) {
            getGroups[i] = node.value;
            node = node.getNext();
        }
        return getGroups;
    }

    /*
    - возвращающий общее число групп.
     */

    @Override
    public int groupsQuantity() {
        return size;
    }

    /*
    - возвращающий общее число сотрудников во всех группах.
     */

    @Override
    public int employeesQuantity() {
        ProjectsNode node = head;
        int employeesQuantity = 0;
        while (node != null) {
            employeesQuantity += node.value.employeeQuantity();
            node = node.getNext();
        }
        return employeesQuantity;
    }

    /*
    - возвращающий количество сотрудников, занимающих заданную должность (должность передается в качестве параметра).
     */
    //todo аналогично реализации в департаменте

    @Override
    public int employeesQuantity(JobTitlesEnum jobTitle) {
        ProjectsNode node = head;
        int employeesQuantity = 0;
        while (node != null) {
            for (int i = 0; i < node.value.employeeQuantity(); i++) {
                if (node.value.getEmployees()[i].getJobTitle().equals(jobTitle)) {
                    employeesQuantity++;
                }
            }
            node = node.getNext();
        }
        return employeesQuantity;
    }

    /*
    - возвращающий сотрудника с максимальной заработной платой
     */

    @Override
    public Employee mostValuableEmployee() {
        ProjectsNode node = head;
        Employee mostValuableEmployee = head.value.mostValuableEmployee();
        while (node != null) {
            if (mostValuableEmployee.getSalary() < node.value.mostValuableEmployee().getSalary()) {
                mostValuableEmployee = node.value.mostValuableEmployee();
            }
            node = node.getNext();
        }
        return mostValuableEmployee;
    }

    /*
    - возвращающий ссылку на группу к которой относится сотрудник. Имя и фамилия сотрудника передается в качестве параметра.
     */

    //todo аналогично реализации в департаменте
    @Override
    public EmployeeGroup getEmployeesGroup(String firstName, String secondName) {
        ProjectsNode node = head;
        EmployeeGroup getEmployeesGroup = null;
        while (node != null) {
            for (int i = 0; i < node.value.employeeQuantity(); i++) {
                if (node.value.getEmployees()[i].getFirstName().equals(firstName) & node.value.getEmployees()[i].getSecondName().equals(secondName)) {
                    getEmployeesGroup = node.value;
                }
            }
            node = node.getNext();
        }
        return getEmployeesGroup;
    }

    private class ProjectsNode {
        private ProjectsNode next;
        private EmployeeGroup value;

        ProjectsNode(EmployeeGroup value) {
            this.value = value;
        }

        public void setValue(EmployeeGroup value) {
            this.value = value;
        }

        public EmployeeGroup getValue() {
            return value;
        }

        void setNext(ProjectsNode next) {
            this.next = next;
        }

        ProjectsNode getNext() {
            return next;
        }
    }
}
