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
        addNode(node);
    }

    /*
    - удаляющий группу. Принимает группу в качестве параметра. Возвращает число удаленных групп.
     */

    @Override
    public int remove(EmployeeGroup group) {
        int remove = 0;
        ProjectsNode previous = null;
        ProjectsNode current;

        for (current = head; current != null; current = current.getNext()){
            if (current.value.equals(group)) {
                if(removeNode(previous, current))
                    remove++;
            } else {
                previous = current;
            }
        }
        tail = previous;
        return remove;
    }

    /*
    - удаляющий группу по его названию (принимает название в качестве параметра). Возвращает логическое значение.
     */

    @Override
    public boolean remove(String groupName) {
        boolean remove = false;
        ProjectsNode previous = null;
        ProjectsNode current;

        for (current = head; current != null; current = current.next){
            if (current.value.getName().equals(groupName)) {
                if (removeNode(previous, current))
                    remove = true;
            } else {
                previous = current;
            }
        }
        tail = previous;
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

        if (size !=0) {
            for (int i = 0; i < size; i++) {
                employeesQuantity += node.value.getEmployees(jobTitle).length;
                node = node.getNext();
            }
            return employeesQuantity;
        }
        else
            return 0;
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
            if (node.value.hasEmployee(firstName, secondName)) {
                getEmployeesGroup = node.value;
            }
            node = node.getNext();
        }
        return getEmployeesGroup;
    }

    private void addNode(ProjectsNode node){
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    private boolean removeNode(ProjectsNode previous, ProjectsNode current){
        if (previous != null) {
            previous.setNext(current.getNext());
        }
        size--;
        return true;
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
