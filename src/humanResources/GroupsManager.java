package humanResources;

public interface GroupsManager {
    void add(EmployeeGroup groupable);
    int remove(EmployeeGroup group);
    boolean remove(String groupName);
    EmployeeGroup getEmployeeGroup(String name);
    EmployeeGroup[] getEmployeeGroups();
    int groupsQuantity();
    int employeesQuantity();
    int employeesQuantity(JobTitlesEnum jobTitle);
    Employee mostValuableEmployee();
    EmployeeGroup getEmployeesGroup(String firstName, String secondName);
    void setBonus();


    /*
    - добавления группы в конец списка. Принимает ссылку на экземпляр класса, реализующего интерфейс EmployeeGroup в качестве параметра.
    - удаляющий группу. Принимает группу в качестве параметра. Возвращает число удаленных групп.
    - удаляющий группу по его названию (принимает название в качестве параметра). Возвращает логическое значение.
    - возвращающий группу по ее названию (принимает название в качестве параметра).
    - возвращающий массив групп.
    - возвращающий общее число групп.
    - возвращающий общее число сотрудников во всех группах.
    - возвращающий количество сотрудников, занимающих заданную должность (должность передается в качестве параметра).
    - возвращающий сотрудника с максимальной заработной платой
    - возвращающий ссылку на группу к которой относится сотрудник. Имя и фамилия сотрудника передается в качестве параметра.
     */
}
