package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Layer implements GIS_layer {
	private Set<GIS_element> list;
	private Ldata data;

	public Layer(Ldata data) {
		list = new HashSet<GIS_element>();
		this.data=data;
	}

	@Override
	public Meta_data get_Meta_data() {
		return data;
	}

	@Override
	public boolean add(GIS_element e) {
		return list.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		return list.addAll(c);
	}

	@Override
	public void clear() {
		list.clear();		
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<GIS_element> iterator() {
		return list.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

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
	
	public String toString()
	{
		return data.getName();
	}

}
