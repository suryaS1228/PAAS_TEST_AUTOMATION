<#assign Numofdesc=[]><#list Noofdesc as x><#assign Numofdesc=Numofdesc+[x.value]></#list>
{
  "EndClientUserUniqueSessionId": "1",
  "EndClientUserBrowserSessionId": "12",
  "EndClientUserBrowserInformation": "192.168.5.12",
  "PaaSClientIPAddress": "192.168.5.12",
  "ClientUniqueRequestID": "12587",
  "ServiceRequestDetail": {
  <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
  },
  "QuoteInformation": {
  <#list QuoteInformation as result>"${result.atrib}":"${result.value}",
  </#list>
     "DescriptionOfOperations": {
            "DescriptionOfOperationsList": [
            <#assign i=1>
        <#list 1..Numofdesc[0] as result>
        <#assign descDetails="descDetails"+i>
              {
	              	<#list descDetails?eval as result>"${result.atrib}":"${result.value}"</#list>
	              
              }	<#if result?is_last><#else>,</#if><#assign i=i+1>
              </#list>
            ]
          },
  "Location": {
      "LocationDetail": [
        {
        <#list LocationDetail as result>"${result.atrib}":"${result.value}",
        </#list>
        "Classification": {
            "ClassificationDetail": [
              {
              	<#list ClassificationDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
              	</#list>
              }
            ]
          }
        }
      ]
    }
   }
  }