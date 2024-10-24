import java.util.Random;

public class BirthdaySim{

    private Random random;

    //Person class
    public class Person{
        private int birthday;

        //Constructor
        public Person(int birthday){
            this.birthday = birthday;
        } 

        public int getBirthday(){return birthday;};
    }

    //Constructor
    public BirthdaySim(){
        random = new Random();
    }

    public double run(int people, int trials){
        int matches = 0;

        for (int i = 0; i < trials; i++){
            //init array of people
            Person[] arr = new Person[people];
            for(int x = 0; x < people; x++){
                arr[x] = new Person(random.nextInt(365) + 1);
            }

            checkbirthdays:
            for(int a = 0; a < people-1; a++){
                for(int b = a+1; b < people; b++){
                    if(arr[a].getBirthday() == arr[b].getBirthday()){
                        matches++;
                        break checkbirthdays;
                    }
                }
            }
        }

        return matches / (double) trials;
    }

}
