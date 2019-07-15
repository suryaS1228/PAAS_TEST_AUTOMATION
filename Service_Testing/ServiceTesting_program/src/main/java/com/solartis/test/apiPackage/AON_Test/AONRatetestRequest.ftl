<#assign numberOfProfessionarray=[]><#list NumberOfProfession as x>
<#assign numberOfProfessionarray=numberOfProfessionarray+[x.value]></#list>
<#assign numberOfCov=[]><#list NumberOfCoverage as y>
<#assign numberOfCov = numberOfCov+[y.value]></#list>
<#assign numberOfuw=[]><#list NumberOfUwQuestions as z>
<#assign numberOfuw = numberOfuw+[z.value]></#list>

<#assign i=1>
<#assign j=1>
<#assign k=1>
{
  "Policy": {
  	"Professions": [
  	<#list 1..numberOfProfessionarray[0] as result>
  	<#assign Professions="Profession"+i>
	  	{
	      	
	  			<#list Professions?eval as result>
	  			"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
				</#list>       
	        
	    }
      <#if i=numberOfProfessionarray[0]><#else>,</#if><#assign i=i+1>
      </#list>
	],
  <#list 1..numberOfCov[0] as result>
  	<#assign coverages="coverage"+j>  
     "Coverages": {
    <#list coverages?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
     },
      <#if j=numberOfCov[0]><#else>,</#if><#assign j=j+1>
      </#list>
      
    
    <#list 1..numberOfuw[0] as result>
  	<#assign UwQuestion="UwQuestions"+k>  
     "UwQuestions": {
    <#list UwQuestion?eval as result>
	  			"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
				</#list>
     },
      <#if k=numberOfuw[0]><#else>,</#if><#assign k=k+1>
      </#list> 
<#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
     </#list>  
    
   
    }, 
    "OwnerId": "27",
  "ServiceRequestDetail": {
  "OwnerId": "27",
  <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  },
  
  "EndClientUserUniqueSessionId": "5ba3b6f1f8ce2d1148d3aba3"
}