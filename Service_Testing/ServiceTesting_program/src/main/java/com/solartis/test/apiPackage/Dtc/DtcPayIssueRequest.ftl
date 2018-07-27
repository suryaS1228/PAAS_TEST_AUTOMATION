{
  "EndClientUserUniqueSessionId": "1",
  "EndClientUserBrowserSessionId": "12",
  "EndClientUserBrowserInformation": "192.168.5.12",
  "PaaSClientIPAddress": "192.168.5.12",
  "ClientUniqueRequestID": "12587",
  "ServiceRequestDetail": 
  {
  	<#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
  },
  "PolicyInformation": 
  {
  	<#list PolicyInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
        </#list>
  }
}