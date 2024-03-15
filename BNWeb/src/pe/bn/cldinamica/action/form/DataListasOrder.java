package pe.bn.cldinamica.action.form;

import java.util.Comparator;

public class DataListasOrder implements Comparator<DataListas> {

    public int compare(DataListas o1, DataListas o2) {
        return o1.getDescription().compareTo(o2.getDescription());
    }
	
}
