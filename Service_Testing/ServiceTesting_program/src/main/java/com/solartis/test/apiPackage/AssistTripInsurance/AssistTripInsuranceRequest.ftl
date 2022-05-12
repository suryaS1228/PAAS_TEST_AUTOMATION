<#assign numoftravelers=[]><#list Nooftravelers as x><#assign numoftravelers=numoftravelers+[x.value]></#list>
{
  	<#list Policy as result>
  	"${result.atrib}":"${result.value}",
    </#list>
    
    "PolicyInformation":
    {
    <#list PolicyInformation as result>
  	"${result.atrib}":"${result.value}",
    </#list>
    
     "PaymentInformation":
     {
     <#list PaymentInformation as result>
  	 "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
     </#list>
    }, 
     
    "TravelerList" : <#assign i=1>
    [
     
        <#list 1..numoftravelers[0] as result>
        <#assign num_of_travelers="num_of_travelers_"+i>
        {
         <#list num_of_travelers?eval as result>
         "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
         </#list>
        }<#if result?is_last><#else>,</#if><#assign i=i+1>
        </#list>
     ]
    
    },
    
    "ServiceRequestDetail":
     {
     <#list ServiceRequestDetail as result>
  	 "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
     </#list>
    }
 }
    