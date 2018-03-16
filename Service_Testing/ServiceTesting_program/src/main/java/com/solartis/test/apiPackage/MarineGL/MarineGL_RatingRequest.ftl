{    
	"EndClientUserUniqueSessionId" : "1",
     "EndClientUserBrowserSessionId" : "12",
     "EndClientUserBrowserInformation" : "192.168.5.12",
     "PaaSClientIPAddress" : "192.168.5.12",
     "ClientUniqueRequestID" : "12587",
     "ServiceRequestDetail": {
      <#list ServiceRequestDetail as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if>
		</#list>
        },
        "QuoteInformation": 
        {
        "Location":{
        "LocationDetail" : [        
      
        {
         <#list LocationDetail?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
          "Classification":{
          "ClassificationDetail": [
          {
           <#list ClassificationDetail?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
          }
          ]
          }
        }
        ],
        <#list QuoteInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
      }
    }