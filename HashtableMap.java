// --== CS400 Project One File Header ==--
// Name: Aditya Kamasani
// CSL Username: kamasani
// Email: kamasani@wisc.edu
// Lecture #: Lec 003 mon wends fri 1:20
// Notes to Grader: <any optional extra notes to your grader>
import java.util.NoSuchElementException;

/**
 * Helper class to hold the key value pair
 * 
 * @author Aditya
 * @param <ValueType> Generic type
 * @param <KeyType>   Generic type
 *
 */
class keyValuePair<ValueType, KeyType> {
  private KeyType key = null;
  private ValueType value = null;

  /**
   * Constructor
   * @param key   The key which decides where in the array the value is placed and helps locate the
   *              value
   * @param value the thing being store in the hashmap
   */
  public keyValuePair(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Getter method for the instance key
   * 
   * @return the instance key
   */
  public KeyType getKey() {
    return key;
  }

  /**
   * getter method for the instance value
   * 
   * @return the instance value
   */
  public ValueType getValue() {
    return value;
  }

}


/**
 * The class that has the code for all the functionality for th hashmap we are going to build
 * 
 * @author Aditya
 *
 * @param <KeyType>   Generic type
 * @param <ValueType> generic type
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  protected keyValuePair<KeyType, ValueType>[] table;
  private int capacity;
  private int size;
  private keyValuePair<KeyType, ValueType> sentinal = new keyValuePair(null, null);

  /**
   * Constructor
   * 
   * @param capacity the capacity of the hashmap we are about to build but dont worry you can put
   *                 more items than the capacity because we have a rehash function
   */
  public HashtableMap(int capacity) {
    this.capacity = capacity;
    table = new keyValuePair[capacity];
    this.size = 0;
  }

  /**
   * Default constructor
   */
  public HashtableMap() {
    // with default capacity = 8
    this.capacity = 8;
    this.table = new keyValuePair[capacity];

  }

  @Override
  /**
   * method to put the keyvalue pair in the array which is our hashtable
   * 
   * @param key   The key which decides where in the array the value is placed and helps locate the
   *              value
   * @param value The thing we are storing in the hash map
   * @throws IllegalArgumentException if key is null or duplicate key
   */
  public void put(KeyType key, ValueType value) throws IllegalArgumentException {
    if (key == null || this.containsKey(key)) {
      throw new IllegalArgumentException();
    }
    keyValuePair pair = new keyValuePair(key, value);
    int hashfunc = Math.abs(key.hashCode() % this.table.length);
    while (table[hashfunc] != null) { // while the hash map has a value
      if ((table[hashfunc].equals(sentinal))) { // if a value was removed skip
        break;
      }
      hashfunc++;
      if (hashfunc == table.length) { // if we reached the end of the hash loop around
        hashfunc = 0;
      }

    }

    table[hashfunc] = pair; // insert the pair
    size++;
    if ((size / (double) table.length) >= 0.7) { // check if we need to rehash it
      rehash(table);
    }
  }

  /**
   * helper method to help rehash the hashtable
   * 
   * @param array the hashtable we are trying to expand and rehash the keyvalue pairs
   */
  private void rehash(keyValuePair[] array) {
    keyValuePair[] newTable = new keyValuePair[table.length * 2]; // make a new hash array
    for (int i = 0; i < table.length; i++) {
      if (table[i] != null) { // if there is a keyvalue pair
        if (table[i].equals(sentinal)) { // that wasnt removed
          continue;
        }
        keyValuePair pair = table[i];
        int hashfunc = Math.abs(pair.getKey().hashCode() % newTable.length); // new hashfunc
        while (newTable[hashfunc] != null) {
          hashfunc++;
          if (hashfunc == newTable.length) { // loop around when reached the end of the hash
            hashfunc = 0;
          }
        }
        newTable[hashfunc] = pair; // insert the pair
      }
    }
    this.table = newTable; // Instantiating the newtable
    this.capacity = newTable.length;
  }

  /**
   * Method to see if our hashtable contains the keyvalue pair with the given key
   * 
   * @param key The key which decides where in the array the value is placed and helps locate the
   *            value
   * @return true if it does contain the key else false
   */
  @Override
  public boolean containsKey(KeyType key) {
    int hashfunc = Math.abs(key.hashCode() % this.table.length);
    while (table[hashfunc] != null) { // while there is a value
      if (table[hashfunc].getKey() != null && table[hashfunc].getKey().equals(key)) {
        return true; // if the keyvalues match return true
      }

      if (hashfunc == Math.abs(key.hashCode() % this.table.length) - 1) { // if it reads every value
        break; // and loops back break
      }
      hashfunc++;
      if (hashfunc == table.length) { // loop around if at the end of hash
        hashfunc = 0;
      }
    }

    return false;

  }

  @SuppressWarnings("unchecked")
  @Override
  /**
   * @param key The key which decides where in the array the value is placed and helps locate the
   *            value
   * @return the value that the given key is associated with
   * @throws NoSuchElementException if the keyvalue pair with the given key doesnt exsist
   */
  public ValueType get(KeyType key) throws NoSuchElementException {
    // TODO Auto-generated method stub
    int hashfunc = Math.abs(key.hashCode() % this.table.length); // hash function
    while (table[hashfunc] != null) { // while there is a value
      if (table[hashfunc].getKey() == null) { // if its a removed value continue
        hashfunc++;
        continue;
      }
      if (table[hashfunc].getKey() != null && table[hashfunc].getKey().equals(key)) {
        return (ValueType) table[hashfunc].getValue(); // if the key is found return value
      }


      if (hashfunc == Math.abs(key.hashCode() % this.table.length) - 1) { // if every value was
                                                                          // checked break
        break;
      }
      hashfunc++;
      if (hashfunc == table.length) { // loop if at end of hash
        hashfunc = 0;
      }

    }
    throw new NoSuchElementException(); // throw exception if there is no such key
  }

  @SuppressWarnings("unchecked")
  @Override
  /**
   * @param key The key which decides where in the array the value is placed and helps locate the
   *            value
   * @return the value of the keyvalue pair we just removed
   * @throws NoSuchElementException if the keyvalue pair we are trying to remove doesnt exsist
   */
  public ValueType remove(KeyType key) throws NoSuchElementException {
    ValueType value = null;
    int hashfunc = Math.abs(key.hashCode() % this.table.length);// hash function
    while (table[hashfunc] != null) { // while table contains value
      if (table[hashfunc].getKey() == null) { // if it is a removed value continue
        hashfunc++;
        continue;
      }
      if (table[hashfunc].getKey() != null && table[hashfunc].getKey().equals(key)) {
        value = (ValueType) table[hashfunc].getValue();
        table[hashfunc] = sentinal; // remove it by keeping a sentinal value if the keys match
      }
      if (hashfunc == Math.abs(key.hashCode() % this.table.length - 1)) {
        throw new NoSuchElementException(); // if you checked the whole thing throw exception
      }

      hashfunc++;
      if (hashfunc == table.length) { // loop around if at end of hash
        hashfunc = 0;
      }
    }
    return (ValueType) value;
  }

  @Override
  /**
   * clears the present hashtable
   */
  public void clear() {
    this.table = new keyValuePair[capacity];

  }

  @Override
  /**
   * returns the size of the hashtable that has been occupied
   * 
   * @return the amount of hashtable capacity slots used
   */
  public int getSize() {
    // TODO Auto-generated method stub
    return size;
  }

  @Override
  /**
   * returns the capacity
   * 
   * @return the capacity
   */
  public int getCapacity() {
    // TODO Auto-generated method stub
    return capacity;
  }

}
