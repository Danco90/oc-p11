package com.pino.project.ocp11.java11.ocp.chapter14.genericsandcollections;

import java.util.*;

public class MapImplementationsDemo {

    public static void main(String[] args) {
        // MAP : identify values by a key
        /*
         * Map methods 						Description
         * void clear()						Removes all keys and values from the map.
         * boolean isEmpty()					Returns whether the map is empty.
         * int size()						Returns the number of entries (key/value pairs) in the map.
         * V get(Object key)					Returns the value mapped by key or null if none is mapped.
         * V put(K key, V value)				Adds or replaces key/value pair. Returns previous value or null.
         * V remove(Object key)				Removes and returns value mapped to key. Returns null if none.
         * boolean containsKey(Object key)   Returns whether key is in map.
         * boolean containsValue(Object)     Returns value is in map.
         * Set<K> keySet()					Returns set of all keys.
         * Collection<V> values()    		Returns Collection of all values.
         */

        //Comparing Map Implementations
        //1. HashMap stores the keys in a hash table and uses the hashCode()
        // of the keys to retrieve their values more efficiently
        //++benefit: adding elements and retrieving the element by key both have O(1) constant time
        //--tradeoff: order of insertion is lost
        System.out.println("HashMap ::");
        Map<String, String> map = new HashMap<>();
        map.put("koala", "bamboo");
        map.put("lion", "meat");
        map.put("giraffe", "leaf");
        String food = map.get("koala"); // bamboo
        for (String key: map.keySet()) System.out.print(key + ","); // koala,giraffe,lion, NOT SORTED order, or the order of insertion
        //The order is determined by hashCode() of the key
        for (String value: map.values()) System.out.print(value + ","); // bamboo,meat,leaf, the order correspond to the order of the keys
//		System.out.println(map.contains("lion"));//COMPILATION ERROR as it is a method of Collection
        System.out.println(map.containsKey("lion"));//true
        System.out.println(map.containsValue("lion"));//false
        System.out.println(map.size()); // 3

        /* Helper methods to create a Map
         * Java 9 - Map.of and Map.ofEntries are static factory method of java.util.Map and have been introduced in java 9
         * After its creation, the keys and values cannot be added, deleted and updated and
         * if we try to these operation, UnsupportedOperationException will be thrown.
         *
         */
        //a) Map.of("key1","value1","key2");//INCORRECT : DOES NOT COMPILE
        map = Map.of("key1","value1","key2","value2");//returns an immutable Map

        //b) There is a better way that lets you supply key/value pairs
        map = Map.ofEntries(
                Map.entry("key1","value1"),
                Map.entry("key2", "value2")
        );

        //c) Java10 - Map.copyOf(map) provides a convenient way to create unmodifiable maps.
        map = Map.copyOf(map);

        // Going through all the data in a map
        System.out.println("\nGoing through all the data in a map ::");
        Map<Integer, Character> mp = new HashMap<>();
        mp.put(1,'a');
        mp.put(2,'b');
        mp.put(3,'c');
        //Way 1
        mp.forEach((k, v) -> System.out.println(v));
        //map.values().forEach(System.out::println);

        System.out.println();
        //Way 2 - get the key/value pairs in a Set
        mp.entrySet().forEach(e ->
                System.out.println(e.getKey() +" "+ e.getValue()));

        //default V getOrDefault(Object key, V defaultValue) behaves the same way as get() when the key is present
        System.out.println("\ndefault V getOrDefault(Object key, V defaultValue) ::");
        Map<Character, String> mpp = new HashMap<>();
        mpp.put('x',"sport");
        System.out.println("X marks the "+ mpp.get('x'));
        System.out.println("X marks the "+ mpp.getOrDefault('x', ""));
        System.out.println("Y marks the "+ mpp.get('y'));
        System.out.println("Y marks the "+ mpp.getOrDefault('y', ""));

        //2. LinkedHashMap: it is like HashMap, but keeps the order of insertion
        //++benefit: order of insertion is maintained
        //--tradeoff: slower than HashMap
        System.out.println("\nLinkedHashMap ::");
        Map<String, String> linkHMap = new LinkedHashMap<>();
        linkHMap.put("koala", "bamboo");
        linkHMap.put("lion", "meat");
        linkHMap.put("giraffe", "leaf");
        for (String key: linkHMap.keySet()) System.out.print(key + ",");// koala, lion, giraffe with the order of insertion

        //3. TreeMap stores the keys in a sorted tree structure.
        //++benefit: keys are always in sorted order
        //--tradeoff: adding and checking if a key is present are both O(log n) good
        System.out.println("\nTreeMap ::");
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("koala", "bamboo");
        treeMap.put("lion", "meat");
        treeMap.put("giraffe", "leaf");
        for (String key: treeMap.keySet()) System.out.print(key + ",");//giraffe,koala,lion in SORTED order

        //4. Hashtable is like Vector* in that it is really old and thread-safe
        Map<Integer,String> hashtable = new Hashtable<>();
        //*ArrayList is to Vector as HashMap is to Hashtable

    }
}
