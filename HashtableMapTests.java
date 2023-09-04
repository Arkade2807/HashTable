// --== CS400 Project One File Header ==--
// Name: Aditya Kamasani
// CSL Username: kamasani
// Email: kamasani@wisc.edu
// Lecture #: Lec 003 mon wends fri 1:20
// Notes to Grader: <any optional extra notes to your grader>
import java.util.NoSuchElementException;

public class HashtableMapTests {
  /**
   * Testing constructors and getter methods
   * @return true if the tested methods are working else return false
   */
  public static boolean test1() {
    HashtableMap map = new HashtableMap();
   if(map.getSize()!= 0 || map.getCapacity()!=8) {
     return false;
   }
   HashtableMap map1 = new HashtableMap(5);
   if(map1.getSize()!= 0 || map1.getCapacity()!=5) {
     return false;
   }
    return true;
    /* test code here */ }
  /**
   * Testing the put method and get method
   * @return true if the tested methods are working else return false
   */
  public static boolean test2() {
    int test = 0;
    
    HashtableMap<Integer, String> map = new HashtableMap<Integer, String>();
    try{map.put(null, "");
    
    }
    catch (IllegalArgumentException e){
      test++;
    }
    map.put(1, "test");
    
    if(map.get(1)!="test") {
      return false;
    }
    try{map.put(1, "test1");
      }catch (IllegalArgumentException e){
        test++;
      }
    if(test == 2) {
      return true;
    }
    return false;
    /* test code here */ }
  /**
   * test remove method
   * @return true if the tested methods are working else return false
   */
  public static boolean test3() {
    int test =0;
    HashtableMap<Integer, String> map = new HashtableMap<Integer, String>(10);
    map.put(8, "test1");
    map.put(18,"test2");
    map.put(28, "test3");
    map.put(38, "test4");
    map.put(48, "test5");
   if(map.containsKey(8)) {
     test++;
   }
   if(map.containsKey(28)) {
     test++;
   }
    map.remove(8);
    if(map.containsKey(8)) {
      return false;
    }
    map.remove(28);
    if(map.containsKey(28)) {
      return false;
    
    }
    if(map.containsKey(38)&&map.containsKey(48)) {
      test++;
    }
    if(test == 3) {
     
      return true;
    }
    return false;
    /* test code here */ }
  
  /**
   * tests rehashing
   * @return true if the tested methods are working else return false
   */
  public static boolean test4() {
    int test =0;
    HashtableMap<Integer, String> map = new HashtableMap<Integer, String>(10);
    map.put(8, "test1");
    map.put(18,"test2");
    map.put(28, "test3");
    map.put(38, "test4");
    map.put(48, "test5");
    map.put(58, "test5");
    map.put(68, "test5");
    map.put(78, "test5");
   if(map.table.length!=20) {
     return false;
   }
    return true;
    /* test code here */ }
  
  /**
   * Tests get method and clear method
   * @return true if the tested methods are working else return false
   */
  public static boolean test5() {
    int test =0;
    HashtableMap<Integer, String> map = new HashtableMap<Integer, String>(10);
    map.put(8, "test1");
    map.put(18,"test2");
    map.put(28, "test3");
    if(!map.get(8).equals("test1")) {
      return false;
    }
    if(!map.get(18).equals("test2")) {
      return false;
    }
    if(!map.get(28).equals("test3")) {
      return false;
    }
    map.clear();
    
   
    if(map.containsKey(8)) {
      return false;
    }
    if(map.containsKey(18)) {
      return false;
    }
    if(map.containsKey(28)) {
      return false;
    }
    return true;
    /* test code here */ }
  /**
   * Main method which runs the tests
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("Test 1:" + test1());
    System.out.println("Test 2:" + test2());
    System.out.println("Test 3:" + test3());
    System.out.println("Test 4:" + test4());
    System.out.println("Test 5:" + test5());
    // TODO Auto-generated method stub

  }

}
