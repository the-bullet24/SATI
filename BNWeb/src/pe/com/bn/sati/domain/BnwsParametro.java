package pe.com.bn.sati.domain;



	public class BnwsParametro implements java.io.Serializable
	{
	    ParamUrl paramUrl;
	    

		

	    public ParamUrl getParamUrl()
	    {
	        return paramUrl;
	    }


	    public void setParamUrl(ParamUrl paramUrl)
	    {
	        this.paramUrl = paramUrl;
	    }


	    
	    public class ParamUrl implements java.io.Serializable
	    {

	        private String ParamUrlUrl;

	        public String getParamUrlUrl()
	        {
	            return ParamUrlUrl;
	        }

	        public void setParamUrlUrl(String paramUrlUrl)
	        {
	            ParamUrlUrl = paramUrlUrl;
	        }

	    }



	}
	

