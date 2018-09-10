{
  "ServiceRequestDetail": 
  {
  	<#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
  },
   "BatchInformation":
    {
   
  "PolicyInformation": [
  {
  	<#list PolicyInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
  }
  ],
  <#list BatchInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
},

   <#list Comman as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
}