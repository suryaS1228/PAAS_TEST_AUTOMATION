{
  "ServiceRequestDetail": {
  <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
  </#list>
	},
  "AttributeListDetail": {
    "AttributeDetail": [
    
    <#list AttributeListDetail  as result>
	   
	   {
	    "Value": "${result.value}",
        "Key":  "${result.atrib}"
         
        }<#if result?is_last><#else>,</#if> </#list>
 	 ]
  }
}