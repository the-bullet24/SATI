package pe.bn.login.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Menu extends Serializable {

	/**
	 * @return the subMenu
	 */
	public abstract Menu[] getSubMenu();

	/**
	 * @param subMenu the subMenu to set
	 */
	public abstract void setSubMenu(Menu[] subMenu);
	
	public abstract Menu[] getSubMenu2();

	/**
	 * @param subMenu the subMenu to set
	 */
	public abstract void setSubMenu2(Menu[] subMenu2);

	/**
	 * @return the codeFather
	 */
	public abstract String getCodeFather();

	/**
	 * @param codeFather the codeFather to set
	 */
	public abstract void setCodeFather(String codeFather);

	/**
	 * @return the codeOption
	 */
	public abstract String getCodeOption();

	/**
	 * @param codeOption the codeOption to set
	 */
	public abstract void setCodeOption(String codeOption);

	/**
	 * @return the codePerson
	 */
	public abstract String getCodePerson();

	/**
	 * @param codePerson the codePerson to set
	 */
	public abstract void setCodePerson(String codePerson);

	/**
	 * @return the descriptionOption
	 */
	public abstract String getDescriptionOption();

	/**
	 * @param descriptionOption the descriptionOption to set
	 */
	public abstract void setDescriptionOption(String descriptionOption);

	/**
	 * @return the messageOption
	 */
	public abstract String getMessageOption();

	/**
	 * @param messageOption the messageOption to set
	 */
	public abstract void setMessageOption(String messageOption);

	/**
	 * @return the ordenMenu
	 */
	public abstract BigDecimal getOrdenMenu();

	/**
	 * @param ordenMenu the ordenMenu to set
	 */
	public abstract void setOrdenMenu(BigDecimal ordenMenu);

}