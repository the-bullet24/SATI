package pe.bn.cldinamica.action.form;

public class MensajesActDesTDC extends MensajesTDC{

	private DataTDC data;
	
	public MensajesActDesTDC(){
		
	}
	
	public MensajesActDesTDC(DataTDC data){
		this.data = data;
	}

	public DataTDC getData() {
		return data;
	}

	public void setData(DataTDC data) {
		this.data = data;
	}
	
	

	

}
