package humanResources;

public class PartTimeEmployee extends Employee {
    private static final JobTitlesEnum JOB_TITLE = JobTitlesEnum.NONE;
    private static final int SALARY = 0;

    /*
    Конструкторы: такие же, как у суперкласса
     */

    public PartTimeEmployee (String firstName, String secondName) {
        super(firstName, secondName, JOB_TITLE, SALARY);
    }

    public PartTimeEmployee (String firstName, String secondName, JobTitlesEnum jobTitle, int salary) {
        super(firstName, secondName, jobTitle, salary);
    }

    /*
    Методы:
    - реализация метода, возвращающего размер премии – возвращается всегда 0.
    - реализация метода, устанавливающего размер премии – ничего не делает.
     */

    public int getBonus() {
        return 0;
    }

    public void setBonus(int bonus) {
    }

    @Override
    public String toString(){
        //“<secondName> <firstName>, <jobTitle> (внешний совместитель), <salary>р.”
        StringBuilder line = new StringBuilder();
        if (getSecondName() != null && !getSecondName().isEmpty() && getFirstName() != null && !getFirstName().isEmpty()) {
            line.append(getSecondName()).append(" ").append(getFirstName()).append(", ");
        }
        if (getJobTitle() != null && getJobTitle() != JobTitlesEnum.NONE) {
            line.append(getJobTitle()).append(" (внешний совместитель), ");
        }
        if (getSalary() != 0) {
            line.append(getSalary()).append(" р.");
        }
        return line.toString();
    }

    @Override
    public boolean equals(Object obj){
        return super.equals(obj);
    }

    @Override
    public int hashCode(){
        return super.hashCode()^getFirstName().hashCode()^getSecondName().hashCode()^getJobTitle().hashCode()^getSalary();
    }
}
