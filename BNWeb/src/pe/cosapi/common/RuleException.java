package pe.cosapi.common;

import org.apache.struts.action.ActionError;;

public class RuleException
{
	private ActionError ae;

	public ActionError getActionError()
	{
		return ae;
	}

	private RuleException()
	{
	}

	public RuleException(String key)
	{
		ae = new ActionError(key);
	}

	public RuleException(String key, String param0)
	{
		ae = new ActionError(key, param0);
	}

	public RuleException(String key, String param0, String param1)
	{
		ae = new ActionError(key, param0, param1);
	}

	public RuleException(String key, String param0, String param1, String param2)
	{
		ae = new ActionError(key, param0, param1, param2);
	}

	public RuleException(String key, String param0, String param1, String param2, String param3)
	{
		ae = new ActionError(key, param0, param1, param2, param3);
	}

	public RuleException(String key, String[] params)
	{
		ae = new ActionError(key, params);
	}
}
