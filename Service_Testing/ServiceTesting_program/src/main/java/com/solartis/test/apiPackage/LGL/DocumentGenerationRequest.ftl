{
  "ServiceRequestDetail": {
  <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  },
  "Policy": {
  <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  "DocGenerationFormsList": [<#assign i=1>
  <#list 1..82 as pol>
  <#assign DocGenerationFormsListss="DocGenerationFormsList"+i>
  <#if DocGenerationFormsListss?eval??>
  	{
	  <#list DocGenerationFormsListss?eval as result>	  
	  "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>	  
	  </#list>
	}<#if pol?is_last><#else>,</#if>
  </#if><#assign i=i+1>
  </#list>
   ],
  }
}