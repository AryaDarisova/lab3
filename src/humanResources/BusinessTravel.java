package humanResources;

public final class BusinessTravel {
    private final int compensation;
    private final int daysCount;
    private final String description;
    private final String destination;

    private static final int COMPENSATION = 0;
    private static final int DAYS_COUNT = 0;
    private static final String DESCRIPTION = "";
    private static final String DESTINATION = "";

    /*
    Конструкторы:
    - Без параметров. Поля инициируются: строки – пустыми, числа – 0.
    - Принимающий 4 параметра: название города, число дней (проведенных в
командировке), сумма компенсации, описание
     */

    public BusinessTravel () {
        this(DESTINATION, DAYS_COUNT, COMPENSATION, DESCRIPTION);
    }

    public  BusinessTravel (String destination, int daysCount, int compensation, String description) {
        this.destination = destination;
        this.daysCount = daysCount;
        this.compensation = compensation;
        this.description = description;
    }

    /*
    Методы:
    - возвращающий название города.
    - возвращающий число дней.
    - возвращающий сумму компенсации.
    - возвращающий описание.
     */

    public int getCompensation() {
        return compensation;
    }

    public int getDaysCount() {
        return daysCount;
    }

    public String getDescription() {
        return description;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        //“<city> <daysCount> (<compensation>). <description>”
        return getString().toString();
    }

    public StringBuilder getString(){
        StringBuilder line = new StringBuilder();
        if (destination != null && !destination.isEmpty()) {
            line.append(destination).append(" ");
        }
        if (daysCount != 0 && compensation != 0) {
            line.append(daysCount).append(" (").append(compensation).append("). ");
        }
        if (description != null && !description.isEmpty()) {
            line.append(description);
        }
        return line;
    }

    @Override
    public boolean equals(Object obj)  {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(this.getClass() == obj.getClass())) {
            return false;
        }
        BusinessTravel equalsTravel = (BusinessTravel) obj;
        return (this.compensation == equalsTravel.compensation) &&
                (this.daysCount == equalsTravel.daysCount) &&
                (this.description.equals(equalsTravel.description)) &&
                (this.destination.equals(equalsTravel.destination));
    }

    @Override
    public int hashCode() {
        return compensation ^ daysCount ^ description.hashCode() ^ destination.hashCode();
    }
}
