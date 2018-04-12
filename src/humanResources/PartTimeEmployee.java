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
        //todo в полученный Билдер вставить строку про совместителя
        super.getString().insert(super.getString().lastIndexOf(","), " (внешний совместитель)");
        return super.getString().toString();
    }

    //todo в таком переопределении нет смысла, нужно просто удалить

    //todo вызывай реализацию суперкласса, там уже вычисляется хэн на основе части полей
    @Override
    public int hashCode(){
        return super.hashCode() ^ getJobTitle().hashCode();
    }
}
