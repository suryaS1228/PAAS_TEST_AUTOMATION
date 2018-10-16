<#assign numoftravelers=[]><#list Nooftravelers as x><#assign numoftravelers=numoftravelers+[x.value]></#list>
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
        "QuoteInformation": {
        "TravelerList" : [        
          <#assign i=1>
        <#list 1..numoftravelers[0] as result>
        <#assign Traveler="Traveler"+i>
        {
         <#list Traveler?eval as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
        }<#if result?is_last><#else>,</#if><#assign i=i+1>
        </#list>
        ],
        <#list QuoteInformation as result>"${result.atrib}":"${result.value}"<#if result?is_last><#else>,</#if></#list>
      }
    }