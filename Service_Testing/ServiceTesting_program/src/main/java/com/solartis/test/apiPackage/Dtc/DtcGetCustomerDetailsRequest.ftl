{
  "ServiceRequestDetail": 
  {
  	<#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
  },
   "CustomerInformation": 
  {
	<#list CustomerInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
    </#list>
  }
}