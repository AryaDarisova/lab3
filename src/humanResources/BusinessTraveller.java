package humanResources;

public interface BusinessTraveller {

    /*
    2 метода:
    - добавляющий информацию о командировке (в качестве параметра принимает ссылку
на экземпляр класса BusinessTravel).
   - возвращающий массив командировок.
     */

    boolean addTravel(BusinessTravel travel);
    BusinessTravel[] getTravels();
}
