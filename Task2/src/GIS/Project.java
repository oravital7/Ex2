package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * 
 * @author Dana Mor and Or avital
 *this class represent the Project - set of layers 
 */
public class Project implements GIS_project {
	private Set<GIS_layer> list;
	private Pdata data;
/**
 * 
 * @param data - data of the project
 */
	public Project(Pdata data) {
		list = new HashSet<GIS_layer>();
		this.data = data;
	}
	/**
	 * returns the data of the project
	 */
	@Override
	public Meta_data get_Meta_data() {
		return data;
	}
/**
 * function that add a layer to the project
 */
	@Override
	public boolean add(GIS_layer e) {
		return list.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		return list.addAll(c);
	}
	/**
	 * function that delete all layers
	 */
	@Override
	public void clear() {
		list.clear();		
	}
	/**
	 * function that checks if a layer exists
	 */
	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}
	/**
	 * function that checks if the project is empty
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
/**
 *  returns iterator
 */
	@Override
	public Iterator<GIS_layer> iterator() {
		return list.iterator();
	}
/**
 * remove layer from the project
 */
	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}
	/**
	 * remove all layers from the project
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}
	/**
	 * returns how many layers are in the project
	 */
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	
}
