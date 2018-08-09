<#assign numoftravelers=[]><#list No_Of_Travellers as x><#assign numoftravelers=numoftravelers+[x.value]></#list>
{
  "ServiceRequestDetail": 
  {
    <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
  },
  "CustomerInformation": <#assign i=1>
   {
     "TravelerList" : [
     <#list 1..numoftravelers[0] as result>
     {<#assign TravelerListS = "TravelerList"+i>
       <#list TravelerListS?eval as result>	  
	  "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>	  
	  </#list>
     }<#if result?is_last><#else>,</#if><#assign i=i+1>
     </#list>
     <#list CustomerInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
   }
}