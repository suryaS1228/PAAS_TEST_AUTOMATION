<#assign numoftravelers=[]><#list Nooftravelers as x><#assign numoftravelers=numoftravelers+[x.value]></#list>
{    
  <#list Policy as result>
  "${result.atrib}":"${result.value}",
  </#list>
  "ServiceRequestDetail": {
     <#list ServiceRequestDetail as result>
     "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	 </#list>
  },
  "QuoteInformation": {
     <#list QuoteInformation as result>
     "${result.atrib}":"${result.value}",
     </#list>
     "TravelerList" : [
        <#assign i=1>
        <#list 1..numoftravelers[0] as result>
        <#assign Traveler="Traveler"+i>
        {
         <#list Traveler?eval as result>
         "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
         </#list>
        }<#if result?is_last><#else>,</#if><#assign i=i+1>
        </#list>
     ]
   }
 }