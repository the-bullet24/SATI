package pe.bn.login.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.bn.login.domain.Menu;
import pe.cosapi.common.DAOFactory;

public class MenuImpl implements Menu, Serializable {
	
	private String 		codePerson;
	private String 		codeOption;
	private String 		messageOption;
	private String 		descriptionOption;
	private String 		codeFather;
	private BigDecimal 	ordenMenu;
	private Menu[] 		subMenu;
	private Menu[] 		subMenu2;
	
	
	public Menu[] getSubMenu2() {
		return subMenu2;
	}

	public void setSubMenu2(Menu[] subMenu2) {
		this.subMenu2 = subMenu2;
	}

	public Menu[] getMenuByCode(String codigoPersona,String codigoTarjeta) throws Exception {
		MenuImpl menuImpl = new MenuImpl();
		Menu[] arrMenu = DAOFactory.getLoginImpl().getMenuByCode(codigoPersona,codigoTarjeta);
		for (int i = 0; i < arrMenu.length; i++) {
				arrMenu[i].setSubMenu(menuImpl.getSubMenuByCode(arrMenu[i].getCodePerson(), arrMenu[i].getCodeOption()));
				
		}
		return arrMenu;
	}
	
	public Menu[] getMenuByCode1(String codigoPersona,String codigoTarjeta, String ip) throws Exception {
		MenuImpl menuImpl = new MenuImpl();
		Menu[] arrMenu = DAOFactory.getLoginImpl().getMenuByCode1(codigoPersona,codigoTarjeta);
		for (int i = 0; i < arrMenu.length; i++) {
				Menu[] arrSubMenu = menuImpl.getSubMenuByCode1(arrMenu[i].getCodePerson(), arrMenu[i].getCodeOption(), ip);
				arrMenu[i].setSubMenu(arrSubMenu);
				
//				System.out.println("arrMenu.getCodeOption:"+arrMenu[i].getCodeOption());
//				System.out.println("arrMenu.getDescriptionOption:"+arrMenu[i].getDescriptionOption());
				
				if(arrSubMenu!= null){
					for (int y = 0; y < arrSubMenu.length; y++) {
						Menu submenu = arrSubMenu[y];
//						System.out.println("submenu.getCodeOption:"+submenu.getCodeOption());
//						System.out.println("submenu.getDescriptionOption:"+submenu.getDescriptionOption());
//						
						Menu[] arrSubMenu2=DAOFactory.getLoginImpl().getSubMenuByCode2(arrMenu[i].getCodePerson(), arrMenu[i].getCodeOption(),submenu.getCodeOption());
						arrSubMenu[y].setSubMenu2(arrSubMenu2);
						if(arrSubMenu2!= null){
							
							for (int k = 0; k < arrSubMenu2.length; k++) {
								Menu submenu2 = arrSubMenu2[k];
//								System.out.println("submenu2.getCodeOption:"+submenu2.getCodeOption());
//								System.out.println("submenu2.getDescriptionOption:"+submenu2.getDescriptionOption());
							}
							
						}
					
					
					}
					
				}
		
		}
		

		return arrMenu;
	}


	public Menu[] getMenuByCodeOtros(String codigoPersona,String codigoTarjeta) throws Exception {
		MenuImpl menuImpl = new MenuImpl();
		Menu[] arrMenu = DAOFactory.getLoginImpl().getMenuByCodeOtros(codigoPersona,codigoTarjeta);
		for (int i = 0; i < arrMenu.length; i++) {
		
			arrMenu[i].setSubMenu(menuImpl.getSubMenuByCodeOtros(arrMenu[i].getCodePerson(), arrMenu[i].getCodeOption()));
//			System.out.println("arrMenu[i].getSubMenu():"+arrMenu[i].getSubMenu());
			
		}
		return arrMenu;
	}
	
	
	
	private Menu[] getSubMenuByCodeOtros(String codigoPersona, String codigoPadre) throws Exception {
		Menu[] arrSubMenu = DAOFactory.getLoginImpl().getSubMenuByCodeOtros(codigoPersona, codigoPadre);
		if (arrSubMenu.length ==0)
			return null;
		return arrSubMenu;
	}
	

	
	private Menu[] getSubMenuByCode(String codigoPersona, String codigoPadre) throws Exception {
		Menu[] arrSubMenu = DAOFactory.getLoginImpl().getSubMenuByCode(codigoPersona, codigoPadre);
		if (arrSubMenu.length ==0)
			return null;
		return arrSubMenu;
	}
	
	private Menu[] getSubMenuByCode1(String codigoPersona, String codigoPadre,String ip) throws Exception {
		Menu[] arrSubMenu = DAOFactory.getLoginImpl().getSubMenuByCode1(codigoPersona, codigoPadre, ip);
	
		
	for (int i = 0; i < arrSubMenu.length; i++) {
		Menu menu = arrSubMenu[i];
		
				
		Menu[] arrSubMenu2=DAOFactory.getLoginImpl().getSubMenuByCode2(codigoPersona, codigoPadre,menu.getCodeOption());
		
		for (int k = 0; k < arrSubMenu2.length; k++) {
			Menu menu2 = arrSubMenu2[k];
			//System.out.println("getSubMenuByCode2:"+menu2.getDescriptionOption());
		}
		
	}
		if (arrSubMenu.length ==0)
			return null;
		return arrSubMenu;
	}
	

	
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#getSubMenu()
	 */
	public Menu[] getSubMenu() {
		return subMenu;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#setSubMenu(pe.cosapi.domain.impl.MenuImpl[])
	 */
	public void setSubMenu(Menu[] subMenu) {
		this.subMenu = subMenu;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#getCodeFather()
	 */
	public String getCodeFather() {
		return codeFather;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#setCodeFather(java.lang.String)
	 */
	public void setCodeFather(String codeFather) {
		this.codeFather = codeFather;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#getCodeOption()
	 */
	public String getCodeOption() {
		return codeOption;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#setCodeOption(java.lang.String)
	 */
	public void setCodeOption(String codeOption) {
		this.codeOption = codeOption;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#getCodePerson()
	 */
	public String getCodePerson() {
		return codePerson;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#setCodePerson(java.lang.String)
	 */
	public void setCodePerson(String codePerson) {
		this.codePerson = codePerson;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#getDescriptionOption()
	 */
	public String getDescriptionOption() {
		return descriptionOption;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#setDescriptionOption(java.lang.String)
	 */
	public void setDescriptionOption(String descriptionOption) {
		this.descriptionOption = descriptionOption;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#getMessageOption()
	 */
	public String getMessageOption() {
		return messageOption;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#setMessageOption(java.lang.String)
	 */
	public void setMessageOption(String messageOption) {
		this.messageOption = messageOption;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#getOrdenMenu()
	 */
	public BigDecimal getOrdenMenu() {
		return ordenMenu;
	}
	/* (non-Javadoc)
	 * @see pe.cosapi.domain.impl.Menu#setOrdenMenu(java.math.BigDecimal)
	 */
	public void setOrdenMenu(BigDecimal ordenMenu) {
		this.ordenMenu = ordenMenu;
	}
	
}
