{
  "ServiceRequestDetail": {
  <#list servicedetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  },
  "Policy": {
  <#list Policy as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
  "DocGenerationFormsList": [<#assign i=1>
  <#list 1..82 as pol>
  <#if Policy??>
	  <#list "DocGenerationFormsList"+i?eval as result>
	  {
	  "${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
	  }<#if result?is_last><#else>,</#if>
	  </#list>
  </#if><#assign i=i+1>
  </#list>
   ],
  }
}