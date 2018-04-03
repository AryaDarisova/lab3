package humanResources;

public abstract class Employee {
    private String firstName;
    private String secondName;
    private JobTitlesEnum jobTitle;
    private int salary;

    private static final JobTitlesEnum JOB_TITLE = JobTitlesEnum.NONE;
    private static final int SALARY = 0;

    /*
    Конструкторы:
    - принимающий два параметра – имя и фамилию. Должность при этом инициализируется пустой строкой, а заработная плата – 0;
    - принимающий четыре параметра – имя, фамилия, должность и заработная плата.
    */

    protected Employee(String firstName, String secondName) {
        this(firstName, secondName, JOB_TITLE, SALARY);
    }

    protected Employee(String firstName, String secondName, JobTitlesEnum jobTitle, int salary) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    /*
    Методы:
    - возвращающий имя.
    - устанавливающий новое значение имени.
    - возвращающий фамилию.
    - устанавливающий новое значение фамилии.
    - возвращающий должность.
    - устанавливающий новое значение должности.
    - возвращающий заработную плату.
    - устанавливающий новое значение заработной платы.
     */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public JobTitlesEnum getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitlesEnum jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public abstract int getBonus();

    public abstract void setBonus(int bonus);

    //todo наоборот, toString вызывает getString()
    @Override
    public String toString(){
        //“<secondName> <firstName>, <jobTitle>, <salary>р.”
        StringBuilder line = new StringBuilder();
        if (secondName != null && !secondName.isEmpty() && firstName != null && !firstName.isEmpty()) {
            line.append(secondName).append(" ").append(firstName).append(", ");
        }
        if (jobTitle != null && jobTitle != JobTitlesEnum.NONE) {
            line.append(jobTitle).append(", ");
        }
        if (salary != 0) {
            line.append(salary).append(" р.");
        }
        return line.toString();
    }

    //todo замени этот piece of shit! Тут реализуется логика формирования строки, и сформированный билдер возвращается
    public StringBuilder getString(){
        return new StringBuilder(this.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(this.getClass() == obj.getClass())) {
            return false;
        }
        Employee temp = (Employee) obj;
        //todo в топку == для строк
        return (firstName == temp.firstName || (firstName != null &&firstName.equals(temp.getFirstName()))) &&
                (secondName == temp.secondName || (secondName != null && secondName.equals(temp.getSecondName()))) &&
                jobTitle == temp.jobTitle && salary == temp.salary;
    }

    @Override
    public int hashCode() {
        return firstName.hashCode()^secondName.hashCode()^jobTitle.hashCode()^salary;
    }
}