<#assign numofProviderarray=[]><#list NumOfProviders as x><#assign numofProviderarray=numofProviderarray+[x.value]></#list>
<#assign i=1>
{
  "Policy": {
  	"Provider": [
  	<#list 1..numofProviderarray[0] as result>
  	<#assign Provider="Provider"+i>
	  	{
	      	"ProviderDetail":
	      	 {
	  			<#list Provider?eval as result>
	  			"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
				</#list>       
	         }
	    }
      <#if i=numofProviderarray[0]><#else>,</#if><#assign i=i+1>
      </#list>
	],
     <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
     </#list> 
    },
  "OwnerId": "27",
  "ServiceRequestDetail": {
  <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  },
  "EndClientUserUniqueSessionId": "Uniquesession"
}