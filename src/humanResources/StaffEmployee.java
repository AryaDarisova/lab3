package humanResources;

public class StaffEmployee extends Employee implements BusinessTraveller{

    //todo делай либо приватные методы работы с нодами, которые будут использоваться в публичных методах, либо выноси список в отдельный класс, со своими методами
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
        head = node;
        head = head.getNext();
        travelsQuantity++;
        return true;
    }

    @Override
    public BusinessTravel[] getTravels() {
        BusinessTravel[] getTravels = new BusinessTravel[travelsQuantity];
        ListNode temp = head;
        for (int i = 0; i < travelsQuantity; i++) {
            getTravels[i] = temp.value;
            temp = temp.getNext();
        }
        return getTravels;
    }

    private class ListNode {
        private ListNode next, prev;
        private BusinessTravel value;

        public ListNode(BusinessTravel value) {
            this.value = value;
        }

        public void setValue(BusinessTravel value) {
            this.value = value;
        }

        public BusinessTravel getValue() {
            return value;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public ListNode getPrev() {
            return prev;
        }

        public void setPrev(ListNode prev) {
            this.prev = prev;
        }
    }

    //todo используй getString() и добавляешь только командировки
    @Override
    public String toString() {
        //“<secondName> <firstName>, <jobTitle>, <salary>р., <bonus>р.
        //Командировки:
        //{<BusinessTravelsInfo>}”
        StringBuilder line = new StringBuilder();
        if (getSecondName() != null && !getSecondName().isEmpty() && getFirstName() != null && !getFirstName().isEmpty()) {
            line.append(getSecondName()).append(" ").append(getFirstName()).append(" ");
        }
        if (getJobTitle() != null && getJobTitle() != JobTitlesEnum.NONE) {
            line.append(getJobTitle()).append(", ");
        }
        if (getSalary() != 0) {
            line.append(getSalary()).append(" р.\nКомандировки:\n");
        }
        ListNode temp = head;
        for (int i = 0; i < travelsQuantity; i++) {
            line.append(temp.value.toString()).append("\n");
            temp = temp.getNext();
        }
        return line.toString();
    }


    @Override
    public boolean equals(Object obj)  {
        //todo вся логика проверки до цикла есть в реализации суперкласса
        if (super.equals(obj)) {
            //теперь тут цикл
        }
        if (this == obj)
            return true;
        if(obj == null && !this.getClass().equals(obj.getClass()))
            return false;
        StaffEmployee temp = (StaffEmployee) obj;
        ListNode current = head;
        while(current!=null) {
            if (this.travelsQuantity != temp.travelsQuantity)
                return false;
            current = current.getNext();
        }
        return (travelsQuantity == temp.travelsQuantity);
    }

    //todo вызывай super/hashcode() тоже
    @Override
    public int hashCode() {
        int hash = 0;
        ListNode temp = head;
        while (temp!=null) {
            hash ^= temp.value.hashCode();
            temp = temp.getNext();
        }
        return Integer.hashCode(travelsQuantity) ^ head.hashCode() ^ hash;
    }
}
