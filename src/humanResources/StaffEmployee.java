package humanResources;

public class StaffEmployee extends Employee implements BusinessTraveller{

    //либо выноси список в отдельный класс, со своими методами
    private int bonus;
    private ListNode head;
    private int travelsQuantity;

    private static final JobTitlesEnum JOB_TITLE = JobTitlesEnum.NONE;
    private static final int SALARY = 0;
    private static final int BONUS = 0;

    /*
    Конструкторы:
    - принимающий два параметра – имя и фамилию. Должность при этом
инициализируется значением NONE, заработная плата и премия – 0; список командировок – пустой.
    - принимающий четыре параметра – имя, фамилия, должность и заработная плата.
Список командировок – пустой. Премия – 0.
    - принимающий четыре параметра – имя, фамилия, должность, заработная плата,
массив командировок. Список инициируется значениями из массива. Премия – 0.
     */

    public StaffEmployee(String firstName, String secondName) {
        super(firstName, secondName, JOB_TITLE, SALARY);
        bonus = BONUS;
    }

    public StaffEmployee(String firstName, String secondName, JobTitlesEnum jobTitle, int salary) {
        super(firstName, secondName, jobTitle, salary);
        bonus = BONUS;
    }

    public StaffEmployee(String firstName, String secondName, JobTitlesEnum jobTitle, int salary, BusinessTravel[] travels) {
        super(firstName, secondName, jobTitle, salary);
        for (BusinessTravel temp : travels) {
            addTravel(temp);
            travelsQuantity++;
        }
        bonus = BONUS;
    }

    /*
    Методы:
    - реализация метода, возвращающего размер премии.
    - реализация метода, устанавливающего размер премии.
    - добавляющий информацию о командировке (в качестве параметра принимает ссылку
на экземпляр класса BusinessTravel).
    - возвращающий массив командировок.
     */
    @Override
    public int getTravelsQuantity() {
        return travelsQuantity;
    }

    @Override
    public int getBonus() {
        return bonus;
    }

    @Override
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean addTravel(BusinessTravel travel) {
        ListNode node = new ListNode(travel);
        addNode(node);
        return true;
    }

    private void addNode(ListNode node) {
        if (head == null) {
            head = node;
        }
        else {
            head.next = node;
        }
        head = head.next;
        travelsQuantity++;
    }

    @Override
    public BusinessTravel[] getTravels() {
        BusinessTravel[] getTravels = new BusinessTravel[travelsQuantity];
        ListNode travels = head;
        for (int i = 0; i < travelsQuantity; i++) {
            getTravels[i] = travels.value;
            travels = travels.next;
        }
        return getTravels;
    }

    private class ListNode {
        ListNode next, previous;
        BusinessTravel value;

        ListNode(BusinessTravel value) {
            this.value = value;
        }

    }

        //“<secondName> <firstName>, <jobTitle>, <salary>р., <bonus>р.
        //Командировки:
        //{<BusinessTravelsInfo>}”

    @Override
    public StringBuilder getString() {
        StringBuilder line = super.getString();
        line.append(", ");
        if (bonus != 0) {
            line.append(bonus).append("p.\n");
        }
        line.append("Командировки:\n");
        ListNode travels = head;
        if (head != null) {
            for (int i = 0; i < travelsQuantity; i++) {
                line.append(travels.value.toString()).append("\n");
                travels = travels.next;
            }
        }
        return line;
    }

    @Override
    public boolean equals(Object obj)  {
        if (super.equals(obj)) {
            StaffEmployee equalsEmployee = (StaffEmployee) obj;
            ListNode current = head;
            //теперь тут цикл
            while(current != null) {
                if (this.travelsQuantity != equalsEmployee.travelsQuantity)
                    return false;
                current = current.next;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode() ^ bonus ^ travelsQuantity;;
        ListNode travels = head;
        while (travels != null) {
            hash ^= travels.value.hashCode();
            travels = travels.next;
        }
        return hash;
    }
}
