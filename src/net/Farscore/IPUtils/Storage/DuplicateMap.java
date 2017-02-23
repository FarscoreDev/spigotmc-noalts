package net.Farscore.IPUtils.Storage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DuplicateMap<K, V> 
{
	public enum MapType
    {
        Hash,LinkedHash
    }

    public  int HashCode = 0;
    public Map<Key<K>,V> map = null;

    public DuplicateMap()
    {
        map = new HashMap<Key<K>,V>();
    }

    public DuplicateMap( MapType maptype )
    {
        if ( maptype == MapType.Hash ) {
            map = new HashMap<Key<K>,V>();
        }
        else if ( maptype == MapType.LinkedHash ) {
            map = new LinkedHashMap<Key<K>,V>();
        }
        else
            map = new HashMap<Key<K>,V>();
    }

    public V put( K key, V value  )
    {

        return map.put( new Key<K>( key , HashCode++ ), value );
    }

    public void putAll( Map<K, V> map1 )
    {
        Map<Key<K>,V> map2 = new LinkedHashMap<Key<K>,V>();

        for ( Entry<K, V> entry : map1.entrySet() ) {
            map2.put( new Key<K>( entry.getKey() , HashCode++ ), entry.getValue());
        }
        map.putAll(map2);
    }

    public Set<Entry<K, V>> entrySet()
    {
        Set<Entry<K, V>> entry = new LinkedHashSet<Map.Entry<K,V>>();
        for ( final Entry<Key<K>, V> entry1 : map.entrySet() ) {
            entry.add( new Entry<K, V>(){
                private K Key = entry1.getKey().Key();
                private V Value = entry1.getValue();

                @Override
                public K getKey() {
                    return Key;
                }

                @Override
                public V getValue() {
                    return Value;
                }

                @Override
                public V setValue(V value) {
                    return null;
                }});
        }

        return entry;
    }

    @Override
    public String toString() {
        StringBuilder builder = new  StringBuilder();
        builder.append("{");
        boolean FirstIteration = true;
        for ( Entry<K, V> entry : entrySet() ) {
            builder.append( ( (FirstIteration)? "" : "," ) + ((entry.getKey()==null) ? null :entry.getKey().toString() ) + "=" + ((entry.getValue()==null) ? null :entry.getValue().toString() )  );
            FirstIteration = false;
        }
        builder.append("}");
        return builder.toString();
    }

    public class Key<K1>
    {
        K1 Key;
        int HashCode;

        public Key(K1 key, int hashCode) {
            super();
            Key = key;
            HashCode = hashCode;
        }

        public K1 Key() {
            return Key;
        }

        @Override
        public String toString() {
            return  Key.toString() ;
        }

        @Override
        public int hashCode() {

            return HashCode;
        }
    }
}