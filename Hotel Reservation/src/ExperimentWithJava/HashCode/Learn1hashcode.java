package ExperimentWithJava.HashCode;

//HashMap and HashSet use the hashcode value of an object to find out
// how the object would be stored in the collection

//Hashing retrieval involves:
/*

* 1. First, find out the right bucket using hashCode().
* 2. Secondly, search the bucket for the right element using equals()
* */
//Overriding both hashcode and equals method

class HashCode{

    String name;
    int id;

    public HashCode(String name, int id){
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        HashCode hsc = (HashCode) obj;

        return (hsc.name.equals(this.name) && hsc.id == this.id);
    }

    @Override
    public int hashCode(){
        return this.id;
    }



}
public class Learn1hashcode {

    public static void main(String[] args) {
        HashCode hsc1 = new HashCode("aditya", 1);
        HashCode hsc2 = new HashCode("aditya", 1);


    }

}
